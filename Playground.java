import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;

public class Playground {
  public static void main(String Args[]) {
    MyFrame frame = new MyFrame();
  }
}
class MyFrame extends JFrame implements KeyListener {
  MyPanel panel;
 
 MyFrame(){
  panel = new MyPanel(1000, 1000);
  
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
  this.add(panel);
  this.pack();
  this.setLocationRelativeTo(null);
  this.addKeyListener(this);
  this.setVisible(true);
  this.toFront();
  this.requestFocus();

 }
  @Override
	public void keyTyped(KeyEvent e) {
  }
 @Override
	public void keyPressed(KeyEvent e) {
    //System.out.println(e.getKeyCode());
  }
  @Override
	public void keyReleased(KeyEvent e) {
  }
}
class MyPanel extends JPanel implements ActionListener, MouseListener {
  PhysicsWorld world;
  MyPanel(int inW, int inH) {
    this.setPreferredSize(new Dimension(inW,inH));
    this.addMouseListener(this);
    world = new PhysicsWorld();
    //Start the timer and program
    Timer frameT = new Timer(25, this);
    frameT.start();
  }
  
  public void paint(Graphics g) {
    Graphics2D g2D = (Graphics2D) g;
    //Background
    g2D.setColor(Color.black);
    g2D.fillRect(0, 0, this.getWidth(), this.getHeight());
    //grid
    g2D.setStroke(new BasicStroke(2));
    g2D.setColor(Color.gray);
    for (int i = 100; i < this.getWidth(); i+= 100) {
      g2D.drawLine(i, 0, i, this.getHeight());
    }
    for (int i = 100; i < this.getHeight(); i+= 100) {
      g2D.drawLine(0, i, this.getWidth(), i);
    }
    //Floor
    g2D.setStroke(new BasicStroke(5));
    g2D.setColor(Color.white);
    g2D.drawLine(0, this.getHeight() - 50, this.getWidth(), this.getHeight() - 50);
    world.drawWorld(g2D);
  }

  @Override//Action performed by timer (frame)
  public void actionPerformed(ActionEvent e) {
    world.advanceWorld();
    repaint();
  }
  //Mouse
  @Override
  public void mouseClicked(MouseEvent e) {
  }
  @Override
  public void mouseEntered(MouseEvent e) {
  }
  @Override
  public void mouseExited(MouseEvent e) {
  }
  @Override
  public void mousePressed(MouseEvent e) {
  }
  @Override
  public void mouseReleased(MouseEvent e) {
  }
}
