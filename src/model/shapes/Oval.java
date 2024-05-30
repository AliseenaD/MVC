package model.shapes;

import java.util.Objects;

/**
 * A class that defines oval shapes that extend shape.
 */
public class Oval extends Shape implements IShape {
  private double radiusX;
  private double radiusY;

  /**
   * A constructor for ovals that takes a point, color, radius in x, and
   * radius in y.
   * @param x x coord
   * @param y y coord
   * @param r r
   * @param g g
   * @param b b
   * @param radiusX x radius
   * @param radiusY y radius
   * @throws IllegalArgumentException if the radii are negative
   */
  public Oval(double x, double y, double radiusX, double radiusY, int r, int g, int b) throws
          IllegalArgumentException {
    super(x, y, r, g, b);
    if (radiusX < 0 || radiusY < 0) {
      throw new IllegalArgumentException("Radius cannot be negative!");
    }
    this.radiusX = radiusX;
    this.radiusY = radiusY;
  }

  /**
   * A copy constructor for ovals.
   * @param other shape
   */
  public Oval(Oval other) {
    super(other);
    this.radiusY = other.radiusY;
    this.radiusX = other.radiusX;
  }

  /**
   * Gets the X radius.
   * @return x radius
   */
  public double getXSize() {
    return this.radiusX;
  }

  /**
   * Gets the Y radius.
   * @return y radius
   */
  public double getYSize() {
    return this.radiusY;
  }

  /**
   * Sets the x radius to a passed value.
   * @param radiusX x radius
   * @throws IllegalArgumentException if passed value less than 0
   */
  public void setXSize(double radiusX) throws IllegalArgumentException {
    if (radiusX < 0) {
      throw new IllegalArgumentException("Radius cannot be negative!");
    }
    this.radiusX = radiusX;
  }

  /**
   * Sets the y radius to a passed value.
   * @param radiusY y radius
   * @throws IllegalArgumentException if radius less than 0
   */
  public void setYSize(double radiusY) throws IllegalArgumentException {
    if (radiusY < 0) {
      throw new IllegalArgumentException("Radius cannot be negative!");
    }
    this.radiusY = radiusY;
  }

  /**
   * Provides a new oval that is a deep copy of the original.
   * @return deep oval copy
   */
  @Override
  public IShape copy() {
    return new Oval(this.getLocation().getX(), this.getLocation().getY(),
            this.getXSize(), this.getYSize(), this.getColor().get(0), this.getColor().get(1),
            this.getColor().get(2));
  }

  /**
   * Gets a description of the oval with location, color, width, and height.
   * @return description
   */
  public String toString() {
    return "Type: Oval \n" + "Center point: (" + this.getLocation() + "), X-radius: " + this.radiusX
            + ", Y-radius: " + this.radiusY + " Color: " + "(" + (double)this.getColor().get(0)
            + "," + (double)this.getColor().get(1) + "," + (double)this.getColor().get(2)
            + ")";
  }

  /**
   * An equals method based on location, radius,  and color.
   * @param o other
   * @return true or false
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Oval oval)) return false;
    return Double.compare(getXSize(), oval.getXSize()) == 0
            && Double.compare(getYSize(), oval.getYSize()) == 0
            && this.getColor().equals(oval.getColor())
            && this.getLocation().equals(oval.getLocation());
  }

  /**
   * Updates the hash.
   * @return hash
   */
  @Override
  public int hashCode() {
    return Objects.hash(getXSize(), getYSize());
  }

  public static void main(String[] args) {
    Oval oval = new Oval(1,1,1,2,3,4,7);
    oval.setColor(2,2,2);
    System.out.println(oval.toString());
    System.out.println(oval.getColor().getLast());
  }
}

