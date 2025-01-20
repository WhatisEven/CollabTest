import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class NaturalResources here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class NaturalResources extends Actor
{
    /**
     * Act - do whatever the NaturalResources wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    protected int breakDamage;
    protected int breakPoint, maxBreakPoint;
    protected int rndArea;
    public NaturalResources()
    {
        breakDamage = 1;
    }
    
    public void act()
    {
        clickAction();
        if (breakPoint <= 0) // Checks if the health point of the object is below 0
        {
            ObjectManager.removeObject(this);
            getWorld().removeObject(this);
        }
    }
    
    /**
     * A abstract method that deals with the action after the user clicks on the current object under the same category
     */ 
    private void clickAction()
    {
        // Checks if the user is clicking on the current object and if the in game player is in the range of the action 
        if (Greenfoot.mouseClicked(this) && !getObjectsInRange(rndArea / 2 + 50, Player.class).isEmpty())
        {
            breakPoint -= breakDamage;
            dropLoot();
        }
    }
    
    /**
     * A abstract method that allows for the object to drop loots for player to get upon
     */
    protected abstract void dropLoot(); 
}
