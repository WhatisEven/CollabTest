import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class WorldGrid here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WorldGrid extends Actor
{
    /**
     * Act - do whatever the WorldGrid wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private static GreenfootImage gridImage;
    private static final boolean showGrid = false; // A boolean that determines whether to show the grid of the map
    private static ArrayList<WorldGrid> emptyGrids = new ArrayList<WorldGrid>();
    public WorldGrid()
    {
        gridImage = new GreenfootImage(GameWorld.MAX_GRIDLENGTH, GameWorld.MAX_GRIDLENGTH);
        gridImage.setColor(new Color (Greenfoot.getRandomNumber(255), Greenfoot.getRandomNumber(255), Greenfoot.getRandomNumber(255)));
        gridImage.fillRect(0, 0, GameWorld.MAX_GRIDLENGTH, GameWorld.MAX_GRIDLENGTH);
        setImage(gridImage);
        
        if (showGrid)
            gridImage.setTransparency(40);
        else
            gridImage.setTransparency(0);
    }
    
    /**
     * A method that adds the empty grid into an arraylist of empty grids
     */
    public static void addEmptyGrid(WorldGrid emptyGrid)
    {
        emptyGrids.add(emptyGrid);
    }
    
    /**
     * A getter method that gets a random empty grid from the stored arraylist of empty grids
     */
    public static WorldGrid getEmptyGrid()
    {
        return emptyGrids.get(Greenfoot.getRandomNumber(emptyGrids.size())); // Returns a random empty grid from the empty grids arraylist
    }
    
    /**
     * A method that returns whether the given grid
     */
    public static boolean isEmpty(WorldGrid checkGrid)
    {
        return emptyGrids.contains(checkGrid);
    }
}
