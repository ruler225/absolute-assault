import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class animate extends GameObject{
  /**Holds all frames of the sprite*/
  public BufferedImage sprites[][];
  public int intFrames[];
  public int intStates = 0;
  /**The current state of the sprite animation*/
  public int intState = 0;
  /**Current frame of the sprite animation*/
  public int intCurrentFrame = 0;  
  /**A counter that counts up to intUpdateFrameCounter, and is incremented by 1 each time updateFrameCounter() is called
    * . When this reaches intUpdateFrameCounter, nextSpriteFrame() is called to update the sprite's frame, and then it is set back to 0.*/
  public int intCurrentCounter = 0;
  /**Specifies how often the sprite will be drawn with the next frame, smaller numbers mean faster sprite animation.*/
  public int intUpdateFrameCounter = 5;
  /**Specifies the fastest speed at which the object can fall*/
  public int intTerminalVelocity = 50;
  /**Specifies acceleration due to gravity*/
  public int intGravity = 5;
  /**Current velocity in the y-axis*/
  public int intCurrentVelocity = 0;
  /**Specifies whether the object is currently airborne or not*/
  public boolean blnAirborne = false;
  
  
  
  public void updatePhysics(){
    if(blnAirborne){
      this.intY += intCurrentVelocity;
      this.intCurrentVelocity += intGravity;
      if(this.intCurrentVelocity > this.intTerminalVelocity){
        this.intCurrentVelocity = this.intTerminalVelocity;
      }
    }
  }
  
  public void drawObject(Graphics g){
    g.drawImage(sprites[intState][intCurrentFrame], intX, intY, null);
  }
  
  public void nextSpriteFrame(){
    if(intCurrentFrame < intFrames[intState]){
      intCurrentFrame++;
    }else{
      intCurrentFrame = 0;
    }
    
  }
  public void updateFrameCounter(){
    if(intCurrentCounter < intUpdateFrameCounter){
     
        intCurrentCounter++;
             
    }else{
      nextSpriteFrame();
      intCurrentCounter = 0;
    }
  }
  
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
  
  /**Constructs a new animate class which has animated sprite. Each BufferedImage object represents
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
  public animate(int intWidth, int intHeight, BufferedImage SpriteSheet[]){
    super(intWidth, intHeight);
    
    //Save all of the spritesheets' frames
    this.intStates = SpriteSheet.length;
    int intSSWidth[] = new int[this.intStates];
    this.intFrames = new int[this.intStates];
    for(int i = 0; i < this.intStates;i++){
      intSSWidth[i] = SpriteSheet[i].getWidth();
      this.intFrames[i] = intSSWidth[i]/intWidth;
      
      for(int j = 0; j <this.intFrames[i];j++){
        this.sprites[i][j] = SpriteSheet[i].getSubimage(j*intWidth, 0, intWidth, intHeight);
      }
      
    }
  }
  public animate(int intX, int intY, int intWidth, int intHeight, BufferedImage SpriteSheet[]){
    super(intX, intY, intWidth, intHeight);
    
    //Save all of the spritesheets' frames
    this.intStates = SpriteSheet.length;
    int intSSWidth[] = new int[this.intStates];
    this.intFrames = new int[this.intStates];
    for(int i = 0; i < this.intStates;i++){
      intSSWidth[i] = SpriteSheet[i].getWidth();
      this.intFrames[i] = intSSWidth[i]/intWidth;
      
      
      for(int j = 0; j < this.intFrames[i];j++){
        this.sprites[i][j] = SpriteSheet[i].getSubimage(j*intWidth, 0, intWidth, intHeight);
      }
      
    }
  }
}