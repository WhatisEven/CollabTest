import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WorkShop here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class WorkShop extends Actor
{
    private boolean isOpen;
    /**
     * Act - do whatever the WorkShop wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    /**
     * A getter method that gets the current opening state of the workshop
     */
    public boolean isOpen()
    {
        return isOpen;
    }
    
    /**
     * A method that indicates opening of the workshop
     */
    public void openWorkShop()
    {
        isOpen = true;
        addHolders();
    }
    
    /**
     * A method that indicates closing of the workshop
     */
    public void closeWorkShop()
    {
        isOpen = false;
    }
    
    private void addHolders()
    {
        for (int i = 0; i < 4; i++)
        {
            for (int f = 0; f < 6; f++)
            {
                getWorld().addObject(new WorkShopHolder(), 300, 700);
            }
        }
    }
}
