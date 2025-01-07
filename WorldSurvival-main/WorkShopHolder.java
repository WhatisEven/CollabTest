import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ShopHolder here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WorkShopHolder extends WorkShop
{
    /**
     * Act - do whatever the ShopHolder wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private static GreenfootImage backgroundImage;
    private GreenfootImage displayImage;
    public WorkShopHolder()
    {
        backgroundImage = new GreenfootImage(180, 180);
        displayImage = new GreenfootImage(90, 90);
        backgroundImage.setColor(Color.YELLOW);
        displayImage.setColor(Color.WHITE);
        displayImage.setFont(new Font("Arial", 90));
        displayImage.drawString("?", 18, 80);
        backgroundImage.fillRect(0, 0, 180, 180);
        backgroundImage.drawImage(displayImage, 45, 45);
        setImage(backgroundImage);
    }
    
    public void act()
    {
        // Add your action code here.
    }
    
}
