import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MessageDisplay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MessageDisplay extends Label
{  
    private int actLast, totalLast;
    private int itemTransparency;
    public MessageDisplay(int value)
    {
        super(value, 26);
    }

    public MessageDisplay(String value)
    {
        super(value, 26);
    }

    public MessageDisplay(int value, Color txtColor)
    {
        super(value, 26);
        setFillColor(txtColor);
    }

    public MessageDisplay(String value, Color txtColor)
    {
        super(value, 24);
        setFillColor(txtColor);
    }

    public void addedToWorld(World world)
    {
        itemTransparency = 255;
        totalLast = 300;  // Last 300 acts before the message fade away
        actLast = totalLast; 
    }

    public void act()
    {
        if (actLast > 0)
        {
            int messageOffset = (getWorld().getObjects(MessageDisplay.class).size() - 1 - getWorld().getObjects(MessageDisplay.class).indexOf(this)) * 20;
            setLocation(getWorld().getWidth() / 2, getWorld().getHeight() - getWorld().getHeight() / 6 - messageOffset);
            actLast--;
        }
        else
        {
            if (itemTransparency <= 0) // Checks if the transparency of the image is completely transparent
            {
                 getWorld().removeObject(this); // Remove this messenge after the display is finished
            }
            else
            {
                itemTransparency -= 3;
                getImage().setTransparency(itemTransparency);
            }
        }
    }
}
