import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PlayerStatusBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class PlayerStatusBar extends Actor
{
    /**
     * Act - do whatever the PlayerStatusBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    protected GreenfootImage borderImage, remainImage, backgroundImage;
    protected Label statusText;
    
    public PlayerStatusBar()
    {
        updateBar();    
    }
    
    public void addedToWorld(World world)
    {
        world.addObject(statusText, getX() - 17, getY());
    }
    
    public void act()
    {
        displayText();
        updateBar();
    }
    
    /**
     * A abstract method that allows for continuously updates the status text
     */
    protected abstract void displayText();
    
    /**
     * A abstract method that allows for continuously updates the filling of the bar base on the remaning player status
     */
    protected abstract void updateBar();
}
