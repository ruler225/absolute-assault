import java.awt.*;
import javax.imageio.*;
import java.io.*;

//Base class for all objects in the game
public abstract class GameObject{
  public Image image;
  public int intX;
  public int intY;
  
  //Size properties are private because once the object is created, the size should not change.
  //The size can be obtained using the getWidth and getHeight methods
  private int intSizeX;
  private int intSizeY;
  
  
  public void setPosition(int intX, int intY){
    this.intX = intX;
    this.intY = intY;
  }
  
  public int getWidth(){
    return intSizeX;
  }
  
  public int getHeight(){
    return intSizeY;
  }

  //Constructor to construct object with a certain size, and a certain image
  public GameObject(int intWidth, int intHeight, String strFileName){
    this.intSizeX = intWidth;
    this.intSizeY = intHeight;
    
    try{
      this.image = ImageIO.read(new File(strFileName));
    }catch(IOException e){
    }
    image.getWidth(image);
  }
  
  //Constructor to construct object with a certain size at an initial position with 
  public GameObject(int intX, int intY, int intHeight, int intWidth, String strFileName){
    this.intSizeX = intWidth;
    this.intSizeY = intHeight;
    this.intX = intX;
    this.intY = intY;
    
    try{
      this.image = ImageIO.read(new File(strFileName));
    }catch(IOException e){
    }
  }
  
  
}