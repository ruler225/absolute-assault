import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class Platform extends inanimate{
  
  public Platform(int intWidth, int intHeight, BufferedImage objectImage){
    super(intWidth, intHeight, objectImage);
  }
  
  public Platform(int intX, int intY, int intWidth, int intHeight, BufferedImage objectImage){
    super(intX, intY, intWidth, intHeight, objectImage);
  }
  
}
