
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;

public class Mainmenu implements ActionListener, MouseListener,MouseMotionListener{
  
  BufferedImage MenuBackGround = null;

  
  //properties
  
  public JFrame theFrame;
  public JButton theButton1;
  public JButton theButton2;
  public JButton theButton3;
  public JPanel thePanel;
  Timer thetimer;
  
  
  //Methods
  public void actionPerformed(ActionEvent evt){
    
    
  }
  
  public void mouseExited(MouseEvent evt){
    
    
    
  }
  
  
  public void mouseEntered(MouseEvent evt){
    
    
  }
  
  public void mouseReleased(MouseEvent evt){
    
    
    
  }
  
  public void mousePressed(MouseEvent evt){
    
  }
  
  public void mouseClicked(MouseEvent evt){
    
    
  }
  
  public void mouseMoved(MouseEvent evt){
    
    
  }
  
  public void mouseDragged(MouseEvent evt){
    
  }
  
  public Mainmenu(){
       try {
   MenuBackGround = ImageIO.read(new File("MenuBackGround.png"));
  } catch (IOException e) {
 }
 
    
    theButton1 = new JButton("Single Player");
    theButton1.setSize(250,50);
    theButton1.setLocation(1000,300);
    
    
    
    theButton2 = new JButton("Cooperative");
    
    theButton2.setSize(250,50);
    theButton2.setLocation(1000,400);
    theButton3 = new JButton("Help");
    
    theButton3.setSize(250,50);
    theButton3.setLocation(1000,500);
    theFrame = new JFrame("Absolute Assault");
    theFrame.setLocation(0,0);
    theFrame.setResizable(false);
    theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    thePanel = new JPanel();
    thePanel.add(theButton1);
    thePanel.add(theButton2);
    thePanel.add(theButton3);
   
   
   
    thePanel.addMouseMotionListener(this);
    thePanel.setLayout(null);
    thePanel.addMouseListener(this);
    thePanel.setPreferredSize(new Dimension(1280, 720));
    theFrame.setContentPane(thePanel);
    theFrame.pack();
    theFrame.setVisible(true);
    
    
 // Graphics.drawImage(Image img,int x, int ,ImageObserver observer);  
    
  }
    public static void main(String[] args){
    Mainmenu mm = new Mainmenu();
  }
  
}