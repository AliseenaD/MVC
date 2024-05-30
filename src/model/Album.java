package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import model.shapes.IShape;

/**
 * A class that is meant to define and characterize an album of shapes.
 */
public class Album implements IAlbum {
  private List<IAlbumShape> shapes = new ArrayList<>();
  // Key will be id, element will be entire snapshot in string
  private Map<String, String> snapshots = new LinkedHashMap<>();
  // Key will be id, element will be entire snapshot as objects
  private Map<String, List<IAlbumShape>> snapshotObjects = new LinkedHashMap<>();
  private List<String> descriptions = new ArrayList<>();


  /**
   * Adds a shape with a name to the album if that name is not already present in the list.
   * Otherwise, throws error that name is already taken.
   * @param name name
   * @param kind kind
   * @param x x
   * @param y y
   * @param r r
   * @param g g
   * @param b b
   * @param xLength x length
   * @param yLength y length
   * @throws IllegalArgumentException if name given that is already in use or name
   * passed is null or empty
   */
  public IAlbumShape createShape(String name, String kind, double x, double y, double xLength,
                                 double yLength, int r, int g, int b)
                                 throws IllegalArgumentException {
    // Check if name already exists
    for (IAlbumShape each : this.shapes) {
      if (each.getName() == name) {
        throw new IllegalArgumentException("Name already exists in the album!");
      }
    }
    // Add if does not exist and shape is not null
    IFactory factory = new ShapeFactory();
    IShape shape = factory.create(kind, x, y, xLength, yLength, r, g, b);
    if (shape != null) {
      IAlbumShape temp = new AlbumShape(name, shape);
      this.shapes.add(temp);
      return temp;
    }
    // Return null if shape is null
    return null;
  }

  /**
   * Gets the shape associated with a passed name. Return null if not found.
   * @param name name
   * @return shape
   * @throws IllegalArgumentException if name empty or null
   */
  public IShape getShape(String name) throws IllegalArgumentException {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Name cannot be null or empty!");
    }
    for (IAlbumShape each : this.shapes) {
      if (each.getName() == name) {
        return each.getShape();
      }
    }
    return null;
  }

  /**
   * Changes the location of the shape of the given name. Else if name not
   * found then nothing happens
   * @param name name
   * @param x x coord
   * @param y y coord
   * @throws IllegalArgumentException if name is empty or null
   */
  public void changeLocation(String name, double x, double y) throws IllegalArgumentException {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Name cannot be empty or null!");
    }
    for (IAlbumShape each : this.shapes) {
      if (Objects.equals(each.getName(), name)) {
        each.getShape().setLocation(x, y);
      }
    }
  }

  /**
   * Changes the color of a shape associated with the passed name. Else nothing
   * happens if name cannot be found.
   * @param name name
   * @param r r
   * @param g g
   * @param b b
   * @throws IllegalArgumentException if name is empty or null
   */
  public void changeColor(String name, int r, int g, int b) throws IllegalArgumentException {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Name cannot be empty or null!");
    }
    for (IAlbumShape each : this.shapes) {
      if (each.getName() == name) {
        each.getShape().setColor(r, g, b);
      }
    }
  }

  /**
   * Changes the size of shape given a name on the x axis. If cannot find shape,
   * nothing occurs.
   * @param name name
   * @param x size on x axis
   * @throws IllegalArgumentException if name is empty or null
   */
  public void changeXSize(String name, double x) throws IllegalArgumentException {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Name cannot be empty or null!");
    }
    for (IAlbumShape each : this.shapes) {
      if (each.getName() == name) {
        each.getShape().setXSize(x);
      }
    }
  }

  /**
   * Changes the size of shape given a name on the y axis. If cannot find shape,
   * nothing occurs.
   * @param name name
   * @param y size on y axis
   * @throws IllegalArgumentException
   */
  public void changeYSize(String name, double y) throws IllegalArgumentException {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Name cannot be empty or null!");
    }
    for (IAlbumShape each : this.shapes) {
      if (each.getName() == name) {
        each.getShape().setYSize(y);
      }
    }
  }

  /**
   * Returns a string of all the current shapes and their data within the
   * album at the time of this call. To be used for snapshot's string.
   * @return descripton of all shapes.
   */
  private String allShapesString() {
    String total = "";
    for (IAlbumShape each : this.shapes) {
      total += each.toString() + "\n";
    }
    return total;
  }

  /**
   * Takes a snapshot of all the shapes and their data in the album and adds it to
   * the list of previous snapshots. Included also is a description as well as the
   * time id of the snapshot.
   * @param description description
   * @return id of the snapshot
   */
  public String snapshot(String description) {
    // Add description to running list
    this.descriptions.add(description);
    // Save time as string for id
    LocalDateTime time = LocalDateTime.now();
    String id = String.valueOf(time);
    // Compile full string to add to snapshot list
    String total = "Snapshot ID: " + id + "\n" + "Timestamp: " + time
            + "\n" + "Description: " + description + "\n" + "Shape information: "
            + "\n" + allShapesString();
    // Add string form to dictionary of string descriptions
    this.snapshots.put(id, total);
    // Add object form to a dictionary with its id being the key
    List<IAlbumShape> copy = new ArrayList<>();
    for (IAlbumShape each : this.shapes) {
      copy.add(each.copy());
    }
    this.snapshotObjects.put(id, copy);
    return id;
  }

  /**
   * Returns the IDs of all previous snapshots. Does this by adding keys to
   * list and returning unmodifiable list.
   * @return list of previous snapshots
   */
  public List<String> getSnapshotHistory() {
    List<String> history = new ArrayList<>(this.snapshots.keySet());
    return Collections.unmodifiableList(history);
  }

  /**
   * Gets all the descriptions of previous snapshots.
   * @return descriptions
   */
  @Override
  public List<String> getDescription() {
    return Collections.unmodifiableList(this.descriptions);
  }

  /**
   * Returns the details of all snapshots.
   * @return map of all previous snapshot details
   */
  public Map<String, List<IAlbumShape>> getSnapshotDetails() {
    return this.snapshotObjects;
  }

  /**
   * Given an id, get the details of a snapshot. Return null if cannot be found.
   * @param id id
   * @return details of snapshot
   * @throws IllegalArgumentException if id is null or empty
   */
  public List<IAlbumShape> getSpecificSnapshot(String id) throws IllegalArgumentException {
    if (id == null || id.isEmpty()) {
      throw new IllegalArgumentException("ID cannot be null or empty!");
    }
    for (Map.Entry<String, List<IAlbumShape>> each : this.snapshotObjects.entrySet()) {
      if (each.getKey().equals(id)) {
        return each.getValue();
      }
    }
    return null;
  }

  /**
   * Clears all the snapshots and shapes in the model.
   */
  public void clearAlbum() {
    this.snapshots.clear();
    this.shapes.clear();
    this.snapshotObjects.clear();
    this.descriptions.clear();
  }

  /**
   * Removes a shape from the album is name is found, otherwise nothing occurs.
   * @param name name
   * @throws IllegalArgumentException if name empty or null
   */
  public void remove(String name) throws IllegalArgumentException {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Name cannot be null or empty!");
    }
    // Remove if shape is found
    for (IAlbumShape shape : this.shapes) {
      if (shape.getName().contentEquals(name)) {
        this.shapes.remove(shape);
      }
    }
  }
}
