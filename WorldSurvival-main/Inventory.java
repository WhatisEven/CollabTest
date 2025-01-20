import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo
import java.util.Arrays;
import java.util.Stack;
import java.util.ArrayList;

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
        // A iterative loop that checks and adds similar items that are already exits in the player's inventory (The item must be stackable)
        if (ItemDataManager.isStackable(itemName))
        {
            for (int i = 0; i < inventoryItems.length; i++)
            {
                for (int f = 0; f < inventoryItems[i].length && inventoryItems[i][f] != null; f++)
                {
                    if (inventoryItems[i][f].contains(itemName)) // Check if the player has a similar item in the inventory already
                    {
                        int previousAmount = ItemDataManager.getItemAmount(inventoryItems[i][f]); // Gets the amount of the items that the player already have
                        int total = (previousAmount + itemAmount > GameWorld.MAX_ITEMSTACK) ? GameWorld.MAX_ITEMSTACK : previousAmount + itemAmount; // If the total amount of items is larger than the limit then set it to the limit
                        inventoryItems[i][f] = ItemDataManager.getItemData(total, itemName); // Add the new amount of items to previous amount of items
                        
                        if (previousAmount + itemAmount > GameWorld.MAX_ITEMSTACK)
                        {
                            itemAmount = previousAmount + itemAmount - GameWorld.MAX_ITEMSTACK; // Gets the remaning amounts of items left that cannot be picked up
                        }
                        else
                            return itemAmount = 0;                        
                    }
                }
            }
        }
        
        
        // A iterative loop that checks for empty slots and adds the items to the player's inventory
        for (int i = 0; i < inventoryItems.length; i++)
        {
            for (int f = 0; f < inventoryItems[i].length; f++)
            {
                if (inventoryItems[i][f] == null) // Checks if there is nothing at the player's current inventory slot
                {
                    if (ItemDataManager.isStackable(itemName)) // Checks if the item can be stacked (more than 1)
                    {
                        if (itemAmount < GameWorld.MAX_ITEMSTACK) // Checks if the item trying to store in the inventory is smaller than the stack limit 
                        {
                            inventoryItems[i][f] = ItemDataManager.getItemData(itemAmount, itemName);
                            return itemAmount = 0; // The user has picked up the rest of the items
                        }
                        else
                        {
                            inventoryItems[i][f] = ItemDataManager.getItemData(GameWorld.MAX_ITEMSTACK, itemName); // Store to the maximum stack limit 
                            itemAmount = itemAmount - GameWorld.MAX_ITEMSTACK; // Identify number of items left available
                        }
                    }
                    else // The item is not stackable 
                    {
                        if (itemAmount > 0)
                        {
                            inventoryItems[i][f] = ItemDataManager.getItemData(itemAmount, itemName);
                            itemAmount = itemAmount - 1;
                        }
                        else
                        {
                            return itemAmount = 0; // To make sure that there is no items left unaddded to the player's inventory
                        }
                    }
                }
            }
        }
        return itemAmount;
    }
    
    /**
     * A methiod that returns the total number of empty slots within the player's inventory
     */
    public static int getEmptySlot()
    {
        int emptySlot = inventoryItems.length * inventoryItems[0].length; // The total number of slots of the player inventory
        
        for (String[] itemList : inventoryItems)
        {
            for (String item : itemList)
            {
                emptySlot = (item != null) ? emptySlot - 1 : emptySlot; // If the current inventory item slot is being taken by another item then the empty slot of the inventory is one less than before or else stays the same
            }
        }
        
        return emptySlot;
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
            getWorld().addObject(new ItemHolder(equipItems[i], true), locationX, locationY);
        }
        
        for (int i = 0; i < inventoryItems.length; i++)
        {
            for (int f = 0; f < inventoryItems[i].length; f++)
            {
                locationX = 648 + (f % 6) * 85 + (holderCurrentSize - holderReferenceSize) / 2;
                locationY = 296 + i * 85 + (holderCurrentSize - holderReferenceSize) / 2; 
                getWorld().addObject(new ItemHolder(inventoryItems[i][f], i, f, false), locationX, locationY);
            }
        }
    }
    
    /**
     * A method returns whether the user has a valid amounts of item in comparison from the given item data
     */
    public static boolean hasValidItem(int itemAmount, String itemName)
    {
        int totalAmount = 0; // Initially no items are found within the inventory
        for (int i = 0; i < inventoryItems.length; i++)
        {
            for (int f = 0; f < inventoryItems[i].length && inventoryItems[i][f] != null; f++) // Checks only if the current array contains the item that is trying to find
            {
                if (inventoryItems[i][f].contains(itemName)) // Check if the current index of the array is the same item as the provided item
                {
                    totalAmount += ItemDataManager.getItemAmount(inventoryItems[i][f]); // Gets the amount of items that the user current have in that specific from the item data  
                }
            }
        }
        
        return totalAmount >= itemAmount; // Check if there is more or equal amount of items in the inventory than required 
    }
    
    /**
     * A method that returns whether the user has a set the amount of different items base on the given items 
     */
    public static boolean hasValidItems(String[] itemDatas)
    {
        for (String itemData : itemDatas)
        {
            if (!hasValidItem(ItemDataManager.getItemAmount(itemData), ItemDataManager.getItemName(itemData))) // Checks if the player does not have one of the required item
                return false;
        }
        
        return true;
    }   
    
    /**
     * A method that removes the amount of items in the player's inventory 
     */
    public static void removeItem(int itemAmount, String itemName)
    {
        for (int i = inventoryItems.length - 1; i >= 0; i--) // Loop through the inventory row backward
        {
            for (int f = inventoryItems[i].length - 1; f >= 0 && itemAmount >= 0; f--) // Loop through each individual column in the inventory backward 
            {
                if (inventoryItems[i][f] != null && inventoryItems[i][f].contains(itemName)) // If the current slot has the item that it is currently looking for
                {
                    itemAmount = itemAmount - ItemDataManager.getItemAmount(inventoryItems[i][f]);
                    if (itemAmount >= 0)
                    {
                        inventoryItems[i][f] = null;
                        organizeItems(i, f);
                    }
                    else
                    {
                        inventoryItems[i][f] = ItemDataManager.getItemData(Math.abs(itemAmount), itemName);
                    }
                }
            }
        }
    }
    
    /**
     * A method removes multiple items at once in the player's inventory
     */
    public static void removeItems(String[] itemDatas)
    {
        for (String itemData : itemDatas) // Iterate through all the items that is required be removed
        {
            removeItem(ItemDataManager.getItemAmount(itemData), ItemDataManager.getItemName(itemData)); // Remove one type of item at each time 
        }
    }
    
    /**
     * A method for equipping tools given the data of the tool, the row of the inventory that is taken from and the column of the inventory that is taken from
     */
    protected static void equipTool(String toolData, int inventoryRow, int inventoryColumn, boolean isEquipSlot)
    {
        if (!isEquipSlot) // If the current inventory slot that the user clicks on is not an equip slot
        {
            if (equipItems[equipItems.length - 1] == null) // Checks if there is nothing equipped at the tool slot
            {
                equipItems[equipItems.length - 1] = toolData; // Assigning the new data to the tool position
                inventoryItems[inventoryRow][inventoryColumn] = null; // Remove this from the inventory when equip
                organizeItems(inventoryRow, inventoryColumn); // Organize the inventory to avoid empty slots
            }
            else
            {
                String currentEquip = equipItems[equipItems.length - 1]; // Get the current equip tool data
                equipItems[equipItems.length - 1] = toolData;
                inventoryItems[inventoryRow][inventoryColumn] = currentEquip;
            }
        }
        else
        {
             if (equipItems[equipItems.length - 1] != null)
             {
                 equipItems[equipItems.length - 1] = null; // Remove the current equip item
                 addItem(ItemDataManager.getItemAmount(toolData), ItemDataManager.getItemName(toolData)); // Add the item back into the player inventory
             }
        }
    }
    
    /**
     * A getter method that gets the current equiping tool name
     */
    public static String getToolName()
    {
        return (equipItems[equipItems.length - 1] == null) ? null : ItemDataManager.getItemName(equipItems[equipItems.length - 1]);
    }
    
    /**
     * A method that organizes the player's inventory to ensure that there are no empty spaces slots in between 
     */
    private static void organizeItems(int rowIndex, int columnIndex)
    {
        for (int i = rowIndex; i < inventoryItems.length; i++)
        {
            for (int f = columnIndex; f < inventoryItems[i].length - 1; f++)
            {
                if (inventoryItems[i][f] == null && inventoryItems[i][f + 1] != null) // Check if there is next item in the inventory
                {
                    inventoryItems[i][f] = inventoryItems[i][f + 1]; // Assigning the next item to the current inventory slot
                    inventoryItems[i][f + 1] = null; // Make the next item slot empty
                }
            }
        }
    }
}
