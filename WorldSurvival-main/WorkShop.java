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
    protected static final int HOLDER_SIZE = 220;
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
        addHolders(); // Add all the work shop holders to the world
    }

    /**
     * A method that indicates closing of the workshop
     */
    public void closeWorkShop()
    {
        isOpen = false;
        getWorld().removeObjects(getWorld().getObjects(WorkShopHolder.class)); // Remove all the work shop holders in the world
    }

    /**
     * A method that displays all the shop items 
     */
    private void addHolders()
    {
        for (int i = 0; i < 2; i++)
        {
            for (int f = 0; f < 5; f++)
            {
                getWorld().addObject(new WorkShopHolder(), getX() - getImage().getWidth() / 2 + 140 + f * 280, getY() - getImage().getHeight() / 2 + 280 + i * 280);
            }
        }
    }
    
    
}