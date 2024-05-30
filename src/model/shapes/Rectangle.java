package model.shapes;

import java.util.Objects;

/**
 * A class for rectangle shapes that extend shape.
 */
public class Rectangle extends Shape implements IShape {
  private double width;
  private double height;

  /**
   * Constructor for rectangle that takes in a point, color, width, and height.
   * @param x x coord
   * @param y y coord
   * @param r r
   * @param g g
   * @param b b
   * @param width width
   * @param height height
   * @throws IllegalArgumentException if width or height are negative
   */
  public Rectangle(double x, double y, double width, double height, int r, int g, int b) throws
          IllegalArgumentException {
    super(x, y, r, g, b);
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("Width or height cannot be less than 0");
    }
    this.width = width;
    this.height = height;
  }

  /**
   * A copy constructor for rectangles.
   * @param other shape
   */
  public Rectangle(Rectangle other) {
    super(other);
    this.width = other.width;
    this.height = other.height;
  }

  /**
   * Gets the width of the rectangle.
   * @return width
   */
  public double getXSize() {
    return this.width;
  }

  /**
   * Returns the height of the rectangle.
   * @return height
   */
  public double getYSize() {
    return this.height;
  }

  /**
   * Sets the width of the rectangle.
   * @param width width
   * @throws IllegalArgumentException if width less than 0
   */
  public void setXSize(double width) throws IllegalArgumentException {
    if (width < 0) {
      throw new IllegalArgumentException("Width cannot be less than 0");
    }
    this.width = width;
  }

  /**
   * Sets the height of the rectangle.
   * @param height height
   * @throws IllegalArgumentException if height less than 0
   */
  public void setYSize(double height) throws IllegalArgumentException {
    if (height < 0) {
      throw new IllegalArgumentException("Height cannot be less than 0");
    }
    this.height = height;
  }

  /**
   * Returns a deep copy of the original rectangle.
   * @return rectangle deep copy
   */
  @Override
  public IShape copy() {
    return new Rectangle(this.getLocation().getX(), this.getLocation().getY(),
            this.getXSize(), this.getYSize(), this.getColor().get(0), this.getColor().get(1),
            this.getColor().get(2));
  }

  /**
   * Gets a description of the rectangle with location, color, width, and height.
   * @return description
   */
  public String toString() {
    return "Type: Rectangle \n" + "Min corner: (" + this.getLocation() +"), Width: "
            + this.width + ", Height: " + this.height + " Color: " + "(" +
            (double)this.getColor().get(0) + "," + (double)this.getColor().get(1) + ","
            + (double)this.getColor().get(2) + ")";
  }

  /**
   * An equals method that checks if a passed object is a rectangle if it shares
   * the same width, height, color, and location.
   * @param o other
   * @return true or false
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Rectangle rectangle)) return false;
    return Double.compare(getXSize(), rectangle.getXSize()) == 0
            && Double.compare(getYSize(), rectangle.getYSize()) == 0
            && this.getColor().equals(rectangle.getColor())
            && this.getLocation().equals(rectangle.getLocation());
  }

  /**
   * Updates hash.
   * @return hash
   */
  @Override
  public int hashCode() {
    return Objects.hash(getXSize(), getYSize());
  }
}
