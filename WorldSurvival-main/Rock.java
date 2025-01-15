import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Rock here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rock extends NaturalResources
{
    /**
     * Act - do whatever the Rock wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    private GreenfootImage rockImage;
    private int rockAmount;
    public Rock()
    {
        rndArea = Greenfoot.getRandomNumber(GameWorld.MAX_ROCKAREA - 10) + 10; // Minimum 10 x 10
        maxBreakPoint = (int)Math.round(rndArea * 0.36); // The health point of a rock is 36% of its size
        rockAmount = rndArea / 8; // Total rock amounts 
        breakPoint = maxBreakPoint;
        rockImage = new GreenfootImage(rndArea, rndArea);
        rockImage.setColor(Color.GRAY);
        rockImage.fillRect(0, 0, rndArea, rndArea);
        setImage(rockImage);
    }
    
    public void act()
    {
        super.act();  
    }
    
    public void dropLoot()
    {
        int rockRecieve = 0;
        
        if (breakPoint > 0)
        {
            if (Greenfoot.getRandomNumber(maxBreakPoint) < breakDamage)
            {
                rockRecieve = (rockAmount / (maxBreakPoint / breakDamage) <= 0) ? 1 : rockAmount / (maxBreakPoint / breakDamage); // Minimum rock drops from mining rocks will be 1
            }
        }
        else
        {
            rockRecieve = rockAmount;
        }
        
        if (rockRecieve != 0)
            getWorld().addObject(new DropLoot(rockRecieve, "Rock", this), getX(), getY());
        
        rockAmount -= rockRecieve;
    }
}
