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
    public static final int MAX_ROCKAREA = 150;
    public static final int MAX_ITEMSTACK = 99; // Maximum 99 items for one stack 
    
    private static final int imageOffsetX = 700;
    private static final int imageOffsetY = 450; 
    public static GreenfootImage MAP_IMAGE = new GreenfootImage("Img.png"); 
    
    public static WorldGrid[][] worldGrids = new WorldGrid[MAP_IMAGE.getWidth() / MAX_GRIDLENGTH][MAP_IMAGE.getHeight() / MAX_GRIDLENGTH];
    public static final Class[] objectClass = {Tree.class, Rock.class, HorizontalBorder.class, VerticalBorder.class}; // An array that identifies all the physical objects within the world
    
    private static Player player = new Player();
    private static PlayerEnergyBar energyBar = new PlayerEnergyBar();
    private static PlayerHungerBar hungerBar = new PlayerHungerBar();
    private ObjectManager objectManager = new ObjectManager(this);
    private InventoryUI inventoryUI;
    private WorkShopUI workShop;
    
    private boolean UIOpening;
    
    private static final Class excludeActors[] = {Label.class, PlayerEnergyBar.class, PlayerHungerBar.class, Player.class, InventoryUI.class, ItemHolder.class, WorkShopUI.class, WorkShopHolder.class}; // An array that contains all classes will not be affected from movements of the player
    
    public GameWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(WORLD_WIDTH, WORLD_HEIGHT, 1, false); 
        initializeUI();
        addBorder(); // Adding the borders to the world
        loadGrid(); 
        setBackground(MAP_IMAGE);
        loadPlayer();
        loadUI();
        setPaintOrder(WorkShop.class, Inventory.class, Label.class, PlayerEnergyBar.class, PlayerHungerBar.class, Border.class,  DropLoot.class, Player.class, NaturalResources.class); 
    }
    
    public void act()
    {
       keyPressAction();
    }
    
    public void initializeUI()
    {
        inventoryUI = new InventoryUI();
        workShop = new WorkShopUI();
        UIOpening = false;
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
    
    /**
     * A method that opens the player's inventory
     */
    public void addInventory()
    {
        addObject(inventoryUI, WORLD_WIDTH / 2, WORLD_HEIGHT / 2);
        inventoryUI.openInventory();
    }
    
    /**
     * A method that closes the player's inventory
     */
    public void removeInventory()
    {
        inventoryUI.closeInventory();
        removeObject(inventoryUI);
    }
    
    /**
     * A method that opens the workshop menu
     */
    public void addWorkShop()
    {
        addObject(workShop, WORLD_WIDTH / 2, WORLD_HEIGHT / 2);
        workShop.openWorkShop();
    }
    
    /**
     * A method that closes the workshop menu
     */
    public void closeWorkShop()
    {
        workShop.closeWorkShop();
        removeObject(workShop);
    }
    
    /**
     * A method that acts upon user's key presses 
     * Press e to open inventory
     * Press c to open workshop menu
     */
    private void keyPressAction()
    {
        String pressingKey = Greenfoot.getKey(); // Get the key that the user is currently pressing
        if (pressingKey != null) // If there is a key being pressed at this instance
        {
            if (pressingKey.equals("e"))
            {
                if (!inventoryUI.isOpen()) // Checks if the inventory is already opened
                {
                    if (!UIOpening)
                    {
                        addInventory();
                        UIOpening = true;
                    }
                }
                else
                {
                    removeInventory();
                    UIOpening = false;
                }
            }
            else if (pressingKey.equals("c"))
            {
                if (!workShop.isOpen()) // Checks if the workshop menu is already opened
                {
                    if (!UIOpening) // Checks if there is already a UI opened
                    {
                        addWorkShop();
                        UIOpening = true;
                    }
                }
                else
                {
                    closeWorkShop();
                    UIOpening = false;
                }
            }
            pressingKey = null;
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
    
    private void loadUI()
    {
        addObject(energyBar, 130, getHeight() - 200);
        addObject(hungerBar, 130, getHeight() - 100);
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
    
    public boolean getUIOpening()
    {
        return UIOpening;
    }
    
    /**
     * A method that sets the player's spawning location when first enters the world
     */
    public static void spawnLocation()
    {
        WorldGrid rndGrid = WorldGrid.getEmptyGrid();
        Player.setMapOffsetX(-1 * Greenfoot.getRandomNumber((rndGrid.getImage().getWidth()) + rndGrid.getX() - rndGrid.getImage().getWidth() / 2) + imageOffsetX);
        Player.setMapOffsetY(-1 * (Greenfoot.getRandomNumber(rndGrid.getImage().getHeight()) + rndGrid.getY() - rndGrid.getImage().getHeight() / 2) + imageOffsetY);
    }
}