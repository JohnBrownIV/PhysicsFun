import java.util.ArrayList;
import java.awt.*;

class PhysicsWorld {
  final double GRAVITY = 5;
  final double AIRRES = 0;
  ArrayList<physicsObj> world;
  PhysicsWorld() {
    world = new ArrayList<physicsObj>();
    world.add(new rectangle(new coord(70,900), 100, 10, 100));
    world.get(0).anchor = true;
    world.add(new rectangle(new coord(0,100), 10, 10, 10));
  }
  public void advanceWorld() {
    //Move everything and apply gravity
    for (int i = 0; i < world.size(); i++) {
      if (!world.get(i).anchor) {
        world.get(i).advance(GRAVITY);
      }
    }
    //Check collisions
    for (int i = 0; i < world.size(); i++) {
      for (int y = i; y < world.size(); y++) {
        if (world.get(y).inside(world.get(i).getCollisionCoords())) {
          
        }
      }
    }
  }
  public void drawWorld(Graphics2D g2D) {
    for (int i = 0; i < world.size(); i++) {
      world.get(i).draw(g2D);
    }
    vector tempV = new vector(GRAVITY, 90);
    tempV.draw(new coord(100,100), g2D);
  }
}
abstract class physicsObj {
  coord position;
  vector velocity;
  double mass;
  double elasticity;
  boolean anchor;
  public abstract boolean inside(coord[] check);
  public abstract void advance(double grav);
  public abstract void draw(Graphics2D g2D);
  public abstract coord[] getCollisionCoords();
}
class coord {
  double x;
  double y;
  coord() {
    x = 0;
    y = 0;
  }
  coord(double inX, double inY) {
    x = inX;
    y = inY;
  }
  public String toString() {
    return "(" + x + ", " + y + ")";
  }
}
class vector {
  double magnitude;
  double angle;
  vector() {
    magnitude = 0;
    angle = 0;
  }
  vector(double inMag, double inAng) {
    magnitude = inMag;
    angle = Math.toRadians(inAng);
  }
  public double xChange() {
    return magnitude * Math.cos(angle);
  }
  public double yChange() {
    return magnitude * Math.sin(angle);
  }
  public void addvector(vector in) {
    //Get the current component form of the vector
    double x = xChange();
    double y = yChange();
    //Alter the component form of the vector using the component form of the other vector
    x += in.xChange();
    y += in.yChange();
    magnitude = Math.sqrt(Math.pow(x, 2) + Math.pow(y,2));
    angle = Math.atan(Math.abs(y) / Math.abs(x));
    if (x < 0 && y > 0) {//upper left quad
      angle = Math.PI - angle;
    } else if (x < 0 && y < 0) {//Lower left quad
      angle = Math.PI + angle;
    } else if (x > 0 && y < 0) {//Lower right quad
      angle = (2 * Math.PI) - angle;
    }
  }
  public void draw(coord pos, Graphics2D g2D) {
    g2D.setColor(Color.red);
    g2D.drawLine((int)pos.x, (int)pos.y, (int)(pos.x + xChange()), (int)(pos.y + yChange()));
  }
}
class rectangle extends physicsObj {
  double width;
  double height;
  rectangle() {
    width = 10;
    height = 10;
    position = new coord(0,0);
    velocity = new vector(0,0);
    mass = 0;
    anchor = false;
  }
  rectangle(coord inPos, double inW, double inH, double inMass) {
    width = inW;
    height = inH;
    mass = inMass;
    elasticity = .5;
    position = new coord(inPos.x, inPos.y);
    velocity = new vector(0,0);
    anchor = false;
  }
  public boolean inside(coord[] check) {
    for (int i = 0; i < check.length; i++) {
      if ((check[i].x > position.x && check[i].x < position.x + width) && (check[i].y > position.y && check[i].y < position.y + height)) {
        return true;
      }
    }
    return false;
  }
  public void advance(double grav) {
    velocity.addvector(new vector(grav, 90));
    //System.out.println("X change: " + velocity.xChange());
    //System.out.println("Y change: " + velocity.yChange());
    position.x += velocity.xChange();
    position.y += velocity.yChange();
    //Code to move the rectangle object
    System.out.println(position.toString());
  }
  public void draw(Graphics2D g2D) {
    g2D.setColor(Color.gray);
    g2D.fillRect((int)Math.round(position.x), (int)Math.round(position.y), (int)Math.round(width), (int)Math.round(height));
    g2D.setColor(Color.white);
    g2D.drawRect((int)Math.round(position.x), (int)Math.round(position.y), (int)Math.round(width), (int)Math.round(height));
    //System.out.println("Drew at " + (int)Math.round(position.x) + ", " + (int)Math.round(position.y));
    if (!anchor) {
      velocity.draw(position,g2D);
    }
  }
  public coord[] getCollisionCoords() {
    return new coord[] {new coord(position.x, position.y),new coord(position.x + width, position.y), new coord(position.x, position.y + height), new coord(position.x + width, position.y + height)};
  }
}