/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author stahc1596
 */

public class ClownFace extends JComponent implements MouseMotionListener{

    // Height and Width of our game
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000)/desiredFPS;
    Color lightBlue = new Color(165, 221, 240);
    int circle = 325;
    int ypoints1 = 350;
    int ypoints2 = 350;
    int ypoints3 = 285;
    int[] ypoints = {ypoints1, ypoints2, ypoints3};
    int[] xpoints = {250, 300, 275};
    
    int mouseX = 0;
    int mouseY = 0;
    
    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g)
    {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);
        
        // GAME DRAWING GOES HERE 
        g.setColor(Color.black);
        //Background
        g.fillRect(0, 0, 800, 600);
        g.setColor(Color.RED);
        //Hair
        g.fillOval(150, 100, 150, 150);
        g.fillOval(500, 100, 150, 150);
        g.fillOval(112, 200, 150, 150);
        g.fillOval(538, 200, 150, 150);
        g.setColor(Color.WHITE);
        //Head
        g.fillOval(200, 100, 400, 400);
        g.setColor(Color.BLUE);
        //Eyeliner
        g.fillOval(265, 190, 120, 120);
        g.fillOval(415, 190, 120, 120);
        g.setColor(Color.WHITE);
        //Eyes
        g.fillOval(275, 200, 100, 100);
        g.fillOval(425, 200, 100, 100);
        g.setColor(Color.BLACK);
        //Pupils
        g.fillOval(300, 225, 50, 50);
        g.fillOval(450, 225, 50, 50);
        g.setColor(Color.BLUE);
        //Lipliner
        g.fillArc(300, 360, 200, 170, 345, 210);
        g.setColor(Color.BLACK);
        //Lips
        g.fillArc(320, 375, 160, 120, 345, 210);
        g.setColor(Color.BLACK);
        //Nose outline
        g.drawOval(325, 255, 150, 150);
        g.setColor(lightBlue);
        //tear drops
        g.fillOval(250, circle, 50, 50);
        g.fillPolygon(xpoints, ypoints, 3);
        g.setColor(Color.BLUE);
        //Sadness in eyes with eyeliner
        g.fillArc(225, 115, 120, 120, 255, 85);
        g.fillArc(455, 115, 120, 120, 200, 85);
        g.setColor(Color.WHITE);
        //Cover parts of the sadness in eyes with eyeliner so it's not a giant shape.
        g.fillArc(225, 115, 110, 110, 255, 85);
        g.fillArc(465, 115, 110, 110, 200, 85);
        g.setColor(Color.RED);
        //Nose
        g.fillOval(325, 255, 150, 150);
                
        g.fillOval(mouseX - 25, mouseY - 25, 50, 50);
        
        // GAME DRAWING ENDS HERE
    }
    
    
    // The main game loop
    // In here is where all the logic for my game will go
    public void run()
    {
        // Used to keep track of time used to draw and update the game
        // This is used to limit the framerate later on
        long startTime;
        long deltaTime;
        
        // the main game loop section
        // game will end if you set done = false;
        boolean done = false; 
        while(!done)
        {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();
            
            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE 
            if(circle != 375 && ypoints1 != 400){
                circle++;
                ypoints[0]++;
                ypoints[1]++;
                ypoints[2]++;
            }else{
                circle = 325;
                ypoints[0] = 350;
                ypoints[1] = 350;
                ypoints[2] = 285;
            }

            // GAME LOGIC ENDS HERE 
            
            // update the drawing (calls paintComponent)
            repaint();
            
            
            
            // SLOWS DOWN THE GAME BASED ON THE FRAMERATE ABOVE
            // USING SOME SIMPLE MATH
            deltaTime = System.currentTimeMillis() - startTime;
            if(deltaTime > desiredTime)
            {
                //took too much time, don't wait
            }else
            {
                try
                {
                    Thread.sleep(desiredTime - deltaTime);
                }catch(Exception e){};
            }
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creates a windows to show my game
        JFrame frame = new JFrame("My Game");
       
        // creates an instance of my game
        ClownFace game = new ClownFace();
        // sets the size of my game
        game.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        // adds the game to the window
        frame.add(game);
         
        // sets some options and size of the window automatically
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        // shows the window to the user
        frame.setVisible(true);
        game.addMouseMotionListener(game);
        // starts my game loop
        game.run();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        
    }
}
