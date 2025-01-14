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
    private static final String[] allItemsName = {"Log", "Stick", "Rock"};
    
    /**
     * A method that combines the name of the item and the amounts of the item together to form a combined data name
     */
    public static String getItemData(int itemAmount, String itemName)
    {
        if (Arrays.asList(allItemsName).contains(itemName)) // Check if the item that is trying to add is a valid item inside the game
        {
            return itemAmount + dataSeperator + itemName;
        }
        else
        {
            return null;
        }
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
