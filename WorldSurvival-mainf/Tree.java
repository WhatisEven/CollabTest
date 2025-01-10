import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tree here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tree extends NaturalResources
{
    /**
     * Act - do whatever the Tree wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private GreenfootImage areaImage;
    private int logAmount, stickAmount;
    public Tree()
    {
        rndArea = Greenfoot.getRandomNumber(GameWorld.MAX_TREEAREA - 30) + 30; // Minimum 30 x 30
        logAmount = rndArea / 5; // How much logs will drop as loot when cutting down
        stickAmount = Greenfoot.getRandomNumber(rndArea / 10); // How many sticks will drop as loot when cutting down
        maxBreakPoint = (int)Math.round(rndArea * 0.30); // Maximum health of the tree is 30% of the size of the tree 
        breakPoint = maxBreakPoint;
        areaImage = new GreenfootImage(rndArea, rndArea);
        areaImage.setColor(Color.GREEN);
        areaImage.fillRect(0, 0, rndArea, rndArea);
        setImage(areaImage);
    }
    
    public void act()
    {
        super.act();
    }
    
    /**
     * An overwrite method for possible loot drops from this object
     */
    public void dropLoot()
    {   
        int logRecieve = 0;
        int stickRecieve = 0;
        if (breakPoint > 0) // If the tree is not on its last hit before being chop down
        {
            if (Greenfoot.getRandomNumber(maxBreakPoint) < breakDamage)
            {
                logRecieve = (logAmount / (maxBreakPoint / breakDamage) <= 0) ? 1 : logAmount / (maxBreakPoint / breakDamage); // Minimum log drops from cutting the tree will be 1
                stickRecieve = (stickAmount == 0) ? stickAmount : Greenfoot.getRandomNumber(stickAmount); // Minimum stick drops from cutting down tree can be 0
            }
        }
        else // Drops all the remaning resources 
        {
            logRecieve = logAmount;
            stickRecieve = stickAmount;
        }
        
        if (logRecieve != 0)
            getWorld().addObject(new DropLoot(logRecieve, "Log", this), getX(), getY());
        
        if (stickRecieve != 0)
            getWorld().addObject(new DropLoot(stickRecieve, "Stick", this), getX(), getY());
            
        logAmount -= logRecieve;
        stickAmount -= stickRecieve;
    }
}