package model.shapes;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class meant to characterize all shapes and implements IShape.
 */
public abstract class Shape implements IShape {
  private Point2D point;
  private Color color;

  /**
   * A constructor for a shape that includes its location point and color.
   * Throws error if point or color are null. Makes sure point passed in is
   * copied so that it cannot be mutated when not desired to.
   * @param x x coord
   * @param y y coord
   * @param r r
   * @param g g
   * @param b b
   */
  public Shape(double x, double y, int r, int g, int b) throws IllegalArgumentException {
    this.point = new Point2D(x, y);
    if (r > 255 || g > 255 || b > 255) {
      throw new IllegalArgumentException("RGB values cannot be greater than 255!");
    }
    this.color = new Color(r, g, b);
  }

  /**
   * A copy constructor for shapes.
   * @param other shape
   */
  public Shape(Shape other) {
    this.point = new Point2D(other.point);
    this.color = other.color;
  }

  /**
   * Gets the color of the shape object. Returns a string of the RGB values.
   * @return color RGB values
   */
  public List<Integer> getColor() {
    List<Integer> colors = new ArrayList<>();
    colors.add(this.color.getRed());
    colors.add(this.color.getGreen());
    colors.add(this.color.getBlue());
    return colors;
    //return "(" + (double)this.color.getRed() + "," + (double)this.color.getGreen()
            //+ "," + (double)this.color.getBlue() + ")";
  }

  /**
   * Gets the location the shape is based on.
   * @return location point
   */
  public Point2D getLocation() {
    return new Point2D(this.point);
  }

  /**
   * Sets the color of the shape.
   * @param r r
   * @param g g
   * @param b b
   */
  public void setColor(int r, int g, int b) {
    this.color = new Color(r, g, b);
  }

  /**
   * Sets the location that the shape is based on. Ensures a copy of the point
   * is made so it cannot be mutated without intended.
   * @param x location x
   * @param y location y
   */
  public void setLocation(double x, double y) {
    this.point = new Point2D(x, y);
  }

  /**
   * Gets the size in x axis.
   * @return x size
   */
  @Override
  public double getXSize() {
    return 0;
  }

  /**
   * Gets the size in y axis.
   * @return y size
   */
  @Override
  public double getYSize() {
    return 0;
  }

  /**
   * Sets the size in x axis.
   * @param x x
   * @throws IllegalArgumentException if x less than 0
   */
  @Override
  public void setXSize(double x) throws IllegalArgumentException {
    return;
  }

  /**
   * Sets the size in y axis.
   * @param y y
   * @throws IllegalArgumentException if y less than 0
   */
  public void setYSize(double y) throws IllegalArgumentException {
    return;
  }

  /**
   * Creates a deep copy of the shape, will be implemented in concrete
   * classes.
   * @return new IShape
   */
  public IShape shape() {
    return null;
  }
}
