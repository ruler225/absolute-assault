//REPLACE ALL STATES SUCH AS WALKING RIGHT OR LEFT WITH THEIR PROPER NUMBERS

import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class Goblin extends Enemy{
  
  /**The state associated with the Goblin attacking*/
  public int intAttackAnimationState;
  /**Counter counting up to intAttackDuration*/
  public int intAttackFrameCounter = 0;
  /**How long it will take the Goblin (in frames) to attack. Default value is the length of the attack animation state*/
  public int intAttackDuration = 0;
  public int intWalkingLeftState = 0;
  public int intWalkingRightState = 0;
  
  public BufferedImage projectileImage;
  
  
  /**Method to update the behaviour of the enemy. It is recommended that this method is called at every frame
    * @return projectile object that may result from the object's behaviour. This value will be null if the enemy
    does not shoot a projectile, and will return a Projectile object if the object shoots a projectile*/
  public Projectile updateBehaviour(Player player){
    
    //Initial projectile is null because enemy hasn't shot anything yet
    Projectile projectile = null;
    
    //If the player is 1500 away from the enemy or less, then it will move left
    if(player.intX > this.intX - 1500 && player.intX < this.intX - 500){
      if(this.getCurrentState() != intWalkingLeftState){
        this.setAnimationState(intWalkingLeftState);
      }
      this.intX -= 5;
    }else if(this.intX > player.intX - 1500 && this.intX < player.intX - 500){ //Same thing as above but moving right instead of left
      if(this.getCurrentState() != intWalkingRightState){
        this.setAnimationState(intWalkingRightState);
      }
      this.intX += 5;
    }else if(player.intX > this.intX - 500){
      if(this.getCurrentState() != this.intAttackAnimationState){
        this.setAnimationState(intAttackAnimationState);
      }
      
      //Counting to the counter so that it takes time for the enemy to attack
      if(this.intAttackFrameCounter <= intAttackDuration){
        //Projectile object is now equal to an actual projectile
        projectile = shootProjectile();
      }else{
        //Increment the counter by 1
        intAttackFrameCounter++;
      }
    }
    return projectile;
  }
  
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
    return new Projectile(this.intX, this.intY + (this.getHeight()/2), 10, 10, 10, 0, currentDirection, false, projectileImage);
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
    public Goblin(int intWidth, int intHeight, int intHealth, int intAttackAnimationState, BufferedImage projectileImage, BufferedImage SpriteSheet[][]){
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
  public Goblin(int intX, int intY, int intWidth, int intHeight, int intHealth, int intAttackAnimationState, BufferedImage projectileImage, BufferedImage SpriteSheet[][]){
    super(intX, intY, intWidth, intHeight, intHealth, SpriteSheet);
    this.intAttackAnimationState = intAttackAnimationState;
    this.intAttackDuration = this.intFrames[intAttackAnimationState];
    this.projectileImage = projectileImage;
  }
  
  
}