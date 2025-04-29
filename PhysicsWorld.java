class PhysicsWorld {
  //is an arraylist of objects
}
abstract class physicsObj {
  coord position;
  vector velocity;
  double mass;
  public abstract boolean inside(coord check);
  public abstract void advance();
}
class coord {
  double x;
  double y;
  coord() {
    x = 0;
    y = 0;
  }
  coord(double inX, double inY) {
    inX = x;
    inY = y;
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
    angle = inAng;
  }
  public double xChange() {
    return magnitude * Math.cos(angle);
  }
  public double yChange() {
    return magnitude * Math.sin(angle);
  }
}
class rectangle extends physicsObj {
  double width;
  double height;
  rectangle() {

  }
  public boolean inside(coord check) {
    return false;
  }
  public void advance() {
    //Code to move the rectangle object
  }
}