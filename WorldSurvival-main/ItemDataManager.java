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
    public static final String[] allResources = {"Log", "Stick", "Rock"}; // A method that contains all resources in this game
    public static final String[] allTools = {"Hoe", "Pickaxe", "Axe"}; // A method that contains all tools in this game
    /**
     * A method that combines the name of the item and the amounts of the item together to form a combined data name
     */
    public static String getItemData(int itemAmount, String itemName)
    {
        if (Arrays.asList(allResources).contains(itemName)) // Check if the item that is trying to add is a valid stackable item inside the game
        {
            return itemAmount + dataSeperator + itemName;
        }
        else if (Arrays.asList(allTools).contains(itemName))
        {
            return 1 + dataSeperator + itemName;
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

    /**
     * A method that returns whether the item is stackable or not
     */
    public static boolean isStackable(String itemName)
    {
        return Arrays.asList(allResources).contains(itemName);
    }

    /**
     * A getter method that gets the damage that can be dealt of the given tool
     */
    public static int getToolDamage(String itemName, boolean choppingAction, boolean miningAction)
    {
        if (itemName != null)
        {
            switch (Arrays.asList(allTools).indexOf(itemName))
            {       
                case 1: // If the item was a wooden pickaxe
                    if (miningAction)
                        return 2; // 3 damage when mining
                    break;
                case 2: // If the item was a stone pickaxe
                    if (choppingAction)
                        return 3; // 2 damages when mining
                    break;
            }
        }

        return 1; // By default the item will deal atleast 1 damage
    }
}
