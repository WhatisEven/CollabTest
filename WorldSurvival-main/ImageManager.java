import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Arrays;
/**
 * Write a description of class ImageManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ImageManager extends Actor
{
    
    /**
     * A method that returns the image of the given item with a scaling system
     */
    public static GreenfootImage getImage(String itemName, double sizeMultiplier)
    {
        // Check if the item is a valid in game item
        if (Arrays.asList(ItemDataManager.allTools).contains(itemName) || Arrays.asList(ItemDataManager.allResources).contains(itemName))
        {
            GreenfootImage itemImage = new GreenfootImage(itemName + ".png");
            itemImage.scale((int)Math.round(itemImage.getWidth() * sizeMultiplier), (int)Math.round(itemImage.getHeight() * sizeMultiplier)); // Scale the image 
            
            return itemImage;
        }
            
            
        return null;
    }
}
