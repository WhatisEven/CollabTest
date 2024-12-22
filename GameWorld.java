import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameWorld extends World
{
    public static final int WORLD_WIDTH = 1400;
    public static final int WORLD_HEIGHT = 900;
    
    public static final int MAX_GRIDLENGTH = 200;
    public static final int MAX_TREEAREA = 200;
    public static GreenfootImage MAP_IMAGE = new GreenfootImage("Img.png"); 
    
    public static WorldGrid[][] worldGrids = new WorldGrid[MAP_IMAGE.getWidth() / MAX_GRIDLENGTH][MAP_IMAGE.getHeight() / MAX_GRIDLENGTH];
    
    private static Player player = new Player();
    private ObjectManager objectManager = new ObjectManager(this);
    private ItemHolder itemHolder;
    private InventoryUI inventoryUI;
    
    private static final Class excludeActors[] = {Player.class, InventoryUI.class, ItemHolder.class};
    
    private static int spawningX = Greenfoot.getRandomNumber(WORLD_WIDTH - player.getImage().getWidth() / 2) + player.getImage().getWidth() / 2;
    private static int spawningY = Greenfoot.getRandomNumber(WORLD_HEIGHT - player.getImage().getHeight() / 2) + player.getImage().getHeight() / 2;
    
    
    public GameWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(WORLD_WIDTH, WORLD_HEIGHT, 1, false); 
        initializeUI();
        addBorder(); // Adding the borders to the world
        loadGrid(); 
        setBackground(MAP_IMAGE);
        loadPlayer();
        setPaintOrder(Inventory.class, Border.class, Player.class); 
    }
    
    public void act()
    {
       keyPressAction();
    }
    
    public void initializeUI()
    {
        itemHolder = new ItemHolder();
        inventoryUI = new InventoryUI();
    }
    
    /**
     * A methodd that moves the background base on the given x and y offset
     */
    public static void moveBackground(World world, int mapOffsetX, int mapOffsetY)
    {
        world.setBackground(new GreenfootImage(MAP_IMAGE)); // Reset the background image
        world.getBackground().drawImage(MAP_IMAGE, mapOffsetX, mapOffsetY);
    }
    
    /**
     * A method that moves all appropriate objects in the world from given x and y direction of speed
     */
    public static void moveObjects(World world, int speedX, int speedY)
    {
        for (Actor actor : world.getObjects(Actor.class))
        {
            if (!Arrays.asList(excludeActors).contains(actor.getClass())) // Checks for specific objects that do not requrie to move when the character is moving
                actor.setLocation(actor.getX() + speedX, actor.getY() + speedY);
        }
    }
    
    public void addInventory()
    {
        addObject(itemHolder, 0, 0);
        addObject(inventoryUI, WORLD_WIDTH / 2, WORLD_HEIGHT / 2);
    }
    
    public void removeInventory()
    {
        Inventory.closeInventory();
        removeObject(itemHolder);
        removeObject(inventoryUI);
    }
    
    private void keyPressAction()
    {
        String pressingKey = Greenfoot.getKey(); // Get the key that the user is currently pressing
        if (pressingKey != null) // If there is a key being pressed at this instance
        {
            if (pressingKey.equals("e"))
            {
                if (!Inventory.isOpen())
                {
                    addInventory();
                }
                else
                {
                    removeInventory();
                }
                pressingKey = null;
            }
        }
    }
    
    /**
     * A method that adds borders to the world to prevent player from exiting the world
     */
    private void addBorder()
    {
        addObject(new HorizontalBorder(), MAP_IMAGE.getWidth() / 2, 0);
        addObject(new HorizontalBorder(), MAP_IMAGE.getWidth() / 2, MAP_IMAGE.getHeight());
        addObject(new VerticalBorder(), 0, MAP_IMAGE.getHeight() / 2);
        addObject(new VerticalBorder(), MAP_IMAGE.getWidth(), MAP_IMAGE.getHeight() / 2);
    }
    
    /**
     * A method that loads the last position and the player itself into the world
     */
    private void loadPlayer()
    {
        addObject(player,  WORLD_WIDTH / 2, WORLD_HEIGHT / 2); // Adds to the center of the screen
        moveBackground(this, Player.getMapOffsetX(), Player.getMapOffsetY());
        moveObjects(this, Player.getMapOffsetX(), Player.getMapOffsetY());
    }
    
    private void loadGrid()
    {
        for (int i = 0; i < worldGrids.length; i++)
        {
            for (int f = 0; f < worldGrids[i].length; f++)
            {
                worldGrids[i][f] = new WorldGrid(); // Adding the grid to the 2D array
                addObject(worldGrids[i][f], f * MAX_GRIDLENGTH + MAX_GRIDLENGTH / 2, i * MAX_GRIDLENGTH + MAX_GRIDLENGTH / 2);
            }
        }
        
        objectManager.spawnObjects();
    }  
}