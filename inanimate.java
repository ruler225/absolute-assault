import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

//This class will represent all inanimate objects in the game
public class inanimate extends GameObject{
  /**Single frame sprite of this object*/
  public BufferedImage image;
  
  /**Draw object on a canvas
    *@param g canvas to draw object on 
    */
  public void drawObject(Graphics g){
    g.drawImage(image, intX, intY, null);
  }
  
  /**Constructs a new inanimate object with a specified width and height, and image
    *@param intWidth width of the object
    *@param intHeight height of the object
    * @param objectImage image of the object. It must be the same width and height as specified in the parameters
    * used while creating the object
    * @throws IllegalArgumentException if the dimensions of the image don't match the width and height specified
    * when creating the object, the constructor will throw an IllegalArgumentException
    */
  public inanimate(int intWidth, int intHeight, BufferedImage objectImage){
    super(intWidth, intHeight);
    if(objectImage.getWidth() != intWidth || objectImage.getHeight() != intHeight){
      throw new IllegalArgumentException("The dimensions of the image don't match the width and height of the object");
    }
    this.image = objectImage;
  }
  
  /**Construct a new inanimate object with a specified width, height, and image, and at a specific location
    * @param intX initial X coordinate of the object
    * @param intY initial Y coordinate of the object
    * @see #inanimate(int, int, BufferedImage)
    */
  public inanimate(int intX, int intY, int intWidth, int intHeight, BufferedImage objectImage){
    super(intX, intY, intHeight, intWidth);
    this.image = objectImage;
  }
  
}