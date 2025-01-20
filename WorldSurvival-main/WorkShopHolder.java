import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ShopHolder here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WorkShopHolder extends WorkShop
{
    /**
     * Act - do whatever the ShopHolder wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private static GreenfootImage backgroundImage;
    private String itemData;
    private int holdAct;
    private boolean isPressing;
    private GreenfootImage displayImage;
    private UILabel holdIndicator;
    public WorkShopHolder(String itemData)
    {
        this.itemData = itemData; // Assigning this specific holder to have its unique item data
        isPressing = false;
        holdAct = 120; // 120 acts / 2 seconds for user to hold click on this object
        holdIndicator = new UILabel("", 50);
        holdIndicator.setFillColor(Color.GREEN);
        backgroundImage = new GreenfootImage(HOLDER_SIZE, HOLDER_SIZE);
        displayImage = new GreenfootImage((int)(HOLDER_SIZE / 1.25), (int)(HOLDER_SIZE / 1.25)); 
        displayImage.fillRect(0, 0, displayImage.getWidth(), displayImage.getHeight());
        backgroundImage.setColor(Color.YELLOW);
        displayImage.setColor(Color.WHITE);
        backgroundImage.fillRect(0, 0, HOLDER_SIZE, HOLDER_SIZE);
        backgroundImage.drawImage(displayImage, backgroundImage.getWidth() / 2 - displayImage.getWidth() / 2, backgroundImage.getHeight() / 2 - displayImage.getHeight() / 2);
        setImage(backgroundImage);
    }
    
    public void addedToWorld(World world)
    {
        world.addObject(holdIndicator, getX(), getY());
    }
    
    public void act()
    {
        
        if (Greenfoot.mousePressed(this)) // Checks if the user has pressed on this object
        {
            isPressing = true;
        }
        else
        {
            if (Greenfoot.mouseClicked(this)) // Checks if the user has let go of the click of this object
            {
                isPressing = false;
            }
        }
            
        if (isPressing) // Check if the user has hold click on this object
        {
            if (Inventory.hasValidItems(requireResources()))
            {
                int itemAmount = ItemDataManager.getItemAmount(itemData);
                String itemName = ItemDataManager.getItemName(itemData);
                
                if (holdAct <= 0) // Check if the user has waited for crafting
                {
                    if (Inventory.getEmptySlot() > itemAmount) // Checks if the player's inventory can hold all the items that is trying to craft
                    {
                        Inventory.removeItems(requireResources());
                        holdIndicator.setValue("");
                
                        Inventory.addItem(itemAmount, itemName); // Add this item into the player's inventory after crafted
                    }
                    else
                    {
                        getWorld().addObject(new MessageDisplay("Item cannot be crafted and no resources is consumed due to insufficient inventory spaces", Color.RED), -1000, -1000); // Display a message to indicate that there is not enough inventory spaces
                    }
                    isPressing = false; // Reset the holding state of the player's mouse 
                }
                else
                {
                    holdAct--;
                    holdIndicator.setValue(Math.round(holdAct / 60.0) + "s"); // Display how long it take to finish crafting this item
                }
            }
            else
            {
                getWorld().addObject(new MessageDisplay("Not enough resources required to craft this item", Color.RED), -1000, -1000); // Display a message that there is not enough resources to craft the desire item
                isPressing = false;
            }
        }
        else 
        {
            holdAct = 120; // Reset the acts when the user releases his mouse
            holdIndicator.setValue(""); // Reset the crafting indicator to be invisible to the user
        }
    }
    
    /**
     * A method that returns all the resources requied in making this item
     */
    private String[] requireResources()
    {
        switch (getWorld().getObjects(WorkShopHolder.class).indexOf(this))
        {
            case 0: 
                // 100 logs, 30 sticks and 5 rocks are required to make the first item (starter hoe)
                return new String[] {ItemDataManager.getItemData(100, "Log"), ItemDataManager.getItemData(30, "Stick"), ItemDataManager.getItemData(5, "Rock")};
            case 1:
                // 110 logs, 15 sticks and 18 rocks are required to make the second item (starter pickaxe)
                return new String[] {ItemDataManager.getItemData(110, "Log"), ItemDataManager.getItemData(15, "Stick"), ItemDataManager.getItemData(18, "Rock")};
            case 2:
                // 150 logs, 20 sticks and 10 rocks are required to make the third item (starter axe)
                return new String[] {ItemDataManager.getItemData(150, "Log"), ItemDataManager.getItemData(20, "Stick"), ItemDataManager.getItemData(10, "Rock")};
        }
        
        return null;
    }
}
