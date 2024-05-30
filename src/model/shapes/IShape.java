package model.shapes;

import java.util.List;

/**
 * An interface that sets forth the methods that shapes will provide.
 */
public interface IShape {

  /**
   * Gets the color of the shape object in RGB values
   * @return color RGB values
   */
  List<Integer> getColor();

  /**
   * Returns the location point in the form of a Point2D object.
   * @return location point
   */
  Point2D getLocation();

  /**
   * Sets the color of the shape. Throws error if color is null.
   * @param r r
   * @param g g
   * @param b b
   */
  void setColor(int r, int g, int b);

  /**
   * Sets a new location point for a shape based off given x and y
   * @param x location x
   * @param y location y
   */
  void setLocation(double x, double y);

  /**
   * Returns the length of shape in x dimension.
   * @return length on X
   */
  double getXSize();

  /**
   * Gets the size of the shape in y dimension
   * @return length on y
   */
  double getYSize();

  /**
   * Sets the X size to passed value.
   * @param x x
   * @throws IllegalArgumentException if x less than 0
   */
  void setXSize(double x) throws IllegalArgumentException;

  /**
   * Sets the Y size to passed value.
   * @param y y
   * @throws IllegalArgumentException if y less than 0
   */
  void setYSize(double y) throws IllegalArgumentException;

  /**
   * Creates a deep copy of the shape.
   * @return new IShape
   */
  IShape copy();
}
