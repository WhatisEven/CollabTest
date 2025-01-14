import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class InventoryUI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InventoryUI extends Inventory
{
    /**
     * Act - do whatever the InventoryUI wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    private static GreenfootImage backgroundImage;
    
    public InventoryUI()
    {
        backgroundImage = new GreenfootImage("InventoryBackground.png");
        setImage(backgroundImage);
    }
}
