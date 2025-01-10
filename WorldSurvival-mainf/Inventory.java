import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo
import java.awt.Event;
/**
 * Write a description of class Inventory here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Inventory extends Actor
{
    /**
     * Act - do whatever the Inventory wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    protected static final String dataSeperator = "-";
    
    protected boolean isOpen;
    protected static String[] equipItems = new String[5];
    protected static String[][] inventoryItems = new String[5][6]; // Inventory can hold a total of 30 different items
    protected static int holderReferenceSize, holderCurrentSize;
    public Inventory()
    {
        holderReferenceSize = 100; // Reference to the image size of item holder is 100
        holderCurrentSize = 70; // Reference to the current image size of the item holder
    }
   
    /**
     * A getter method that gets the current state of the inventory 
     */
    public boolean isOpen()
    {
        return isOpen; 
    }
    
    /**
     * A method that modifies the opening of the player's inventory 
     */
    public void openInventory()
    {
        isOpen = true;
        addItemHolders(); // Adds all item holders in the inventory 
    }
    
    /**
     * A method that modifies the closing of the player's inventory
     */
    public void closeInventory()
    {
        isOpen = false;
        getWorld().removeObjects(getWorld().getObjects(ItemHolder.class)); // Removes all item holders in the inventory
    }
    
    /**
     * A method that add items to the player's inventory
     * Returns number of remaining items left that are available to be picked up
     */
    public static int addItem(int itemAmount, String itemName)
    {
        // A iterative loop that checks and adds similar items that are already exits in the player's inventory
        for (int i = 0; i < inventoryItems.length; i++)
        {
            for (int f = 0; f < inventoryItems[i].length && inventoryItems[i][f] != null; f++)
            {
                if (inventoryItems[i][f].contains(itemName)) // Check if the player has a similar item in the inventory already
                {
                    int previousAmount = Integer.parseInt(inventoryItems[i][f].substring(0, inventoryItems[i][f].indexOf(dataSeperator))); // Gets the amount of the items that the player already have
                    int total = (previousAmount + itemAmount > GameWorld.MAX_ITEMSTACK) ? GameWorld.MAX_ITEMSTACK : previousAmount + itemAmount; // If the total amount of items is larger than the limit then set it to the limit
                    inventoryItems[i][f] = total + dataSeperator + itemName; // Add the new amount of items to previous amount of items
                    
                    if (previousAmount + itemAmount > GameWorld.MAX_ITEMSTACK)
                    {
                        itemAmount = previousAmount + itemAmount - GameWorld.MAX_ITEMSTACK; // Gets the remaning amounts of items left that cannot be picked up
                    }
                    else
                        return itemAmount = 0;                        
                }
            }
        }
        
        // A iterative loop that checks for empty slots and adds the items to the player's inventory
        for (int i = 0; i < inventoryItems.length; i++)
        {
            for (int f = 0; f < inventoryItems[i].length; f++)
            {
                if (inventoryItems[i][f] == null)
                {
                    if (itemAmount < GameWorld.MAX_ITEMSTACK) // Checks if the item trying to store in the inventory is smaller than the stack limit 
                    {
                        inventoryItems[i][f] = itemAmount + dataSeperator + itemName;
                        return itemAmount = 0; // The user has picked up the rest of the items
                    }
                    else
                    {
                        inventoryItems[i][f] = GameWorld.MAX_ITEMSTACK + dataSeperator + itemName; // Store to the maximum stack limit 
                        itemAmount = itemAmount - GameWorld.MAX_ITEMSTACK; // Identify number of items left available
                    }
                }
            }
        }
        return itemAmount;
    }
    
     /**
     * A method that adds all necesscery item holders in the inventory
     */
    private void addItemHolders()
    {
        int locationX, locationY; // The x and y location of the item holder
        for (int i = 0; i < equipItems.length; i++)
        {
            locationX = (i % 2 == 0 && i != 4) ? 365 + (holderCurrentSize - holderReferenceSize) / 2 : (i != 4) ? 365 + 130 + (holderCurrentSize - holderReferenceSize) / 2 : 365 + 65 + (holderCurrentSize - holderReferenceSize) / 2; // A calculation for the fixed position of the 5 item holder 
            locationY = (i < 2) ? 315 + (holderCurrentSize - holderReferenceSize) / 2 : (i < 4) ? 315 + 150 + (holderCurrentSize - holderReferenceSize) / 2 : 315 + 300 + (holderCurrentSize - holderReferenceSize) / 2;
            getWorld().addObject(new ItemHolder(equipItems[i]), locationX, locationY);
        }
        
        for (int i = 0; i < inventoryItems.length; i++)
        {
            for (int f = 0; f < inventoryItems[i].length; f++)
            {
                locationX = 648 + (f % 6) * 85 + (holderCurrentSize - holderReferenceSize) / 2;
                locationY = 296 + i * 85 + (holderCurrentSize - holderReferenceSize) / 2; 
                getWorld().addObject(new ItemHolder(inventoryItems[i][f]), locationX, locationY);
            }
        }
    }
}
