import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends SuperSmoothMover
{
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private static int speed;
    private int distanceFromBorder;
    private static int mapOffsetX, mapOffsetY = 0;    
    private static int objectOffset;

    public Player()
    {
        speed = 2; // Initially the player is moving at a speed of 2 pixels / act
        distanceFromBorder = 0;
    }

    public void act()
    {
        if (Greenfoot.isKeyDown("w"))
        {
            if (!checkBorder(0, -speed)) // Checks if the character is not touching the top edge of the map
            {
                mapOffsetY += speed;
                objectOffset = speed;
            }
            else   
            {
                mapOffsetY += distanceFromBorder;
                objectOffset = distanceFromBorder;
            }
            GameWorld.moveBackground(getWorld(), mapOffsetX, mapOffsetY);
            GameWorld.moveObjects(getWorld(), 0, objectOffset);
        }
        else if (Greenfoot.isKeyDown("s"))
        {
            if (!checkBorder(0, speed)) // Checks if the character is not touching the bottom edge of the map
            {
                mapOffsetY -= speed;
                objectOffset = -speed;
            }
            else
            {
                 mapOffsetY -= distanceFromBorder;
                 objectOffset = -distanceFromBorder;
            }
            GameWorld.moveBackground(getWorld(), mapOffsetX, mapOffsetY);
            GameWorld.moveObjects(getWorld(), 0, objectOffset);
        }
 
        if (Greenfoot.isKeyDown("a")) // If the player press the letter key "a" on the keyboard
        {
            if (!checkBorder(-speed, 0)) // Checks if the character is not touching the most left edge of the map
            {
                mapOffsetX += speed;
                objectOffset = speed;
            }
            else
            {
                 mapOffsetX += distanceFromBorder;
                 objectOffset = distanceFromBorder;
            }
            GameWorld.moveBackground(getWorld(), mapOffsetX, mapOffsetY);
            GameWorld.moveObjects(getWorld(), objectOffset, 0);
        }
        else if (Greenfoot.isKeyDown("d")) // If the player press the letter key "d" on the keyboard
        {
            if (!checkBorder(speed, 0)) // Checks if the character is not touching the most right edge of the map
            {
                mapOffsetX -= speed; // make the x coordinate of the map so that it draws upward
                objectOffset = -speed;
            }
            else  
            {
                mapOffsetX -= distanceFromBorder;  
                objectOffset = -distanceFromBorder;
            }
            GameWorld.moveBackground(getWorld(), mapOffsetX, mapOffsetY); // Moves the background upward
            GameWorld.moveObjects(getWorld(), objectOffset, 0); // Move all necessary objects to fit the visual
        }
    }
    
    /**
     * A method that returns whether the player has reached the end of the world
     * Given the xDireciton (speed and the direction that the player is currently moving, either left or right) horizontally
     * Given the yDireciton (speed and the direction that the player is currently moving, either up or down) vertically 
     */
    private boolean checkBorder(int xDirection, int yDirection)
    {
        for (int i = 0; i < Math.abs(xDirection); i++) // Adding the offset by 1 each time
        {
            if (getOneObjectAtOffset((int)Math.signum(xDirection) * (getImage().getWidth() / 2 + i), 0, Border.class) != null) // Checks all the values within the offset and detects whether the player is about to cross the border
            {
                distanceFromBorder = i;
                return true; // The player is currently tries to cross the border
            }
        }
        
        for (int i = 0; i < Math.abs(yDirection); i++)
        {
            if (getOneObjectAtOffset(0, (int)Math.signum(yDirection) * (getImage().getHeight() / 2 + i), Border.class) != null)
            {
                distanceFromBorder = i;
                return true;
            }
        }
        
        return false; // If not condtion has met the assume the player is not at the border
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
}