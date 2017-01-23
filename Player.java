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
  public double dblJumpVelocity = -100;
  /**The current direction that the player is facing. This can be Direction.Right, Direction.Left,
    * Direction.Up, or Direction.Down*/
  public Direction currentDirection = Direction.Right;
  private boolean blnPrimary = true;
  
  /**Make the player jump*/
  public void jump(){
    if(!blnAirborne){
      this.dblCurrentVelocity = this.dblJumpVelocity;
      this.blnAirborne = true;
    }
  }
  
  /**Returns true or false depending on whether or not the player is the primary player in this instance of the game
    * @return true if the player is primary, false if the player is from another machine on the network*/
  public boolean isPrimary(){
    return this.blnPrimary;
  }
  
  
  
  /**Method that returns a new projectile coming from the player. It will be shot in whichever direction the player
    * is currently facing.
    * @param intWidth the width of the projectile
    * @param intHeight, the height of the projectile
    * @param projectileImage image to be used for the projectile
    * @return the projectile shot from the player
    */
  public Projectile shootWeapon(int intWidth, int intHeight, int intVelocity, int intDamage, BufferedImage projectileImage){
    return new Projectile(intX, intY, intWidth, intHeight, intVelocity, intDamage, currentDirection, true, projectileImage);
  }
  
  /**Set the direction that the player is currently facing. You should only set this to left or right
    * @param direction The direction to make the player face. Only recommended to do Direction.Left or
    * Direction.Right 
    */
  public void setDirection(Direction direction){
    this.currentDirection = direction;
  }
  
  
  /**Constructs a new Player. Each BufferedImage object represents
    * a specific state (ie active, inactive, jumping, etc). There can be many states, or there 
    * can be only one state, depending on the object. The individual frames of the sprite
    * will be saved in the sprites[][] array.
    @param intWidth The width of the object's hit box
    @param intHeight The height of the object's hit box
    @param SpriteSheet Array of BufferedImages for the animated sprite. Each BufferedImage will contain different frames
    these frames must be the specified width and height) and will correspond to different states of the object.
    The first BufferedImage in the array will be the default state of the object.
    @param blnPrimary Set this to true if the player is player 1 and will be controlled by the player, false
    if the player is not the primary player, and is controlled by a server or client (player 2)*/
  public Player(int intWidth, int intHeight, BufferedImage SpriteSheet[][], boolean blnPrimary){
    super(intWidth, intHeight, SpriteSheet);
    this.blnPrimary = blnPrimary;
  }
  /**Constructs a new Player at a specific location. 
    * @param intX initial X coordinate of object
    * @param intY initial Y coordinate of object
    * @see #Player(int, int, BufferedImage[], boolean)
    */
  public Player(int intX, int intY, int intWidth, int intHeight, BufferedImage SpriteSheet[][], boolean blnPrimary){
    super(intX, intY, intWidth, intHeight, SpriteSheet);
    this.blnPrimary = blnPrimary;
  }
  
}