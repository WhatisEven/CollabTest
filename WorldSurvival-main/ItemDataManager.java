import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Arrays;
/**
 * Write a description of class ItemDataManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ItemDataManager extends Actor
{
    private static final String dataSeperator = "-";
    private static final String[] stackableItems = {"Log", "Stick", "Rock"}; // A method that can be stacked upon each other
    private static final String[] unstackableItems = {"WoodenHoe", "StoneHoe", "WoodenPickaxe", "StonePickaxe", "WoodenAxe", "StoneAxe"};
    
    /**
     * A method that combines the name of the item and the amounts of the item together to form a combined data name (Only used for stackable item)
     */
    public static String getItemData(int itemAmount, String itemName)
    {
        if (Arrays.asList(stackableItems).contains(itemName)) // Check if the item that is trying to add is a valid stackable item inside the game
        {
            return itemAmount + dataSeperator + itemName;
        }
        else
        {
            return null;
        }
    }
    
    /**
     * A method that combines the name of the item and the amount (1) of the item together to form a combined data name (Only used for unstackable item)
     */
    public static String[] getItemDatas(int itemAmount, String itemName)
    {
        String[] dataList = new String[itemAmount];
        if (Arrays.asList(unstackableItems).contains(itemName)) // Check if the item that is trying to add is a valid unstackable item inside the game
        {
            for (int i = 0; i < itemAmount; i++) // Add each item individually into the array
            {
                dataList[i] = 1 + dataSeperator + itemName; 
            }
        }
        
        return dataList;
    }
    
    /**
     * A getter method that gets the name of the provided combine data 
     */
    public static String getItemName(String itemData)
    {
        return itemData.substring(itemData.indexOf(dataSeperator) + 1);
    }
    
    /**
     * A getter method that gets the amount of items from the provided combine data
     */
    public static int getItemAmount(String itemData)
    {
        return Integer.parseInt(itemData.substring(0, itemData.indexOf(dataSeperator)));
    }
}
