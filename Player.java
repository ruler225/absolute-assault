import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class Player extends animate{
  
  /**The number of lives remaining the player currently has*/
  public int intLives = 2;
  /**The maximum number of lives the player should have*/
  public int intMaxLives = 2;
  /**Weapon that the player currently has. 0 is default weapon*/
  public int intWeapon = 0;
  /**The initial velocity that the player jumps at. The default velocity is -100*/
  public int intJumpVelocity = -100;
  
  public void jump(){
    if(!blnAirborne){
      this.intCurrentVelocity = this.intJumpVelocity;
    }
  }
  
  
  /**See animate class for constructor detail*/
  public Player(int intWidth, int intHeight, BufferedImage SpriteSheet[]){
    super(intWidth, intHeight, SpriteSheet);
  }
  
  public Player(int intX, int intY, int intWidth, int intHeight, BufferedImage SpriteSheet[]){
    super(intX, intY, intWidth, intHeight, SpriteSheet);
  }
  
}