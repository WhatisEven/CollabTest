import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Loot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DropLoot extends Actor
{
    /**
     * Act - do whatever the Loot wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    private GreenfootImage lootImage;
    private int lootAmount;
    private int moveSpaces;
    private String lootName;
    private Actor fromActor;
    private boolean firstInitialize;
    public DropLoot(int lootAmount, String lootName, Actor fromActor)
    {
        moveSpaces = 0; // Initally the object does not needs to move
        firstInitialize = false;
        lootImage = new GreenfootImage(30, 30);
        lootImage.setColor(Color.YELLOW);
        lootImage.fillOval(0, 0, 30, 30);
        setImage(lootImage);
        this.lootAmount = lootAmount;
        this.lootName = lootName;
        this.fromActor = fromActor; // Get the actor that dropped this loot
    }

    public void act()
    {
        if (!firstInitialize) // If the object is the first time added to the current world
        {
            setSpaces();
            firstInitialize = true;
        }

        moveToPosition();

        if (!getObjectsInRange(45, Player.class).isEmpty()) // Checks if the player is within the range of collecting the object
        {
            if (Greenfoot.isKeyDown("f"))// Checks if the user has press "f" key 
            { 
                int remainingAmount = Inventory.addItem(lootAmount, lootName); // Gets remaning amount of items that are unable to be added to player's inventory
                if (remainingAmount == 0) // Checks all items are sucessfully added into the player's inventory
                    getWorld().removeObject(this);
                else{
                    lootAmount = remainingAmount; // Set the current loot amounts to the remaining loot amounts
                    getWorld().addObject(new MessageDisplay("Insufficient inventory spaces", Color.RED), 0, 0); // Display a message that indicate to the user there is not enough inventory spaces to pick up all the items
                }
            }
        }
    }

    /**
     * A method that deals with a fix moving action of the object
     */
    private void moveToPosition()
    {
        if (moveSpaces > 0)
        {
            move(5);
            moveSpaces -= 5;
        }
    }

    /**
     * A method that sets a fix amounts of space to which the item should move to when dropped
     */
    private void setSpaces()
    {
        // Checks if the actor that dropped the loot is still in the world
        if (fromActor.getWorld() != null)
        {
            moveSpaces = fromActor.getImage().getWidth() / 2 + Greenfoot.getRandomNumber(25);
            setRotation(Greenfoot.getRandomNumber(360)); // A random Rotation between 0 degrees (minimum) to 359 degrees (maximum)
        }
    }
}
