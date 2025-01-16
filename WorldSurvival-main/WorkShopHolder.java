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
    private int holdAct;
    private boolean isPressing;
    private GreenfootImage displayImage;
    private UILabel holdIndicator;
    public WorkShopHolder()
    {
        holdAct = 120; // 120 acts / 2 seconds for user to hold click on this object
        isPressing = false;
        holdIndicator = new UILabel(holdAct, 50);
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
            if (holdAct > 0)
            {
                if (Inventory.hasValidItems(requireResources()))
                {
                    Inventory.removeItems(requireResources());
                }
            }
            holdAct--;
        }
        else 
        {
            holdAct = 120; // Reset the acts when the user releases his mouse
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
                return new String[] {ItemDataManager.getItemData(30, "Log"), ItemDataManager.getItemData(5, "Stick")}; // 30 logs and 5 sticks are required to make the first item
            case 1:
                return new String[] {ItemDataManager.getItemData(100, "Log"), ItemDataManager.getItemData(20, "Stick"), ItemDataManager.getItemData(10, "Rock")}; // 100 logs, 20 sticks and 10 rocks to make the second item
        }
        
        return null;
    }
}
