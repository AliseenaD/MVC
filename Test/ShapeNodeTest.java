import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import view.IShapeNode;
import view.ShapeNode;

/**
 * This class is meant to taste the functionality of the shape nodes that make up the view.
 */
public class ShapeNodeTest {
  IShapeNode one = new ShapeNode("Rectangle", 5, 5,
          5,10,5,4,3);
  IShapeNode two = new ShapeNode("Oval",10,10,5,5,10,10,10);

  /**
   * Tests the getShape method by ensuring the object returned is of type rectangle of the
   * shape2D class.
   */
  @Test
  void testgetShape() {
    Assertions.assertTrue(one.getShape().getClass().toString().contains("Rectangle"));
    Assertions.assertTrue(two.getShape().getClass().toString().contains("Ellipse"));
  }

  /**
   * Tests the getRed method and ensures that an integer for red is returned.
   */
  @Test
  void testGetRed() {
    Assertions.assertEquals(one.getRed(), 5);
    Assertions.assertEquals(two.getRed(), 10);
  }

  /**
   * Tests the getGreen method and ensures that an integer for green is returned.
   */
  @Test
  void testGetGreen() {
    Assertions.assertEquals(one.getGreen(), 4);
    Assertions.assertEquals(two.getGreen(), 10);
  }

  /**
   * Tests getBlue method and ensures that an integer for blue is returned.
   */
  @Test
  void testGetBlue() {
    Assertions.assertEquals(one.getBlue(), 3);
    Assertions.assertEquals(two.getBlue(), 10);
  }
}
