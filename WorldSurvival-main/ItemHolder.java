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
    
    public ItemHolder(String combineData)
    {
        imageHolder = new GreenfootImage(70, 70);
        imageHolder.setColor(Color.BLUE);
        imageHolder.fillRect(0, 0, 70, 70);
        
        if (combineData != null)
        {
            itemAmount = Integer.parseInt(combineData.substring(0, combineData.indexOf(dataSeperator))); // Gets number of items
            itemName = combineData.substring(combineData.indexOf(dataSeperator) + 1); // Gets name of item
            
            imageHolder.setColor(new Color(229, 228, 226));
            imageHolder.setFont(new Font("Comic Sans MS", 20)); // Changing the font style and size
            imageHolder.drawString(String.valueOf(itemAmount), 0, 70); // Drawing the font onto the image
            imageHolder.setFont(new Font("Comic Sans MS", 25));
            imageHolder.drawString(itemName, 10 / itemName.length(), 35);
        }
        setImage(imageHolder);
    }
}
