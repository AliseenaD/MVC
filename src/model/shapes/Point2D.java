package model.shapes;

import java.util.Objects;

/**
 * A class of 2D points that signify the center point of shapes.
 */
public class Point2D {
  private double x, y;

  /**
   * A constructor for a Point2D.
   * @param x x
   * @param y y
   */
  public Point2D(double x, double y){
    this.x = x;
    this.y = y;
  }

  /**
   * Default constructor for Point2D.
   */
  public Point2D(){
    this.x = 0;
    this.y = 0;
  }

  /**
   * A copy constructor for Point2D.
   * @param original point
   */
  public Point2D(Point2D original){
    this.x = original.x;
    this.y = original.y;
  }

  /**
   * Returns the x coord.
   * @return x
   */
  public double getX(){
    return this.x;
  }

  /**
   * Returns the y coord.
   * @return y
   */
  public double getY(){
    return this.y;
  }

  /**
   * Sets the x coord.
   * @param x x
   */
  public void setX(double x){
    this.x = x;
  }

  /**
   * Sets the y coord.
   * @param y y
   */
  public void setY(double y){
    this.y = y;
  }

  /**
   * Returns a string representation of the coordinates.
   * @return description
   */
  public String toString() {
    return this.x + "," + this.y;
  }

  /**
   * An equals method that compares the x and y coords of the two point objects
   * to determine if equals.
   * @param o other
   * @return true or false
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Point2D point2D)) return false;
    return Double.compare(getX(), point2D.getX()) == 0
            && Double.compare(getY(), point2D.getY()) == 0;
  }

  /**
   * Updates hash.
   * @return hash
   */
  @Override
  public int hashCode() {
    return Objects.hash(getX(), getY());
  }
}

