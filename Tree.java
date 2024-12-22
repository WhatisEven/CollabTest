import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tree here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tree extends Actor
{
    /**
     * Act - do whatever the Tree wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private GreenfootImage areaImage;
    private int rndArea;
    
    public Tree()
    {
        rndArea = Greenfoot.getRandomNumber(GameWorld.MAX_TREEAREA - 30) + 30; // Minimum 30 x 30
        areaImage = new GreenfootImage(rndArea, rndArea);
        areaImage.setColor(Color.GREEN);
        areaImage.fillRect(0, 0, rndArea, rndArea);
        setImage(areaImage);
    }
    
    public void act()
    {
        
    }
}
