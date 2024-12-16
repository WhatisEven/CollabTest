import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
/**
 * Write a description of class FishControl here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FishControl extends Actor
{
    Random random = new Random();
    /**
     * Act - do whatever the FishControl wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public int speed = 0;
    public int yLocation = 0;
   // public int transparency = 1;

    public FishControl()
    {
        speed = 1;
        yLocation = 4;
        //transparency = 1;
        //random.nextInt(1, 5);
        //random.nextInt(80, 388);
        
    }

    public void act()
    {
        // Add your action code here.

    }

    public void startAppearing(Actor actor)
    {
        if (actor.getX() <= 100 && actor.getImage().getTransparency() + 4 * speed < 255)
        {
            actor.getImage().setTransparency(actor.getImage().getTransparency() + 4 * speed);
        }
        else if (actor.getX() <= 100 && actor.getImage().getTransparency() < 255)
        {
            actor.getImage().setTransparency(actor.getImage().getTransparency() + 1);
        }
    }

    public void endFading(Actor actor)
    {
        if (actor.getX() >= 550 && actor.getImage().getTransparency() - 5 * speed > 0)
        {
            actor.getImage().setTransparency(actor.getImage().getTransparency() - 4 * speed);
        }
        else if (actor.getX() >= 550 && actor.getImage().getTransparency() > 0)
        {
            actor.getImage().setTransparency(actor.getImage().getTransparency() - 1);
        }
    }
}

