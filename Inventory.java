import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
    private static boolean isOpen;
    
    public Inventory()
    {
        isOpen = false;
    }
    
    public void addedToWorld(World world)
    {
        isOpen = true;
    }
    
    public void act()
    {
        // Add your action code here.
    }
    
    public static boolean isOpen()
    {
        return isOpen; 
    }
    
    public static void closeInventory()
    {
        isOpen = false;
    }
}
