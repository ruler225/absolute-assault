import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

//This class will represent all inanimate objects in the game
public class inanimate extends GameObject{
  public BufferedImage image;
  
  public void drawObject(Graphics g){
    g.drawImage(image, intX, intY, null);
  }
  
  public inanimate(int intWidth, int intHeight, BufferedImage objectImage){
    super(intWidth, intHeight);
    this.image = objectImage;
  }
  
  public inanimate(int intX, int intY, int intHeight, int intWidth, BufferedImage objectImage){
    super(intX, intY, intHeight, intWidth);
    this.image = objectImage;
  }
  
}