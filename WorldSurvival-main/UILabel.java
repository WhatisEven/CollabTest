import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class UILabel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UILabel extends Label
{
    /**
     * A label that is mainly used to display texts on the game's UI objects
     */
    public UILabel(int value, int fontSize)
    {
        super(value, fontSize);
    }
    
    public UILabel(String value, int fontSize)
    {
        super(value, fontSize);
    }
}
