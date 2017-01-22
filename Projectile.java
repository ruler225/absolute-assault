import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class Projectile extends inanimate{
  /**Direction of the projectile. Can be Direction.Right, Direction.Left, Direction.Up, Direction.Down*/
  public Direction projectileDirection;
  /**true if the Projectile came from a player, false if it came from an enemy*/
  public boolean blnFriendly;
  public int intProjectileSpeed = 10;
  
  /**Moves the Projectile based on its speed and direction. It is recommended for this method to be called at every frame*/
  public void moveProjectile(){
    if(projectileDirection == Direction.Right){
      this.intX += this.intProjectileSpeed;
    }else if(projectileDirection == Direction.Left){
      this.intX -= this.intProjectileSpeed;
    }else if(projectileDirection == Direction.Up){
      this.intY -= this.intProjectileSpeed;
    }else if(projectileDirection == Direction.Down){
      this.intY += this.intProjectileSpeed;
    }
  }
  
  
  
  /**Construct a new Projectile flying from an initial position in a specific direction
    *@param intX initial X coordinate of the Projectile
    * @param intY initial Y coordinate of the Projectile
    * @param intWidth the width of the projectile
    * @param intHeight the height of the projectile
    * @param objectImage image that the Projectile will use
    * @param direction Direction of the projectile (either Direction.Left, Direction.Right, Direction.Down, Direction.Up)
    * @param blnisFriendly If this is true, then the Projectile should not harm the players, if this is false, then the Projectile
    * comes from an enemy and should hurt players
    */
  public Projectile(int intX, int intY, int intWidth, int intHeight, BufferedImage objectImage, Direction direction, boolean blnisFriendly){
    super(intX, intY, intWidth, intHeight, objectImage);
    projectileDirection = direction;
    this.blnFriendly = blnisFriendly;
  }
}