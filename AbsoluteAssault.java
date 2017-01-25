//The Grand to do List

//Jeremy : fix gun pointing down to still in appropriate animation states
//Jeremy: HUD that matches the screen and levels
//Jeremy: An instruction picture 
// level 2 and 3 csvs (just adjust positions of platforms and number of mobs (stats))
//Jump and crouch  (max height? max distance? need to move forward with jump)
//OBJECT COLLISIOn and PHYSICS (falling off screen)
//enemy and hitboxes / projectile interaction
//COOPERATIVE
//enemy and player interaction
//make the screen scrollable (up and sides in all directions?)
//item switching
//pickup hearts.... health + and -


//note
//from crouch to jump, play a frame of the standing animation before transitioning into jump <-----


// .... etc

//no music, we have no time



import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import javax.swing.*;

public class AbsoluteAssault implements ActionListener, KeyListener{
  
  //Properties
  public JFrame theFrame;
  
  public JButton singlePlayerButton;
  
  public JButton cooperativeButton;
  
  public JButton helpButton;
  
  public menupanel menu;
  
  public gamepanel game;
  
  public JTextArea chatbox;
  
  //Methods
  public void actionPerformed(ActionEvent evt) {    
    if(evt.getSource() == singlePlayerButton ){ 
      // Single Player mode start!
      startSinglePlayer();
    }    
  }  
  
  public void keyReleased(KeyEvent evt){    
  }
  
  public void keyPressed(KeyEvent evt){    
  }
  
  public void keyTyped(KeyEvent evt){    
  }
  
  //Constructor
  public AbsoluteAssault(){
    theFrame = new JFrame("Absolute Assault");
    theFrame.setResizable(false);
    theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    menu = new menupanel();
    menu.setLayout(null);
    menu.setPreferredSize(new Dimension(1280, 720));
    
    singlePlayerButton = new JButton("Single Player");
    singlePlayerButton.setSize(450, 50);
    singlePlayerButton.setLocation(800, 200);
    singlePlayerButton.addActionListener(this);
    
    cooperativeButton = new JButton("Cooperative");
    cooperativeButton.setSize(450, 50);
    cooperativeButton.setLocation(800, 300);
    cooperativeButton.addActionListener(this);
    
    helpButton = new JButton("Help");
    helpButton.setSize(450, 50);
    helpButton.setLocation(800, 400);
    helpButton.addActionListener(this);
    
    menu.add(singlePlayerButton);
    menu.add(cooperativeButton);
    menu.add(helpButton);
    menu.addKeyListener(this);
    
    theFrame.setContentPane(menu);
    
    theFrame.pack();
    
    theFrame.setVisible(true);
    
    menu.repaint();    
    
  }
  
  /**
   * start single player
   * */
  private void startSinglePlayer(){
      
    game = new gamepanel();
    game.setPreferredSize(new Dimension(1280, 720));
    game.setLayout(null);
    
    chatbox = new JTextArea();
    chatbox.setSize(200,100);
    chatbox.setLocation(1170, 510);
    game.add(chatbox);
    
    theFrame.setContentPane(game);        
    theFrame.pack(); 
  }
  //main method
  public static void main (String args []){    
    AbsoluteAssault game = new AbsoluteAssault();
  }  
}


