import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class ObjectGenerator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ObjectManager extends Actor
{
    /**
     * Act - do whatever the ObjectGenerator wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private static World world;
    
    private static ArrayList<ArrayList<Actor>> allObjects = new ArrayList<ArrayList<Actor>>();
    
    public ObjectManager(World world)
    {
        this.world = world;
    }
    
    public void spawnObjects()
    {
        if (allObjects.size() == 0) // Checks if there is no objects in the world right now (Indicate that the map was newely made)
            generateObjects(); // Generates objects for this map
            
        for (int i = 0; i < allObjects.size(); i++)
        {
            for (int f = 0; f < allObjects.get(i).size(); f++)
            {
                if (allObjects.get(i).get(f) != null)
                    world.addObject(allObjects.get(i).get(f), GameWorld.worldGrids[i][f].getX(), GameWorld.worldGrids[i][f].getY()); // Adding the object to the matching world grid 
            }
        }
    }
    
    /**
     * A method that generate random objects for the map
     */
    private void generateObjects()
    {
        ArrayList<Actor> tempList;
        for (int i = 0; i < GameWorld.worldGrids.length; i++)
        {
            tempList = new ArrayList<Actor>();
            for (int f = 0; f < GameWorld.worldGrids[i].length; f++)
            {
                tempList.add(randomObject());
            }
            allObjects.add(tempList);
        }
    }
    
    /**
     * A method that generates and returns random object 
     */
    private Actor randomObject()
    {
        if (Greenfoot.getRandomNumber(100) < 70)
        {
            return new Tree();
        }
        return null;
    }
}
