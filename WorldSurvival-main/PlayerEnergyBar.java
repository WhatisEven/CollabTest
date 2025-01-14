import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PlayerHealthBAr here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerEnergyBar extends PlayerStatusBar
{
    /**
     * Act - do whatever the PlayerHealthBAr wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */    
    
    public PlayerEnergyBar()
    {
        statusText = new Label(Player.getEnergyPoint(), 50);
    }
    
    public void act()
    {
        super.act();
    }
    
    /**
     * A method that displays the current health of the player
     */
    public void displayText()
    {
        statusText.setValue(Player.getEnergyPoint());
    }
    
    /**
     * A method that updates the filling of the health bar base on the remaining health of the player
     */
    public void updateBar()
    {
        borderImage = new GreenfootImage(240, 50); // Creates a brown border for the health bar
        remainImage = new GreenfootImage(200, 36); // Creates a flexible green color block to represent health remaining for the player
        backgroundImage = new GreenfootImage(200, 36); // Creates a red color block to represent the background for the health lost  
        borderImage.setColor(new Color(101, 67, 33));
        remainImage.setColor(new Color (154, 254, 255));
        backgroundImage.setColor(new Color (78, 226, 236));
        borderImage.fillRect(0, 0, 214, 50);
        remainImage.fillRect(0, 0, (int)(((double)Player.getEnergyPoint() / Player.getMaxEnergy()) * 200), 36);
        backgroundImage.fillRect(0, 0, 200, 36);
        borderImage.drawImage(backgroundImage, 7, 7); // Draws the red block onto the border
        borderImage.drawImage(remainImage, 7, 7); // Draws the green block onto the border to overlap the red block
        setImage(borderImage);
    }
}