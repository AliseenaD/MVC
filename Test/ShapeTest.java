import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import model.shapes.IShape;
import model.shapes.Oval;
import model.shapes.Point2D;
import model.shapes.Rectangle;

/**
 * A class meant to test shape functions.
 */
class ShapeTest {
  IShape rectangle = new Rectangle(12, 11, 12, 23, 43, 20,20);
  IShape oval = new Oval(89, 76, 12, 25, 56, 10, 15);

  /**
   * Ensures errors are thrown for incorrect values passed to the shapes.
   */
  @Test
  void testErrors() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      IShape three = new Rectangle(5, 6, 1, 1, 1, -12, 3);
      IShape four  = new Oval(3, 4, 1, 1, 1, 9, -1);
      IShape five = new Oval(3,1,276,12,3,12,12);
    });
  }

  /**
   * Tests the getColor() method.
   */
  @Test
  void testGetColor() {
    Assertions.assertEquals("(12.0,23.0,43.0)", rectangle.getColor());
    Assertions.assertEquals("(12.0,25.0,56.0)", oval.getColor());
  }

  /**
   * Tests the setColor method and ensures that an error is thrown
   * when a null color is passed.
   */
  @Test
  void testSetColor() {
    rectangle.setColor(12, 12, 12);
    oval.setColor(15, 15, 15);
    Assertions.assertEquals(rectangle.getColor(), "(12.0,12.0,12.0)");
    Assertions.assertEquals(oval.getColor(), "(15.0,15.0,15.0)");
  }

  /**
   * Tests the getLocation method to ensure an x and y of the point is returned.
   */
  @Test
  void testGetLocation() {
    Assertions.assertEquals(rectangle.getLocation().getX(), 12);
    Assertions.assertEquals(rectangle.getLocation().getY(), 11);
    Assertions.assertEquals(oval.getLocation().getX(), 89);
    Assertions.assertEquals(oval.getLocation().getY(), 76);
  }

  /**
   * Ensures that the setLocation works properly, also
   * ensures that an error is thrown when a new Point2D of null is passed in.
   */
  @Test
  void testSetLocation() {
    rectangle.setLocation(3, 20);
    oval.setLocation(-3, 25.5);
    Assertions.assertEquals(rectangle.getLocation(), new Point2D(3,20));
    Assertions.assertEquals(oval.getLocation(), new Point2D(-3, 25.5));
  }

  /**
   * Ensures that the getXSize method accurately returns width for rectangles
   * and x radius for ovals.
   */
  @Test
  void testGetXSize() {
    Assertions.assertEquals(rectangle.getXSize(), 20);
    Assertions.assertEquals(oval.getXSize(), 10);
  }

  /**
   * Ensures that the getYSize method accurately returns height for rectangles
   * and y radius for ovals.
   */
  @Test
  void testGetYSize() {
    Assertions.assertEquals(rectangle.getYSize(), 20);
    Assertions.assertEquals(oval.getYSize(), 15);
  }

  /**
   * Tests to ensure that setting the X size for both rectangles
   * and squares works properly. Ensures error thrown for values
   * that are less than 0.
   */
  @Test
  void testSetXSize() {
    rectangle.setXSize(100);
    oval.setXSize(1.8);
    Assertions.assertEquals(rectangle.getXSize(), 100);
    Assertions.assertEquals(oval.getXSize(), 1.8);
    // Error throwing
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      rectangle.setXSize(-12);
    });
  }

  /**
   * Tests to ensure that setting the Y size for both rectangles
   * and squares works properly. Ensures error thrown for values
   * that are less than 0.
   */
  @Test
  void testSetYSize() {
    rectangle.setYSize(19);
    oval.setYSize(101.1);
    Assertions.assertEquals(rectangle.getYSize(), 19);
    Assertions.assertEquals(oval.getYSize(), 101.1);
    // Error throwing
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      oval.setYSize(-1213);
    });
  }

  /**
   * Tests the toString for the shapes.
   */
  @Test
  void testToString() {
    Assertions.assertEquals("Type: Rectangle \nMin corner: ("
            + rectangle.getLocation() + "), Width: " + rectangle.getXSize()
            + ", Height: " + rectangle.getYSize() + " Color: " + rectangle.getColor(),
            rectangle.toString());
    Assertions.assertEquals("Type: Oval \nCenter: ("
                    + oval.getLocation() + "), X radius: " + oval.getXSize()
                    + ", Y radius: " + oval.getYSize() + " Color: " + oval.getColor(),
            oval.toString());
  }

  /**
   * Tests to ensure that a true deep copy of a shape is made.
   */
  @Test
  void testDeepCopy() {
    IShape copyRect = rectangle.copy();
    IShape copyOval = oval.copy();
    rectangle.setColor(255,255,0);
    copyOval.setYSize(10000);
    Assertions.assertNotEquals(rectangle.getColor(), copyRect.getColor());
    Assertions.assertNotEquals(oval.getYSize(), copyOval.getYSize());
  }

  /**
   * Tests the equals method for shapes by checking for correct class and correct
   * values as well as ensuring false when either are not correct.
   */
  @Test
  void testEquals() {
    Object o = new Object();
    Assertions.assertFalse(rectangle.equals(o));
    Assertions.assertFalse(oval.equals(o));
    IShape wrongRec = new Rectangle(-10, -2.3, 12, 12, 12,
            12, 98);
    IShape wrongOval = new Oval(90, 922, 12, 12, 12,
            47, 99);
    Assertions.assertFalse(rectangle.equals(wrongRec));
    Assertions.assertFalse(oval.equals(wrongOval));
    IShape rightRec = new Rectangle(12, 11, 12, 23, 43, 20,20);
    IShape rightOval = new Oval(89, 76, 12, 25, 56, 10, 15);
    Assertions.assertTrue(rectangle.equals(rightRec));
    Assertions.assertTrue(oval.equals(rightOval));
  }
}
