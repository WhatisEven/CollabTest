import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PlayerHungerBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerHungerBar extends PlayerStatusBar
{
    /**
     * Act - do whatever the PlayerHungerBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */    
    public PlayerHungerBar()
    {
        statusText = new Label(Player.getHungerPoint(), 50);     
    }
    
    public void act()
    {
       super.act();
    }
    
    /**
     * A method that displays the current hunger of the player
     */
    public void displayText()
    {
        statusText.setValue(Player.getHungerPoint());
    }
    
    /**
     * A method that updates the filling of the hunger bar base on the remaining hunger points of the player
     */
    public void updateBar()
    {
        borderImage = new GreenfootImage(240, 50); // Creates a brown border for the hunger bar
        remainImage = new GreenfootImage(200, 36); // Creates a flexible light brown color block to represent hunger remaining for the player
        backgroundImage = new GreenfootImage(200, 36); // Creates a light grey color block to represent the background for the hunger lost  
        borderImage.setColor(new Color (101, 67, 33));
        remainImage.setColor(new Color (181, 101, 29));
        backgroundImage.setColor(new Color (77, 77, 79));
        borderImage.fillRect(0, 0, 214, 50);
        remainImage.fillRect(0, 0, (int)(((double)Player.getHungerPoint() / Player.getMaxHunger()) * 200), 36);
        backgroundImage.fillRect(0, 0, 200, 36);
        borderImage.drawImage(backgroundImage, 7, 7); // Draws the light grey block onto the border
        borderImage.drawImage(remainImage, 7, 7); // Draws the light brown block onto the border to overlap the light grey block
        setImage(borderImage);
    }
}
