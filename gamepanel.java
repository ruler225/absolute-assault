
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import javax.swing.*;

//Define the states for the player
enum PlayerStates{StandRight,AttackLeft,WalkRight,WalkLeft,StandLeft,AttackRight,JumpRight,JumpLeft,CrouchLeft,CrouchRight}

public class gamepanel extends JPanel implements KeyListener, ActionListener{
  //the background image
  private BufferedImage backgroundImg;
  //the map tiles
  private inanimate[] tiles;
  // the player object
  private Player player;
  //the tile images
  private static BufferedImage gr = null;
  private static BufferedImage  le= null;
  private static BufferedImage re = null;
  private static BufferedImage r1b = null;
  private static BufferedImage r1 = null;
  private static BufferedImage m = null;
  private static BufferedImage mb = null;
  private static BufferedImage l1 = null;
  private static BufferedImage l1b = null;
  private static BufferedImage b = null;
  
  private static BufferedImage screen = null;
  
  private static Graphics buffer = null;
    
  private static final int IMAGE_SIZE = 100;
  private static final int MAX_FRAMES = 4;
  private static final int ANIMATION_STATES =10;
  
  private static boolean blnNetworked = false;
    
  JTextArea area = new JTextArea();
  // the timer
  Timer thetimer;
  //Constructor
  public gamepanel() {
    area.setSize(300, 100);
    area.setLocation(0, 0);
    this.add(area);
    //read all the images Files
    screen = new BufferedImage(1800, 720, BufferedImage.TYPE_INT_ARGB);
    buffer = screen.createGraphics();
    BufferedReader br = null;
    FileReader fr = null;
    try {
      backgroundImg = ImageIO.read(new File("../BackGrounds/sky.png"));
      r1 = ImageIO.read(new File("../Map/island/R1.png"));
      r1b = ImageIO.read(new File("../Map/island/R1B.png"));
      l1 = ImageIO.read(new File("../Map/island/L1.png"));
      l1b = ImageIO.read(new File("../Map/island/L1B.png"));
      m = ImageIO.read(new File("../Map/island/M.png"));
      mb = ImageIO.read(new File("../Map/island/MB.png"));
      le = ImageIO.read(new File("../Map/grassplatform/le.png"));
      re = ImageIO.read(new File("../Map/grassplatform/re.png"));
      gr = ImageIO.read(new File("../Map/grassplatform/grass.png"));
      b  = ImageIO.read(new File("../Map/grassplatform/black.png"));
      
      //read csv file and create tiles
      //tile positions
      int x = 0; 
      int y = 0; 
      String line;      
      inanimate current;
      int intCounter = 0;      
      //read the CSV file
      fr = new FileReader("../Map/Level1.csv");
      br = new BufferedReader(fr);      
      // The (current) map 6X13. Change if map changes
      tiles = new inanimate[168];  //Change if map dimension changes
      while ((line = br.readLine()) != null) {
        x = 0; // each row start from x-position 0
        String str[] = line.split(","); // parses the string array
        for (int j = 0; j < str.length; j++) {  // the (current) map has 13 columns                    
          current = createInanimate(str[j], x, y);   //create an inAnimate object       
          x = x + IMAGE_SIZE;               //Get the x-position for the next tile
          tiles[intCounter] = current;               //
          intCounter ++;          
        }
        y = y + IMAGE_SIZE; // get the y-position of the next tile
      }      
      // Read images for the player
      readImgForPlayers();      
    } catch (IOException e1) {    
      e1.printStackTrace();
    } finally{
      try{
         br.close();
         fr.close();
      }catch(IOException e){     
      }
    }
    this.addKeyListener(this);   
    // There are 1000 ms in a second.  Therefore 1000/60 = 60 frames per second
    thetimer = new Timer(1000/60, this);
    // Start the timer
    thetimer.start();
  }  
  
  /**
   * make the panel to be focusable so that it can respond to listeners
   **/
  public void addNotify(){
    super.addNotify();
    requestFocus();    
  }
  
  /**
   * create inanimate object with image and positions based on letters in CSV file
   **/
  private inanimate createInanimate(String code, int x, int y) {
    BufferedImage image = null;    
    code = code.toLowerCase(); // to avoid typing error    
    switch (code) {
      case "gr":
        image = gr;
        break;
      case "le":        
        image = le;
        break;
      case "re":
        image = re;
        break;        
      case "l1":        
        image = l1;
        break;
      case"l1b":
        image = l1b;
        break;
      case "r1":
        image = r1;
        break;
      case"r1b":        
        image = r1b;
        break;
      case "m":
        image = m;
        break;
      case "mb":
        image = mb;
        break;        
      case "b":
        image = b;
        break; 
      default:
        image = null;
        break;
    }
    
    if (image != null)
      //if there is an image...
      return new inanimate(x, y, image.getWidth(), image.getHeight(), image);
    else {
      //Most likely an empty space, will not return an inanimate object
      return null;
    }    
  } 
  
  /**
   * Paint
   **/
  public void paintComponent(Graphics g) {
   // g.clearRect(0, 0, 1280, 720);   //draws a rectangle
    buffer.clearRect(0, 0, 1800, 720);
    //draw the background
    buffer.drawImage(backgroundImg,0,0,null); 
    
    //draw map tiles
    if(tiles != null){
      inanimate tile;      
      for (int j = 0; j < tiles.length; j++) {
        tile = tiles[j];
        if (tile != null) {  // to handle null cases
          tile.drawObject(buffer);          
        }
      }
    }
    
    if (player != null) {
      /*  for(;;){
       if(player.checkCollision(tiles[6][13])){
       player.blnAirborne = false;
       }
       }
       
       */       
      checkCollisions();
      movePlayers();
      player.updateFrameCounter();
      player.updatePhysics();    
      player.drawObject(buffer);
      
      if(player.intX > screen.getWidth() - 1180){
        player.intX -= 5;
      }
      if(player.intX < 100){
        player.intX += 5;
      }
      
      
      
      g.drawImage(screen.getSubimage(player.intX - 100, 0, 1280, 720), 0, 0, null);
     
    }
  }  
   
  private void movePlayers(){
    if(player.blnMovingRight){
      player.intX += 5;
    }else if(player.blnMovingLeft){
      player.intX -= 5;
    }
  }
  
  private void checkCollisions(){
    boolean blnCollisionOccurred = false;
   for(int i = 0;i < tiles.length;i++){
     //Make sure an inanimate object actually exists before checking collision
     
     if(tiles[i] != null){
      if(player.checkCollision(tiles[i]) && player.dblCurrentVelocity >=0){
        if(player.intY <= tiles[i].intY - player.getHeight()){
        player.blnAirborne = false;
        blnCollisionOccurred = true;
        player.intY = tiles[i].intY - player.getHeight();
        }else if(player.intY >= tiles[i].intY - player.getHeight()){
          if(player.intX < tiles[i].intX){
            player.intX -= 5;
          }else if(player.intX > tiles[i].intX){
            player.intX += 5;
          }
        }
      }
    }
   }
   
   if(!blnCollisionOccurred){
     player.blnAirborne = true;
   }
   
  }
  
  
  
  @Override // override the super class 
    
  public void keyPressed(KeyEvent e) {    
    int keyCode = e.getKeyCode();
    int currentState = player.getCurrentState();
    switch( keyCode ) { 
      case KeyEvent.VK_F1:        
        //Shoot attack      
        int nextState = PlayerStates.AttackRight.ordinal();
//        player.shootProjectile(intWidth, intHeight, )
        player.currentDirection = Direction.Right;
        if(currentState != PlayerStates.AttackLeft.ordinal() && currentState != PlayerStates.AttackRight.ordinal()){
          if(currentState == PlayerStates.StandLeft.ordinal() || currentState == PlayerStates.WalkLeft.ordinal()||currentState == PlayerStates.JumpLeft.ordinal()){
            nextState = PlayerStates.AttackLeft.ordinal();
            player.currentDirection = Direction.Left;
          }          
          player.setAnimationState(nextState);
        }
        break;
      case KeyEvent.VK_UP:
        // handle jump        
        // to do jump with collision checking
        player.setAnimationState(PlayerStates.JumpRight.ordinal());        
        player.jump();        
        break;
      case KeyEvent.VK_DOWN:
       player.setAnimationState(PlayerStates.CrouchLeft.ordinal());   
       // player.intY ++;
      
        // handle down 
        break;
      case KeyEvent.VK_LEFT:
        // handle left        
        player.currentDirection = Direction.Left;
        player.setAnimationState(PlayerStates.WalkLeft.ordinal());   
        player.blnMovingLeft = true;
        break;
      case KeyEvent.VK_RIGHT :
        // update state of player
        player.currentDirection = Direction.Right;
        player.setAnimationState(PlayerStates.WalkRight.ordinal());   
        player.blnMovingRight = true;
        break;
    }    
  }
  
  public void actionPerformed(ActionEvent evt){
    if(evt.getSource() == thetimer){      
      repaint();
    }    
  }
  
  @Override
  public void keyReleased(KeyEvent evt) {
    int intKeyCode = evt.getKeyCode();
    int currentState = player.getCurrentState();
    int nextState = PlayerStates.StandRight.ordinal();
    if(currentState == PlayerStates.WalkLeft.ordinal() ||currentState == PlayerStates.JumpLeft.ordinal()||currentState == PlayerStates.AttackLeft.ordinal() ){
      nextState = PlayerStates.StandLeft.ordinal(); 
    }
    player.setAnimationState(nextState); 
     switch(intKeyCode){
    case KeyEvent.VK_LEFT:
      player.blnMovingLeft = false;
    case KeyEvent.VK_RIGHT:
      player.blnMovingRight = false;
  }
  }
  
 
  
  
  @Override
  public void keyTyped(KeyEvent evt) {
    
  }
  
  /**
   * Character states
   **/
  public void readImgForPlayers() {
    BufferedImage spriteSheet[][] = new BufferedImage[ANIMATION_STATES][MAX_FRAMES];
    File folder = new File("../Character/holdingdefault/standright");
    listFilesForFolder(folder, spriteSheet[0]); 
    
    folder = new File("../Character/holdingdefault/attackLeft");
    listFilesForFolder(folder, spriteSheet[1]);
    
    folder = new File("../Character/holdingdefault/walkright");
    listFilesForFolder(folder, spriteSheet[2]);
    
    folder = new File("../Character/holdingdefault/walkleft");
    listFilesForFolder(folder, spriteSheet[3]);
    
    folder = new File("../Character/holdingdefault/standleft");
    listFilesForFolder(folder, spriteSheet[4]);
    
    folder = new File("../Character/holdingdefault/attackright");
    listFilesForFolder(folder, spriteSheet[5]);
    
    folder = new File("../Character/holdingdefault/jumpright");
    listFilesForFolder(folder, spriteSheet[6]);
    
    folder = new File("../Character/holdingdefault/jumpleft");
    listFilesForFolder(folder, spriteSheet[7]);
    
   folder = new File("../Character/holdingdefault/crouchleft");
    listFilesForFolder(folder, spriteSheet[8]);
     folder = new File("../Character/holdingdefault/crouchright");
    listFilesForFolder(folder, spriteSheet[9]);
    
    //create player
    player = new Player(200,200,45,70,spriteSheet,true);    
  }
  
  /**
   * read all files in the folder and create the image
   **/
  public void listFilesForFolder(final File folder, BufferedImage imagesForState[]) {
    int i = 0;
    for (final File fileEntry : folder.listFiles()) {      
      // load image.....
      try {
        BufferedImage image = ImageIO.read(fileEntry);
        imagesForState[i] = image;
        i ++;
      }catch(IOException e1){
         e1.printStackTrace();
      } 
    }    
  } 
}