import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import javax.swing.*;

public class menupanel extends JPanel{
  // the background image
  private BufferedImage backgroundImg; 
  
  public menupanel() {
    try{ // load the background images
      backgroundImg = ImageIO.read(new File("../BackGrounds/MenuBackGround.png"));
    }catch(IOException e) {
    }  
  }
  
  /**
   * paint component
   **/
  public void paintComponent(Graphics g) {
    g.clearRect(0, 0, 1280, 720);
    g.drawImage(backgroundImg,0,0,null);
    repaint();
  }
}
