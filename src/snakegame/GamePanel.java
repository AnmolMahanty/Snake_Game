/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snakegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


public class GamePanel extends JPanel implements ActionListener,KeyListener {
    
    private int []snakexlength=new int[750];    
    private int []snakeylength=new int[750];
    private int lengthOfSnake=3;   
    
    private int[] xPos={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
    private int[] yPos={75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};
    private Random random=new Random();  
    private int enemyX,enemyY;
    
    private boolean left=false;
    private boolean right=true;
    private boolean up=false;
    private boolean down=false;
    private int moves=0;
    
    private int score=0;
    private boolean gameOver=false;
    
    private ImageIcon SnakeTitle =new ImageIcon(getClass().getResource("SnakeTitle.jpg"));
    private ImageIcon LeftMouth =new ImageIcon(getClass().getResource("LeftMouth.png"));
    private ImageIcon RightMouth =new ImageIcon(getClass().getResource("RightMouth.png"));
    private ImageIcon UpMouth =new ImageIcon(getClass().getResource("UpMouth.png"));
    private ImageIcon DownMouth =new ImageIcon(getClass().getResource("DownMouth.png"));
    private ImageIcon SnakeImage =new ImageIcon(getClass().getResource("SnakeImage.png"));
    private ImageIcon Enemy =new ImageIcon(getClass().getResource("Enemy.png"));
    private Timer timer ;
    private int delay=150;
   
    
    
    GamePanel()
    {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        timer =new Timer(delay,this);
        timer.start();
        newEnemy();
    
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); 
        g.setColor(Color.white);
        g.drawRect(24,10,851,55);
        g.drawRect(24,74,851,576);
        
        SnakeTitle.paintIcon(this,g,25,11);
       g.setColor(Color.black);
       g.fillRect(25, 75, 850  , 575);
       if(moves==0){
           snakexlength[0]=100;
           snakexlength[1]=75;
           snakexlength[2]=50;
           snakeylength[0]=100;
           snakeylength[1]=100;
           snakeylength[2]=100;
           
       }
       
       
       if(left){
           LeftMouth.paintIcon(this, g,snakexlength[0],snakeylength[0]);          
       }      
       if(right){
           RightMouth.paintIcon(this, g,snakexlength[0],snakeylength[0]);          
       }
       if(up){
           UpMouth.paintIcon(this, g,snakexlength[0],snakeylength[0]);          
       }
       if(down){
           DownMouth.paintIcon(this, g,snakexlength[0],snakeylength[0]);          
       }
       
       for(int i=1;i<lengthOfSnake;i++){
           SnakeImage.paintIcon(this, g, snakexlength[i], snakeylength[i]);
       }
       Enemy.paintIcon(this,g,enemyX,enemyY);
       
       if(gameOver){
           g.setColor(Color.white);
           g.setFont(new Font("Arial",Font.BOLD,50));
           g.drawString("GAME OVER", 300, 300);
           
           g.setFont(new Font("Arial",Font.PLAIN,20));
           g.drawString("Press SPACE To Restart", 320, 350);
            
            }
       g.setColor(Color.white);
       g.setFont(new Font("Arial",Font.PLAIN,14));
       g.drawString("Score :"+score, 750, 30);
       g.drawString("Length :"+lengthOfSnake, 750, 50);
       
       g.dispose();
    }
    @Override
    public void actionPerformed(ActionEvent e){
        for(int i=lengthOfSnake-1;i>0;i--){
            snakexlength[i]=snakexlength[i-1];
            snakeylength[i]=snakeylength[i-1];
        }
       if(left){
           snakexlength[0]=snakexlength[0]-25;
       } 
       if(right){
           snakexlength[0]=snakexlength[0]+25;
       } 
       if(up){
           snakeylength[0]=snakeylength[0]-25;
       } 
       if(down){
           snakeylength[0]=snakeylength[0]+25;
       }
       if(snakexlength[0]>850)
           snakexlength[0]=25;
        if(snakexlength[0]<25)
           snakexlength[0]=850;
         if(snakeylength[0]>625)
           snakeylength[0]=75;
         if(snakeylength[0]<75)
           snakeylength[0]=625; 
        collideWithEnemy();
        collideWithBody();
        
       repaint();
    }
         @Override
         
         public void keyPressed(KeyEvent e){    
        if(e.getKeyCode()==KeyEvent.VK_SPACE){
            restart();
        }
            
            
        if(e.getKeyCode()==KeyEvent.VK_LEFT && (!right)){
           left=true;
           right=false;
           up=false;
           down=false;
           moves++;
                   
       }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT && (!left)){
           left=false;
           right=true;
           up=false;
           down=false;
           moves++;
                   
       }
        if(e.getKeyCode()==KeyEvent.VK_UP && (!down)){
           left=false;
           right=false;
           up=true;
           down=false;
           moves++;
                   
       }
        if(e.getKeyCode()==KeyEvent.VK_DOWN && (!up)){
           left=false;
           right=false;
           up=false;
           down=true;
           moves++;
                   
       }
       
    
    }

  
  

    private void newEnemy() {
        enemyX=xPos[random.nextInt(34)];
        enemyY=yPos[random.nextInt(23)];
        for(int i=lengthOfSnake-1;i>=0;i--){
             if(snakexlength[i]==enemyX && snakeylength[i]==enemyY){
                 newEnemy();
             }
            
        }
     
    }

    private void collideWithEnemy() {
        if(snakexlength[0]==enemyX && snakeylength[0]==enemyY){
            newEnemy();
            lengthOfSnake++;
            score++;
        }
       
    }

    private void collideWithBody() {
        for(int i=lengthOfSnake-1;i>0;i--){
             if(snakexlength[i]==snakexlength[0] && snakeylength[i]==snakeylength[0]){
                 timer.stop();
                 gameOver=true;
             }
            
        }
    
    }

    private void restart() {
        gameOver=false ;
        moves=0;
        score=0;
        lengthOfSnake=3;
        left=false;
        right=true;
        up=false;
        down=false;
        timer.start();
        repaint();
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
         // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
        

}
