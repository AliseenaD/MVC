package view;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * A shape node that packages a shape with its color within the view for drawing.
 */
public class ShapeNode implements IShapeNode {
  private int red;
  private int green;
  private int blue;
  private Shape shape;

  public ShapeNode(String shape, double x, double y,
                   double xLength, double yLength, int r, int g, int b) {
    if (shape == null || shape.isEmpty()) {
      throw new IllegalArgumentException("Shape type cannot be empty or null!");
    }
    if (shape.contains("Rectangle")) {
      this.shape = new Rectangle2D.Double(x,y,xLength,yLength);
      this.red = r;
      this.green = g;
      this.blue = b;
    }
    if (shape.contains("Oval")) {
      this.shape = new Ellipse2D.Double(x,y,xLength,yLength);
      this.red = r;
      this.green = g;
      this.blue = b;
    }
  }

  /**
   * Return the shape associated with the node.
   * @return shape
   */
  public Shape getShape() {
    return this.shape;
  }

  /**
   * Return the integer value associated with red for the shape.
   * @return red int
   */
  public int getRed() {
    return this.red;
  }

  /**
   * Return the integer value associated with green for the shape.
   * @return green int
   */
  public int getGreen() {
    return this.green;
  }

  /**
   * Return the integer value associated with blue for the shape.
   * @return blue int
   */
  public int getBlue() {
    return this.blue;
  }
}
