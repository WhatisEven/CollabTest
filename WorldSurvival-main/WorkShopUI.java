import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WorkShopUI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WorkShopUI extends WorkShop
{
    /**
     * Act - do whatever the WorkShopUI wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private static GreenfootImage backgroundImage;
    
    public WorkShopUI()
    {
        backgroundImage = new GreenfootImage(GameWorld.WORLD_WIDTH, GameWorld.WORLD_HEIGHT);
        backgroundImage.setColor(Color.GRAY);
        backgroundImage.fillRect(0, 0, GameWorld.WORLD_WIDTH, GameWorld.WORLD_HEIGHT);
        backgroundImage.setTransparency(250);
        setImage(backgroundImage);
    }

    public void act()
    {
        
    }
}
