import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class Wizard extends Enemy{
  /**The state associated with the Wizard attacking*/
  public int intAttackAnimationState;
  /**How long it will take the Wizard (in frames) to attack. Default value is the length of the attack animation state*/
  public int intAttackDuration = 0;
  /**Image to be used for the projectiles shot by the Wizard*/
  public BufferedImage projectileImage;
  public int intFacingLeftState = 0;
  public int intFacingRightState = 0;
  
  /**Method to update the behaviour of the enemy. It is recommended that this method is called at every frame
    * @param player Player for enemy to aim for
    * @return projectile object that may result from the object's behaviour. This value will be null if the enemy
    does not shoot a projectile, and will return a Projectile object if the object shoots a projectile*/
  public Projectile updateBehaviour(Player player){
    Projectile projectile = null;
    
    if(player.intX > this.intX - 800){
    if(this.getCurrentState() != intFacingLeftState){
        this.setAnimationState(intFacingLeftState);
      }
    this.currentDirection = Direction.Left;
    projectile = shootProjectile();
    }else if(this.intX > player.intX - 800){
       if(this.getCurrentState() != intFacingRightState){
        this.setAnimationState(intFacingRightState);
      }
       this.currentDirection = Direction.Right;
       projectile = shootProjectile();
    }
    return projectile;
  }
  
  /**Method to update the behaviour of the enemy when there are two players. When using this method, the enemy
    * will aim for the closest player
    * @param player1 Player object for player 1
    * @param player2 Player object for player 2
    * @return projectile object that may result from the object's behaviour. This value will be null if the enemy
    does not shoot a projectile, and will return a Projectile object if the object shoots a projectile
    */
  public Projectile updateBehaviour(Player player1, Player player2){
    Projectile projectile = null;
    int intDistance1 = player1.intX - this.intX;
    int intDistance2 = player2.intX - this.intX;
    if(intDistance1 < 0){
      intDistance1 = -intDistance1;
    }
    if(intDistance2 < 0){
      intDistance2 = -intDistance2;
    }
    
    if(intDistance1 < intDistance2){
      projectile = updateBehaviour(player1);
    }else{
      projectile = updateBehaviour(player2);
    }
    return projectile;
  }
  
  public Projectile shootProjectile(){
    return new Projectile(this.intX, this.intY + (this.getHeight()/2), 10, 10, 20, 0, currentDirection, false, projectileImage);
  }
  
  
  /**Constructs a new Goblin object with a specific width and height. Each BufferedImage object represents
    * a specific state (ie active, inactive, jumping, etc). There can be many states, or there 
    * can be only one state, depending on the object. The individual frames of the sprite
    * will be saved in the sprites[][] array.
    * @param intWidth the width of the enemy
    * @param intHeight the height of the enemy
    * @param intHealth the amount of health that the enemy will have
    * @param intAttackAnimationState the animation state corresponding with the Goblin while it is attacking.
    * @param projectileImage image to use for the projectile shot out by the Goblin
    * @param SpriteSheet Array of BufferedImages for the animated sprite. Each BufferedImage will contain different frames
    these frames must be the specified width and height) and will correspond to different states of the object.
    The first BufferedImage in the array will be the default state of the object.
    */
  public Wizard(int intWidth, int intHeight, int intHealth, int intAttackAnimationState, BufferedImage projectileImage, BufferedImage SpriteSheet[][]){
    super(intWidth, intHeight, intHealth, SpriteSheet);
    this.intAttackAnimationState = intAttackAnimationState;
    this.intAttackDuration = this.intFrames[intAttackAnimationState];
    this.projectileImage = projectileImage;
  }
  
  /**Constructs a new Goblin object with a specific width and height at a specific location
    *@param intX initial X coordinate of the Enemy
    * @param intY initial Y coordinate of the Enemy
    * @see #Goblin(int, int, int, BufferedImage[][])
    */
  public Wizard(int intX, int intY, int intWidth, int intHeight, int intHealth, int intAttackAnimationState, BufferedImage projectileImage, BufferedImage SpriteSheet[][]){
    super(intX, intY, intWidth, intHeight, intHealth, SpriteSheet);
    this.intAttackAnimationState = intAttackAnimationState;
    this.intAttackDuration = this.intFrames[intAttackAnimationState];
    this.projectileImage = projectileImage;
  }
  
  
}