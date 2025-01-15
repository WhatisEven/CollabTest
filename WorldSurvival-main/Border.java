import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Border here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Border extends Actor
{
    /**
     * Act - do whatever the Border wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    protected static GreenfootImage verticalImage, horizontalImage;
    
    public Border()
    {
        verticalImage = new GreenfootImage(1, GameWorld.MAP_IMAGE.getHeight());
        horizontalImage = new GreenfootImage(GameWorld.MAP_IMAGE.getWidth(), 1);
        
        verticalImage.setColor(Color.RED);
        horizontalImage.setColor(Color.RED);
        
        verticalImage.fill();
        horizontalImage.fill();
    }
}
