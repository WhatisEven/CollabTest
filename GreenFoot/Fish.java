import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
/**
 * Write a description of class Fish here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fish extends Actor
{
    /**
     * Act - do whatever the Fish wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    FishControl fishControl = new FishControl();
    public int currentSpeed = 0;
    
    public void act()
    {
        // Add your action code here.
        fishControl.startAppearing(this);
        setLocation(currentSpeed += fishControl.speed, fishControl.yLocation);
        fishControl.endFading(this);
        ((MyWorld)getWorld()).reachEnd(this);
    }
}
