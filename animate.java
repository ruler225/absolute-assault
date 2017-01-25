import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class animate extends GameObject{
  /**Holds all frames of the sprite*/
  public BufferedImage sprites[][];
  /**Number of arrays for each state of the object. The index number is the state number*/
  public int intFrames[];
  /**Total number of states*/
  public int intStates = 0;
  /**The current state of the sprite animation*/
  private int intState = 0;
  /**Current frame of the sprite animation*/
  public int intCurrentFrame = 0;  
  /**A counter that counts up to intUpdateFrameCounter, and is incremented by 1 each time updateFrameCounter() is called
    * . When this reaches intUpdateFrameCounter, nextSpriteFrame() is called to update the sprite's frame, and then it is set back to 0.*/
  public int intCurrentCounter = 0;
  /**Specifies how often the sprite will be drawn with the next frame, smaller numbers mean faster sprite animation.*/
  public int intUpdateFrameCounter = 5;
  /**Specifies the fastest speed at which the object can fall*/
  public double dblTerminalVelocity = 50;
  /**Specifies acceleration due to gravity*/
  public double dblGravity = 5;
  /**Current velocity in the y-axis*/
  public double dblCurrentVelocity = 0;
  /**Specifies whether the object is currently airborne or not*/
  public boolean blnAirborne = false;
  
  
  
  
  /**Apply physics and gravity to this object. This method should be called once in every frame*/
  public void updatePhysics(){
    if(blnAirborne){
      this.intY += (int)dblCurrentVelocity;
      this.dblCurrentVelocity += dblGravity;
      if(this.dblCurrentVelocity > this.dblTerminalVelocity){
        this.dblCurrentVelocity = this.dblTerminalVelocity;
      }
    }
  }
  
  
  
  /**Draw the object to a canvas. The picture drawn will depend on the current frame and state of the object
    * @param g canvas to draw object to*/
  public void drawObject(Graphics g){
    g.drawImage(sprites[intState][intCurrentFrame], intX, intY, null);
  }
  
  /**Move to the next frame of the animated sprite. Used internally but can also be called from outside
    * the class*/
  public void nextSpriteFrame(){
    if(intCurrentFrame < intFrames[intState] - 1){
      intCurrentFrame++;
    }else{
      intCurrentFrame = 0;
    }
  }
  
  /**Increment the frame counter by 1. This method should be called once every frame to animate sprites properly*/
  public void updateFrameCounter(){
    if(intCurrentCounter < intUpdateFrameCounter){
      
      intCurrentCounter++;
      
    }else{
      nextSpriteFrame();
      intCurrentCounter = 0;
    }
  }
  
  /**Set a new refresh interval for the new sprite. Smaller numbers mean faster animation
    * @param intNewCounter new refresh variable that will be used for the sprite
    * @see #intUpdateFrameCounter*/
  public void updateFrameCounter(int intNewCounter){
    this.intUpdateFrameCounter = intNewCounter;
  }
  
  /**Set which animation state the sprite is currently animating
    * @param intNewState The integer number for the new animation state which will be animated
    * @throws IllegalArgumentException If the animation state specified does not exist, then an exception will be thrown*/
  public void setAnimationState(int intNewState){
    if(intNewState > this.intStates){
      throw new IllegalArgumentException("Animation state was not loaded and does not exist!");
    }
    this.intState = intNewState;
  }
  
  /**Get the current animation state of the object
    * @return int indicating the current animation state of the object*/
  public int getCurrentState(){
    return intState;
  }
  
  /**Get the number of states that exist for this object
    * @return int indicating the highest index number to be used for animation states*/
  public int getStates(){
    return intStates - 1;
  }
  
  /**Constructs a new animate object which has animated sprite. Each BufferedImage object represents
    * a specific state (ie active, inactive, jumping, etc). There can be many states, or there 
    * can be only one state, depending on the object. The individual frames of the sprite
    * will be saved in the sprites[][] array.
    @param intWidth The width of the object's hit box
    @param intHeight The height of the object's hit box
    @param SpriteSheet Array of BufferedImages for the animated sprite. Each BufferedImage will contain different frames
    these frames must be the specified width and height) and will correspond to different states of the object.
    The first BufferedImage in the array will be the default state of the object.
    * 
    */
  public animate(int intWidth, int intHeight, BufferedImage SpriteSheet[][]){
    super(intWidth, intHeight);
    
    //Save all of the spritesheets' frames
     int intFrameCount = 0;
    this.intStates = SpriteSheet.length;
    this.intFrames = new int[this.intStates];
    for(int i = 0; i < this.intStates;i++){
      intFrameCount = SpriteSheet[i].length;
      for(int j = 0; j < SpriteSheet[i].length;j++){
        if(SpriteSheet[i][j] == null){
          intFrameCount--;
        }
      }
        this.intFrames[i] = intFrameCount;        
      }
    sprites = SpriteSheet;
    }
  
  
  
  /**Constructs a new animate class which has animated sprites at an initial X and Y position
    * @param intX initial X coordinate of the object
    * @param intY initial Y coordinate of the object
    * @see #animate(int, int, BufferedImage[])
    */
  public animate(int intX, int intY, int intWidth, int intHeight, BufferedImage SpriteSheet[][]){
    super(intX, intY, intWidth, intHeight);
    
    //Save all of the spritesheets' frames
    int intFrameCount = 0;
    this.intStates = SpriteSheet.length;
    this.intFrames = new int[this.intStates];
    for(int i = 0; i < this.intStates;i++){
      intFrameCount = SpriteSheet[i].length;
      for(int j = 0; j < SpriteSheet[i].length;j++){
        if(SpriteSheet[i][j] == null){
          intFrameCount--;
        }
      }
        this.intFrames[i] = intFrameCount;        
      }
    sprites = SpriteSheet;
  }
}