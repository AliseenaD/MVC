package model;

import model.shapes.IShape;

/**
 * A factory for shapes.
 */
public interface IFactory {

  /**
   * Creates a new IShape based on the passed kind and parameters. returns null if
   * kind is not a shape.
   * @param kind kind
   * @param x x
   * @param y y
   * @param r r
   * @param g g
   * @param b b
   * @param xLength x length
   * @param yLength y length
   * @return shape
   */
  IShape create(String kind, double x, double y, double xLength, double yLength, int r, int g,
                int b);
}
