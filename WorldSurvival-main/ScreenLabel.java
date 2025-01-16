import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


/**
 * A Label class that allows you to display a textual value on screen.
 * 
 * The Label is an actor, so you will need to create it, and then add it to the world
 * in Greenfoot.  If you keep a reference to the Label then you can change the text it
 * displays.  
 *
 * @author Amjad Altadmri 
 * @version 1.1
 */
public class ScreenLabel extends Label
{
    /**
     * A label that is mainly used in the main screen of the game
     */
    public ScreenLabel(int value, int fontSize)
    {
        super(value, fontSize);
    }
    
    public ScreenLabel(String value, int fontSize)
    {
        super(value, fontSize);
    }
}