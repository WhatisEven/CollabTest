import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ItemHolders here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ItemHolder extends Inventory
{
    /**
     * Act - do whatever the ItemHolders wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private GreenfootImage imageHolder;
    private int itemAmount;
    private String itemName;
    
    public ItemHolder(String itemData)
    {
        imageHolder = new GreenfootImage(70, 70);
        imageHolder.setColor(Color.BLUE);
        imageHolder.fillRect(0, 0, 70, 70);
        
        if (itemData != null)
        {
            itemAmount = ItemDataManager.getItemAmount(itemData); // Gets number of items
            itemName = ItemDataManager.getItemName(itemData); // Gets name of item
            
            imageHolder.setColor(new Color(229, 228, 226));
            imageHolder.setFont(new Font("Comic Sans MS", 20)); // Changing the font style and size
            imageHolder.drawString(String.valueOf(itemAmount), 0, 70); // Drawing the font onto the image
            imageHolder.setFont(new Font("Comic Sans MS", 25));
            imageHolder.drawString(itemName, 10 / itemName.length(), 35);
        }
        setImage(imageHolder);
    }
}
