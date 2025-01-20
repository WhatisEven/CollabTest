import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Arrays;
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
    private int rowNum, columnNum;
    private String itemName;
    private boolean isEquipSlot; // A boolean that determines whether this inventory slot is a equip slot
    public ItemHolder(String itemData, boolean equipSlot)
    {
        isEquipSlot = equipSlot;
        imageHolder = new GreenfootImage(70, 70);
        imageHolder.setColor(Color.BLUE);
        imageHolder.fillRect(0, 0, 70, 70);
         if (itemData != null)
        {
            itemAmount = ItemDataManager.getItemAmount(itemData); // Gets number of items
            itemName = ItemDataManager.getItemName(itemData); // Gets name of item
            
            imageHolder.setColor(Color.GRAY);

            imageHolder.drawImage(ImageManager.getImage(itemName, 1.4),imageHolder.getWidth() / 2 - ImageManager.getImage(itemName, 1.4).getWidth() / 2, imageHolder.getHeight() / 2 - ImageManager.getImage(itemName, 1.4).getHeight() / 2);
            imageHolder.setFont(new Font("Comic Sans MS", 20)); // Changing the font style and size
            if (itemAmount > 1) // If there is more than 1 of the same item stacked
                imageHolder.drawString(String.valueOf(itemAmount), 0, 70); // Drawing the font onto the image
        }
        setImage(imageHolder);
    }
    
    public ItemHolder(String itemData, int rowNum, int columnNum, boolean equipSlot)
    {
        this(itemData, equipSlot);
        this.rowNum = rowNum;
        this.columnNum = columnNum;
    }
    
    public void act()
    {
        if (Greenfoot.mouseClicked(this))
        {
             if (Arrays.asList(ItemDataManager.allTools).contains(itemName)) // Checks if the item that the user trying to equip is a valid tool
             {
                equipTool(ItemDataManager.getItemData(itemAmount, itemName), rowNum, columnNum, isEquipSlot);
             }
        }
    }
}