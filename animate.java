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
  public int intCurrentCounter = 0;
  public int intUpdateFrameCounter = 5;
  
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
    }
  }
  
  public void updateFrameCounter(int intNewCounter){
    this.intUpdateFrameCounter = intNewCounter;
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