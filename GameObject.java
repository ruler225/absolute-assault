import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

//Base class for all objects in the game
public abstract class GameObject{
  public int intX;
  public int intY;
  
  //Size properties are private because once the object is created, the size should not change.
  //The size can be obtained using the getWidth and getHeight methods
  private int intSizeX;
  private int intSizeY;
  
  
  //Set the position of the object
  public void setPosition(int intX, int intY){
    this.intX = intX;
    this.intY = intY;
  }
  
  public abstract void drawObject(Graphics g);
  
  //Returns a boolean of true or false if the object collides with another
  public boolean checkCollision(GameObject object){
    int intWidth = object.getWidth();
    int intHeight = object.getHeight();
    
    //Check if the two objects collide
    if (this.intX >= (object.intX - this.getWidth()) && this.intX <= (object.intX + this.getWidth())){
      if(this.intY >= (object.intY - this.getHeight()) && this.intY <= (object.intY + this.getHeight())){
        return true;
      }
    }
    
    return false;
  }
  
  //Return the width of the object
  public int getWidth(){
    return intSizeX;
  }
  
  //Return the height of the object
  public int getHeight(){
    return intSizeY;
  }
 
  //Constructor to construct object with a certain size
  public GameObject(int intWidth, int intHeight){
    this.intSizeX = intWidth;
    this.intSizeY = intHeight;
  }
  
  //Constructor to construct object with a certain size at an initial position
  public GameObject(int intX, int intY, int intHeight, int intWidth){
    this.intSizeX = intWidth;
    this.intSizeY = intHeight;
    this.intX = intX;
    this.intY = intY;
  }
  
  
}