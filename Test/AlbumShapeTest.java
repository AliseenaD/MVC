import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import model.AlbumShape;
import model.IAlbumShape;
import model.shapes.Oval;
import model.shapes.Rectangle;

class AlbumShapeTest {
  IAlbumShape one = new AlbumShape("One", new Rectangle(1,1,
          11,13,12, 12, 23));
  IAlbumShape two = new AlbumShape("Two", new Oval(9,1,
          23,25,27, 12, 12));

  /**
   * Tests that errors are thrown for album shapes that have null names or shapes.
   */
  @Test
  void testError() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      IAlbumShape one = new AlbumShape("", new Rectangle(1,1,
              11,13,14, 12, 23));
      IAlbumShape two = new AlbumShape(null, new Rectangle(1,1,
              13,15,17, 12, 23));
      IAlbumShape three = new AlbumShape("asdf", null);
    });
  }

  /**
   * Tests that getName works properly.
   */
  @Test
  void testGetName() {
    Assertions.assertEquals(one.getName(), "One");
    Assertions.assertEquals(two.getName(), "Two");
  }

  /**
   * Tests that getShape properly gets shape.
   */
  @Test
  void testGetShape() {
    // Test by checking values of shape
    Assertions.assertEquals(one.getShape().getXSize(), 12);
    Assertions.assertEquals(two.getShape().getYSize(), 12);
  }

  /**
   * Tests the toString.
   */
  @Test
  void testToString() {
    Assertions.assertEquals("Name: " + one.getName() + "\n"
            + one.getShape(), one.toString());
    Assertions.assertEquals("Name: " + two.getName() + "\n"
            + two.getShape(), two.toString());
  }

  /**
   * Tests the equals method for album shape instances by looking at
   * class and characteristics.
   */
  @Test
  void testEquals() {
    Object o = new Object();
    Assertions.assertFalse(one.equals(o));
    Assertions.assertFalse(two.equals(o));
    IAlbumShape wrong = new AlbumShape("Test", new Rectangle(5, 10,
            12, 12,12, 20,20));
    Assertions.assertFalse(one.equals(wrong));
    Assertions.assertFalse(two.equals(wrong));
    IAlbumShape oneRight = new AlbumShape("One", new Rectangle(1,1,
            11,13,12, 12, 23));
    IAlbumShape twoRight = new AlbumShape("Two", new Oval(9,1,
            23,25,27, 12, 12));
    Assertions.assertTrue(one.equals(oneRight));
    Assertions.assertTrue(two.equals(twoRight));
  }

  /**
   * Tests the deep copy of the album shape.
   */
  @Test
  void testDeepCopy() {
    IAlbumShape oneCopy = one.copy();
    IAlbumShape twoCopy = two.copy();
    one.getShape().setYSize(100000);
    twoCopy.getShape().setXSize(99);
    Assertions.assertNotEquals(one.getShape().getYSize(), oneCopy.getShape().getYSize());
    Assertions.assertNotEquals(two.getShape().getXSize(), twoCopy.getShape().getXSize());
  }
}
