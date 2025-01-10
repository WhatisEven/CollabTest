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
        backgroundImage = new GreenfootImage(1200, 800);
        backgroundImage.setColor(Color.GRAY);
        backgroundImage.fillRect(0, 0, 1200, 800);
        backgroundImage.setTransparency(250);
        setImage(backgroundImage);
    }

    public void act()
    {
        // Add your action code here.
    }
}
