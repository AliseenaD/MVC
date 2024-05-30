import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import model.shapes.Point2D;

/**
 * Tests the methods in Point2D.
 */
class PointTest {
  Point2D one = new Point2D(10, 6);
  Point2D two = new Point2D(98, 87.5);

  /**
   * Tests the getX method.
   */
  @Test
  void testGetX() {
    Assertions.assertEquals(one.getX(), 10);
    Assertions.assertEquals(two.getX(), 98);
  }

  /**
   * Tests the getY method.
   */
  @Test
  void testGetY() {
    Assertions.assertEquals(one.getY(), 6);
    Assertions.assertEquals(two.getY(), 87.5);
  }

  /**
   * Tests the setX method.
   */
  @Test
  void testSetX() {
    one.setX(1);
    two.setX(-124);
    Assertions.assertEquals(one.getX(), 1);
    Assertions.assertEquals(two.getX(), -124);
  }

  /**
   * Tests the setY method.
   */
  @Test
  void testSetY() {
    one.setY(-20);
    two.setY(65);
    Assertions.assertEquals(one.getY(), -20);
    Assertions.assertEquals(two.getY(), 65);
  }

  /**
   * Tests the toString method.
   */
  @Test
  void testToString() {
    Assertions.assertEquals(one.getX() + "," + one.getY(), one.toString());
    Assertions.assertEquals(two.getX() + "," + two.getY(), two.toString());
  }

  /**
   * Tests the equals method and ensures works correctly.
   */
  @Test
  void testEquals() {
    Object o = new Object();
    Assertions.assertFalse(one.equals(o));
    Assertions.assertFalse(two.equals(o));
    Point2D wrong = new Point2D(99, 23.23);
    Assertions.assertFalse(one.equals(wrong));
    Assertions.assertFalse(two.equals(wrong));
    Point2D oneRight = new Point2D(10, 6);
    Point2D twoRight = new Point2D(98, 87.5);
    Assertions.assertTrue(one.equals(oneRight));
    Assertions.assertTrue(two.equals(twoRight));
  }
}
