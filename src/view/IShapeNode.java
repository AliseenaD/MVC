package view;

import java.awt.*;

/**
 * An interface that sets forth the functionality of the shape nodes
 * that are inserted within the view.
 */
public interface IShapeNode {
  /**
   * Return the shape associated with the node.
   * @return shape
   */
  public Shape getShape();

  /**
   * Return the integer value associated with red for the shape.
   * @return red int
   */
  public int getRed();

  /**
   * Return the integer value associated with green for the shape.
   * @return green int
   */
  public int getGreen();

  /**
   * Return the integer value associated with blue for the shape.
   * @return blue int
   */
  public int getBlue();
}
