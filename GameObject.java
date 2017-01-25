import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

enum Direction{Right, Left, Up, Down}

//Base class for all objects in the game
public abstract class GameObject{
  /**X coordinate of the object*/
  public int intX;
  /**Y coordinate of the object*/
  public int intY;
  
  //Size properties are private because once the object is created, the size should not change.
  //The size can be obtained using the getWidth and getHeight methods
  private int intSizeX;
  private int intSizeY;
  
  
  /**Set the position of the object
    * @param intX New X coordinate of the object
    * @param intY New Y coordinate of the object
    */
  public void setPosition(int intX, int intY){
    this.intX = intX;
    this.intY = intY;
  }
  
  /**Draw the object on a canvas
    * @param g Canvas to draw object on*/
  public abstract void drawObject(Graphics g);
  
  /**Checks if object collides with another Gameobject
    *@param object Object to check for a collision with this one 
    *@return true if the object has collided with the GameObject specified, false if it hasn't collided
    */
  public boolean checkCollision(GameObject object){
    int intWidth = object.getWidth();
    int intHeight = object.getHeight();
    
    //Check if the two objects collide
    if ((this.intX >= (object.intX - this.getWidth()) && this.intX <= (object.intX + this.getWidth())) ||
      (object.intX >= (this.intX - object.getWidth()) && object.intX <= (this.intX + object.getWidth()))){
      if((this.intY >= (object.intY - this.getHeight()) && this.intY <= (object.intY + this.getHeight())) ||
        (object.intY >= (this.intY - object.getHeight()) && object.intY <= (this.intY + object.getHeight()))){
        return true;
      }
    }    
    return false;
  }
  
   /**Checks if object collides with another Gameobject in the X axis
    *@param object Object to check for a collision with this one 
    *@return true if the object has collided with the GameObject in the X axis, false if it hasn't collided
    */
  public boolean checkCollisionX(GameObject object){
    if ((this.intX >= (object.intX - this.getWidth()) && this.intX <= (object.intX + this.getWidth())) ||
      (object.intX >= (this.intX - object.getWidth()) && object.intX <= (this.intX + object.getWidth()))){
     return true; 
    }
    return false;
  }
  
   /**Checks if object collides with another Gameobject in the Y axis
    *@param object Object to check for a collision with this one 
    *@return true if the object has collided with the GameObject in the Y axis, false if it hasn't collided
    */
  public boolean checkCollisionY(GameObject object){
     if((this.intY >= (object.intY - this.getHeight()) && this.intY <= (object.intY + this.getHeight())) ||
        (object.intY >= (this.intY - object.getHeight()) && object.intY <= (this.intY + object.getHeight()))){
       return true;
     }
     return false;
  }
  
  /**Gets the width of the object
    * @return the width of the object in pixels
    */
  public int getWidth(){
    return intSizeX;
  }
  
  /**Gets the height of the object
    *@return the height of the object in pixels 
    */
  public int getHeight(){
    return intSizeY;
  }
 
  /**Constructs a new GameObject with a specified width and height
    *@param intWidth the width of the object
    *@param intHeight the height of the object
    */
  public GameObject(int intWidth, int intHeight){
    this.intSizeX = intWidth;
    this.intSizeY = intHeight;
  }
  
  /**Constructs a new GameObject with a specified size at a specific location
    * @param intX the initial X coordinate of the object
    * @param intY the initial Y coordinate of the object    
    * @see #GameObject(int, int)
    */
  public GameObject(int intX, int intY, int intHeight, int intWidth){
    this.intSizeX = intWidth;
    this.intSizeY = intHeight;
    this.intX = intX;
    this.intY = intY;
  }
  
  
}