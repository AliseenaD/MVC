package model;

import model.shapes.IShape;
import model.shapes.Oval;
import model.shapes.Rectangle;

/**
 * A factory for shapes.
 */
public class ShapeFactory implements IFactory {

  /**
   * Creates a new IShape of kind specified in parameters. Returns null if kind
   * cannot be found.
   * @param kind kind
   * @param x x
   * @param y y
   * @param r r
   * @param g g
   * @param b b
   * @param xLength x length
   * @param yLength y length
   * @return new IShape
   */
  @Override
  public IShape create(String kind, double x, double y, double xLength, double yLength,
                       int r, int g, int b) {
    if (kind.equalsIgnoreCase("rectangle")) {
      return new Rectangle(x, y, xLength, yLength, r, g, b);
    }
    else if (kind.equalsIgnoreCase("oval")) {
      return new Oval(x, y, xLength, yLength, r, g, b);
    }
    return null;
  }
}
