import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import model.Album;
import model.AlbumShape;
import model.IAlbum;
import model.IAlbumShape;
import model.shapes.Oval;
import model.shapes.Point2D;
import model.shapes.Rectangle;

class AlbumTest {
  IAlbum album = new Album();

  /**
   * Tests the createShape method. First tests to ensure a shape is added to album,
   * next tests to ensure that no repeat names can be added.
   */
  @Test
  void testCreateShape() {
    album.createShape("One", "rectangle",2, 3, 1,1,1,
            3, 5);
    Assertions.assertEquals(album.getShape("One"), new Rectangle(2, 3, 1,1,
            1, 3, 5));
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      album.createShape("One", "rectangle",2, 3,1,1,1,
              3, 5);
    });
    Assertions.assertNull(album.createShape("Three", "square", 1,1,
            1,1,1,3,5));
  }

  /**
   * Ensures that a shape is accurately returned when called for by a passed name.
   * Also ensures an error is thrown if name is null or empty. Lastly, ensures that
   * null is returned if name cannot be found in album.
   */
  @Test
  void testGetShape() {
    album.createShape("One", "rectangle",2, 3, 1,1,1,
            3, 5);
    album.createShape("two", "oval",9, 9, 0,0,1,
            10, 10);
    Assertions.assertEquals(album.getShape("One"),
            new Rectangle(2, 3, 1,1,1, 3, 5));
    Assertions.assertEquals(album.getShape("two"),
            new Oval(9, 9, 0,0,1, 10, 10));
    // Test error throwing
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      album.getShape(null);
      album.getShape("");
    });
    // Test return null for non appearing shape
    Assertions.assertNull(album.getShape("gher"));
  }

  /**
   * Ensures that the location is accurately changed. Also ensures
   * that error is thrown when name is empty or null. Ensures nothing happens
   * if name cannot be found
   */
  @Test
  void testChangeLocation() {
    album.createShape("One", "rectangle",2, 3, 1,1,1,
            3, 5);
    album.createShape("two", "oval",9, 9, 0,0,1,
            10, 10);
    album.changeLocation("One",5,5);
    album.changeLocation("two",1,1);
    Assertions.assertEquals(album.getShape("One").getLocation(),
            new Point2D(5,5));
    Assertions.assertEquals(album.getShape("two").getLocation(),
            new Point2D(1,1));
    // Test that nothing changes when trying to add new point to a nonexistent shape
    album.changeLocation("three",9, 21);
    Assertions.assertEquals(album.getShape("One").getLocation(),
            new Point2D(5,5));
    Assertions.assertEquals(album.getShape("two").getLocation(),
            new Point2D(1,1));
    // Test error for empty or null name
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      album.changeLocation(null,1,1);
      album.changeLocation("",1,1);
    });
  }

  /**
   * Tests the changeColor method. Also ensures that nothing changes
   * when a nonexistent name is given and an error is thrown if name is
   * empty or null.
   */
  @Test
  void testChangeColor() {
    album.createShape("One", "rectangle",2, 3, 1,1,1,
            3, 5);
    album.createShape("two", "oval",9, 9, 0,0,1,
            10, 10);
    album.changeColor("One", 1,0,0);
    album.changeColor("two", 1,1,1);
    Assertions.assertEquals(album.getShape("One").getColor().get(2),
            0);
    Assertions.assertEquals(1, album.getShape("two").getColor().getLast());
    // Test nothing occurs when passing nonexistent name
    album.changeColor("three", 1,1,1);
    Assertions.assertEquals(album.getShape("One").getColor().getFirst(),
            1);
    Assertions.assertEquals(album.getShape("two").getColor().getFirst(),
            1);
    // Test error thrown for empty or null name
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      album.changeColor("", 1,1,1);
      album.changeColor(null, 0,0,1);
    });
  }

  /**
   * Ensures that x size is appropriately changed. Ensures error is
   * thrown if name passed is empty or null, also ensures that
   * nothing occurs when a nonexistent name is passed.
   */
  @Test
  void testChangeXSize() {
    album.createShape("One", "rectangle",2, 3, 1,1,1,
            3, 5);
    album.createShape("two", "OVAL",9, 9, 0,0,1,
            10, 10);
    album.changeXSize("One", 111);
    album.changeXSize("two", 9999);
    Assertions.assertEquals(album.getShape("One").getXSize(), 111);
    Assertions.assertEquals(album.getShape("two").getXSize(),9999);
    // Test nothing occurs when passing nonexistent name
    album.changeXSize("ashf", 2);
    Assertions.assertEquals(album.getShape("One").getXSize(), 111);
    Assertions.assertEquals(album.getShape("two").getXSize(),9999);
    // Test error thrown for empty or null name
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      album.changeXSize("", 23);
      album.changeXSize(null, 2);
    });
  }

  /**
   * Ensures that y size is appropriately changed. Ensures error is
   * thrown if name passed is empty or null, also ensures that
   * nothing occurs when a nonexistent name is passed.
   */
  @Test
  void testChangeYSize() {
    album.createShape("One", "rectaNgle",2, 3, 1,1,1,
            3, 5);
    album.createShape("two", "oval",9, 9, 0,0,1,
            10, 10);
    album.changeYSize("One", 232);
    album.changeYSize("two", 45);
    Assertions.assertEquals(album.getShape("One").getYSize(), 232);
    Assertions.assertEquals(album.getShape("two").getYSize(),45);
    // Test nothing occurs when passing nonexistent name
    album.changeYSize("ashf", 2);
    Assertions.assertEquals(album.getShape("One").getYSize(), 232);
    Assertions.assertEquals(album.getShape("two").getYSize(),45);
    // Test error thrown for empty or null name
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      album.changeYSize("", 23);
      album.changeYSize(null, 2);
    });
  }

  /**
   * Tests to ensure that the snapshot works and that values altered after snapshot
   * does not alter the snapshot.
   */
  @Test
  void testGetSnapshot() {
    album.createShape("One", "rectangle",2, 3, 1,1,1,
            3, 5);
    album.createShape("two", "oval",9, 9, 0,0,1,
            10, 10);
    String id = album.snapshot("");
    album.changeColor("One", 0,0,123);
    String id2 = album.snapshot("");
    Assertions.assertNotEquals(album.getSpecificSnapshot(id),
            album.getSpecificSnapshot(id2));
  }

  /**
   * Tests that a list of previous snapshots is returned AND that
   * an error is thrown if list is modified.
   */

  // You can use a fixed clock to get duplicate strings, can also edit and remove the time stamp on the id
  @Test
  void testGetSnapshotHistory() {
    album.createShape("One", "rectangle",2, 3, 1,1,234,
            3, 5);
    album.createShape("two", "oval",9, 9, 22,1,1,
            10, 10);
    String idOne = album.snapshot("One");
    String idTwo = album.snapshot("Two");
    // Test correct list is returned
    List<String> test = new ArrayList<>();
    test.add(idOne);
    test.add(idTwo);
    Assertions.assertEquals(test, album.getSnapshotHistory());
    // Test list is unmodifiable
    Assertions.assertThrows(UnsupportedOperationException.class, () -> {
      album.getSnapshotHistory().clear();
    });
  }

  /**
   * Tests that the correct information is returned for all snapshot
   * details.Will check each specific snapshot and also check to ensure that
   * details of previous snapshots are not changed after edits are made to the shapes
   * post snapshot.
   */
  @Test
  void testGetSnapshotDetails() {
    List<IAlbumShape> shapes = new ArrayList<>();
    album.createShape("One", "rectangle",2, 3, 1,1,1,
            3, 5);
    album.createShape("two", "oval",9, 9, 0,0,1,
            10, 10);
    IAlbumShape oneTest = new AlbumShape("One", new Rectangle(2, 3, 1,
            1,1, 3, 5));
    IAlbumShape twoTest = new AlbumShape("two", new Oval(9, 9, 0,0,1,
            10, 10));
    shapes.add(oneTest);
    shapes.add(twoTest);
    String idOne = album.snapshot("One");
    album.changeYSize("two",10000);
    String idTwo = album.snapshot("two");
    Assertions.assertEquals(shapes, album.getSnapshotDetails().get(idOne));
    Assertions.assertNotEquals(album.getSnapshotDetails().get(idOne),
            album.getSnapshotDetails().get(idTwo));
  }

  /**
   * Tests that a specific snapshot is returned by id. Also ensures that
   * null is returned when id cannot be found.
   */
  @Test
  void testGetSpecificSnapshot() {
    album.createShape("One", "rectangle",2, 3, 1,1,234,
            3, 5);
    album.createShape("two", "oval",9, 9, 22,1,1,
            10, 10);
    String idOne = album.snapshot("One");
    album.changeYSize("two", 10000);
    String idTwo = album.snapshot("two");
    Assertions.assertNotEquals(album.getSpecificSnapshot(idOne),
            album.getSpecificSnapshot(idTwo));
    Assertions.assertEquals(album.getSpecificSnapshot(idOne).getFirst().getName(),"One");
    // Now test nothing is returned for nonexistent id
    Assertions.assertNull(album.getSpecificSnapshot("He"));
  }

  /**
   * Ensures that no more shapes or snapshots remain after album is cleared.
   */
  @Test
  void testClearAlbum() {
    album.createShape("One", "rectangle",2, 3, 1,1,234,
            3, 5);
    album.createShape("two", "oval",9, 9, 22,1,1,
            10, 10);
    String idOne = album.snapshot("One");
    String idTwo = album.snapshot("two");
    album.clearAlbum();
    Assertions.assertNull(album.getSpecificSnapshot(idOne));
    Assertions.assertNull(album.getShape("One"));
    Assertions.assertNull(album.getShape("two"));
  }
}
