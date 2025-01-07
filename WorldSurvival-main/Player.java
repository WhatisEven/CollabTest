import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Arrays;
import java.util.ArrayList;
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private static int speed, sprintSpeed, walkSpeed;
    private static int energyPoint, maxEnergyPoint, recoveryTick, recoveryAmount;
    private static int hungerPoint, maxHungerPoint;
    private int distanceFromObject;
    private static int mapOffsetX, mapOffsetY = -1; 
    private static int objectOffset;
    private static int moveThreshold;
    private static int MAX_THRESHOLD = 60; // Maximum 60 movements of the player before dropping of 1 hunger point
    private static int MIN_THRESHOLD = 15; // Minimum 15 movements of the player before dropping of 1 hunger point 
    private GameWorld world;
    private boolean isSprinting, isMoving;
    public Player()
    {
        maxEnergyPoint = 30;
        maxHungerPoint = 50;
        energyPoint = maxEnergyPoint; // Initally the player has a maximum energy point of 30
        hungerPoint = maxHungerPoint; // Initially the player is not starving 
        walkSpeed = 2; 
        sprintSpeed = (int)Math.round(walkSpeed * 1.5);
        speed = walkSpeed; // Initially the player is moving at a speed of 2 pixels / act
        isSprinting  = false;
        isMoving = false;
        distanceFromObject = 0;
        recoveryTick = 0; // Initially 0 act for every energy recovery
        recoveryAmount = 1; // Default energy recovering 1 per tick
        moveThreshold = Greenfoot.getRandomNumber(MAX_THRESHOLD) + MIN_THRESHOLD;
    }

    public void addedToWorld(World world)
    {
        this.world = (GameWorld)(world);
        if (mapOffsetX != -1 || mapOffsetY != -1) 
            GameWorld.spawnLocation(); // Spawn the player randomly in the world 
    }

    public void act()
    {
        if (!world.getUIOpening())
        {
            hungerMovement();
            triggerSprint();
            if (Greenfoot.isKeyDown("w"))
            {
                if (!checkObjects(0, -speed)) // Checks if the character is not touching the top edge of the map
                {
                    mapOffsetY += speed;
                    objectOffset = speed; 
                }
                else   
                {
                    mapOffsetY += distanceFromObject;
                    objectOffset = distanceFromObject;
                }
                GameWorld.moveBackground(world, mapOffsetX, mapOffsetY);
                GameWorld.moveObjects(world, 0, objectOffset);
            }
            else if (Greenfoot.isKeyDown("s"))
            {
                if (!checkObjects(0, speed)) // Checks if the character is not touching the bottom edge of the map
                {
                    mapOffsetY -= speed;
                    objectOffset = -speed;
                }
                else
                {
                    mapOffsetY -= distanceFromObject;
                    objectOffset = -distanceFromObject;
                }
                GameWorld.moveBackground(world, mapOffsetX, mapOffsetY);
                GameWorld.moveObjects(world, 0, objectOffset);
            }

            if (Greenfoot.isKeyDown("a")) // If the player press the letter key "a" on the keyboard
            {
                if (!checkObjects(-speed, 0)) // Checks if the character is not touching the most left edge of the map
                {
                    mapOffsetX += speed;
                    objectOffset = speed;
                }
                else
                {
                    mapOffsetX += distanceFromObject;
                    objectOffset = distanceFromObject;
                }
                GameWorld.moveBackground(world, mapOffsetX, mapOffsetY);
                GameWorld.moveObjects(world, objectOffset, 0);
            }
            else if (Greenfoot.isKeyDown("d")) // If the player press the letter key "d" on the keyboard
            {
                if (!checkObjects(speed, 0)) // Checks if the character is not touching the most right edge of the map
                {
                    mapOffsetX -= speed; // make the x coordinate of the map so that it draws upward
                    objectOffset = -speed;
                }
                else  
                {
                    mapOffsetX -= distanceFromObject;  
                    objectOffset = -distanceFromObject;
                }
                GameWorld.moveBackground(world, mapOffsetX, mapOffsetY); // Moves the background upward
                GameWorld.moveObjects(world, objectOffset, 0); // Move all necessary objects to fit the visual
            }
        }
    }

    /**
     * A method that allows for the user to trigger a sprint mode for the in game player
     * Press / Hold shift key to start sprinting
     */
    private void triggerSprint()
    {
        if (Greenfoot.isKeyDown("shift") && energyPoint > 0 && isMoving) // Checks if the user has pressed shif key
        {
            if (!isSprinting) // Checks if the player is already sprinting
            {
                speed = sprintSpeed; // Set the current player's speed to its sprinting speed
                isSprinting = true;
            }
            energyPoint--; 
        }
        else
        {
            if (isSprinting)
            {
                speed = walkSpeed; // Set the current player's speed to its walking speed
                isSprinting = false;
            }
            automaticRecovery();
        }
    }

    /**
     * A automatic method for energy recovery over time
     */
    private static void automaticRecovery()
    {
        if (energyPoint < maxEnergyPoint) // Checks if there is needs to recover more energy
        {
            if (recoveryTick == 0)
            {
                recoveryTick = (int)Math.round((1 / ((double)hungerPoint / maxHungerPoint)) * 2); // A recovery logic that based on player's hunger bar, higher the hunger bar of the player shorter time needed to recover back energy
                energyPoint += recoveryAmount;
            }
            else
            {
                recoveryTick--;
            }
        }
    }

    /**
     * A method that returns whether the player is/will contact a physical object within the world
     * Given the xDireciton (speed and the direction that the player is currently moving, either left or right) horizontally
     * Given the yDireciton (speed and the direction that the player is currently moving, either up or down) vertically 
     */
    private boolean checkObjects(int xDirection, int yDirection)
    {
        // Checking for horizontal direction of the player
        for (int i = 0; i < Math.abs(xDirection); i++) // Adding the offset by 1 each time
        {
            ArrayList<Actor> encounterObjects = (ArrayList<Actor>)getObjectsAtOffset((int)Math.signum(xDirection) * (getImage().getWidth() / 2 + i), 0, Actor.class);
            if (!encounterObjects.isEmpty()) // Checks if there is a object within the range
            {
                for (Actor encounterObject : encounterObjects) // Iterate through all detected objects
                {
                    if (Arrays.asList(GameWorld.objectClass).contains(encounterObject.getClass())) // Checks if there is a indicated physical object among all the indicated objects  
                    {
                        distanceFromObject = i;
                        return true; // The player is currently encountering a physical object
                    }
                }
            }
        }

        // Checking for vertical direction of the player
        for (int i = 0; i < Math.abs(yDirection); i++)
        {
            ArrayList<Actor> encounterObjects = (ArrayList<Actor>)getObjectsAtOffset(0, (int)Math.signum(yDirection) * (getImage().getHeight() / 2 + i), Actor.class);
            if (!encounterObjects.isEmpty())
            {
                for (Actor encounterObject : encounterObjects)
                {
                    if (Arrays.asList(GameWorld.objectClass).contains(encounterObject.getClass()))
                    {
                        distanceFromObject = i;
                        return true;
                    }
                }
            }
        }

        return false; // If not condtion has met the assume the player is not encountering a physical object
    }

    /**
     * A method that manages the hunger bar during player's movement;
     */
    private void hungerMovement()
    {   
        if (moveThreshold == 0) // Reset to a new threshold when reaches 0
        {
            moveThreshold = Greenfoot.getRandomNumber(MAX_THRESHOLD) + MIN_THRESHOLD; 
            if (hungerPoint > 0)
                hungerPoint--; // The player loses 1 hunger point when the condition has met
            else
                reduceEnergy(1); // The player loses 1 energy point when no hunger point detected
        }

        if (Greenfoot.isKeyDown("w") || Greenfoot.isKeyDown("s") || Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("d")) // Determines if the player is moving 
        {
            moveThreshold--;
            isMoving = true;
        }
        else
        {
            isMoving = false;
        }
    }

    /**
     * A getter method that gets the current horizontal offset of the map
     */
    public static int getMapOffsetX()
    {
        return mapOffsetX;
    }

    /**
     * A getter method that gets the current vertical offset of the map
     */
    public static int getMapOffsetY()
    {
        return mapOffsetY;
    }
    
    /**
     * A setter method that set the current horizontal offset of the map
     */
    public static void setMapOffsetX(int offsetX)
    {
        mapOffsetX = offsetX;
    }
    
    /**
     * A setter method that set the current vertical offset of the map
     */
    public static void setMapOffsetY(int offsetY)
    {
        mapOffsetY = offsetY;
    }

    /**
     * A setter method that sets the maximum energy of the player
     */
    public static void setMaxEnergy(int newEnergyPoint)
    {
        maxEnergyPoint = (newEnergyPoint <= 0) ? 1 : newEnergyPoint; // If the program tries to set the lowest point below or equals to zero, it will be automatically change it to 1 as the lowest instead 
    }
    
    /**
     * A method that allows to reduce current amount of energies that the player currently have
     * Player's current energy will not fall below 0 
     */
    public static void reduceEnergy(int reduceAmount)
    {
        energyPoint = (energyPoint - reduceAmount < 0) ? 0 : energyPoint - reduceAmount; // Automatically set the energy to be 0 when trying to reduce to below minimum amount
    }

    /**
     * A getter method that gets the remaining energy of the player 
     */
    public static int getEnergyPoint()
    {
        return energyPoint;
    }

    /**
     * A getter method that gets the max energy of the player
     */
    public static int getMaxEnergy()
    {
        return maxEnergyPoint;
    }

    /**
     * A getter method that gets the current hunger bar of the player 
     */
    public static int getHungerPoint()
    {
        return hungerPoint;
    }

    /**
     * A getter method that gets the maxumum hunger bar of the player
     */
    public static int getMaxHunger()
    {
        return maxHungerPoint;
    }

    /**
     * A getter method that gets the player's damage for chopping down trees
     */
    public static int getChopDamage()
    {
        return 1;
    }
}