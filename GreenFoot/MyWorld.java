import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public boolean overEdge = false;
    Fish fish = new Fish();
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        startGame();
    }
    
    
    public void startGame()
    {
        fish.getImage().setTransparency(0);
        addObject(fish, 0, 0);
    }
    
    public void reachEnd(Actor actor)
    {
        if (actor.getX() >= 599 && !overEdge)
        {
            System.out.println("E");
            overEdge = true;
        }
    }
    
    public void resetFish(Actor actor)
    {
        overEdge = true;
        removeObject(actor);
        if (actor == fish)
        {
            fish.currentSpeed = 0; //Reset the position of the fish
        }
        FishControl fishControl = new FishControl(); //Reset stats of the fish
    }
}
