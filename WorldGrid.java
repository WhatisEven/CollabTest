import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WorldGrid here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WorldGrid extends Actor
{
    /**
     * Act - do whatever the WorldGrid wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private static GreenfootImage gridImage;
    private boolean hasGenerated;
    
    public WorldGrid()
    {
        hasGenerated = false;
        gridImage = new GreenfootImage(GameWorld.MAX_GRIDLENGTH, GameWorld.MAX_GRIDLENGTH);
        gridImage.setColor(new Color (Greenfoot.getRandomNumber(255), Greenfoot.getRandomNumber(255), Greenfoot.getRandomNumber(255)));
        gridImage.setTransparency(40);
        gridImage.fillRect(0, 0, GameWorld.MAX_GRIDLENGTH, GameWorld.MAX_GRIDLENGTH);
        setImage(gridImage);
    }
    
    /**
     * A setter method that sets the current block status
     */
    public void setGenerated()
    {
        hasGenerated = true;
    }
    
    /**
     * Returns whether the current area of the map has already been generated / considered by object generator
     */
    public boolean getGeneratedStatus()
    {
        return hasGenerated;
    }
}
