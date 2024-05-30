package model;

import java.util.List;
import java.util.Map;

import model.shapes.IShape;

/**
 * An interface meant to set forth the methods provided in the album.
 */
public interface IAlbum {

  /**
   * Creates an instance of an album shape that will be added to the album.
   * If name of instance already exists in the album, then nothing occurs.
   * @param name name
   * @param kind kind
   * @param x x
   * @param y y
   * @param r r
   * @param g g
   * @param b b
   * @param xLength x length
   * @param yLength y length
   * @throws IllegalArgumentException if name already exists in album
   * @return album object
   */
  IAlbumShape createShape(String name, String kind, double x, double y, double xLength, double yLength,
                          int r, int g, int b) throws IllegalArgumentException;

  /**
   * Gets the shape associated with a passed name. Return null if not found.
   * @param name name
   * @return shape
   * @throws IllegalArgumentException if name empty or null
   */
  IShape getShape(String name) throws IllegalArgumentException;

  /**
   * Changes the location of the shape of the given name.
   * @param name name
   * @param x x coord
   * @param y y coord
   * @throws IllegalArgumentException if name is empty or null
   */
  void changeLocation(String name, double x, double y) throws IllegalArgumentException;

  /**
   * Changes the color of a shape associated with the passed name. Else nothing
   * happens if name cannot be found.
   * @param name name
   * @param r r
   * @param g g
   * @param b b
   * @throws IllegalArgumentException if name is empty or null
   */
  void changeColor(String name, int r, int g, int b) throws IllegalArgumentException;

  /**
   * Changes the size of shape given a name on the x axis. If cannot find shape,
   * nothing occurs.
   * @param name name
   * @param x size on x axis
   * @throws IllegalArgumentException if name is empty or null
   */
  void changeXSize(String name, double x) throws IllegalArgumentException;

  /**
   * Changes the size of shape given a name on the y axis. If cannot find shape,
   * nothing occurs.
   * @param name name
   * @param y size on y axis
   * @throws IllegalArgumentException
   */
  void changeYSize(String name, double y) throws IllegalArgumentException;

  /**
   * Takes a snapshot of all the shapes and their data in the album and adds it to
   * the list of previous snapshots. Included also is a description as well as the
   * time id of the snapshot.
   * @param description description
   * @return id of the snapshot
   */
  String snapshot(String description);

  /**
   * Returns all the shapes of all snapshots in map form
   * @return snapshots IDs
   */
  Map<String, List<IAlbumShape>> getSnapshotDetails();

  /**
   * Gets the IDs of all the previous snapshots. Will just return
   * empty list if there are none.
   * @return list of snapshot ids
   */
  List<String> getSnapshotHistory();

  /**
   * Gets the descriptions associated with the all of the snapshots.
   * @return descriptions
   */
  List<String> getDescription();

  /**
   * Given an id, get the shapes of a snapshot. Return null if cannot be found.
   * @param id id
   * @return all shapes within the snapshot
   * @throws IllegalArgumentException if id is null or empty
   */
  List<IAlbumShape> getSpecificSnapshot(String id) throws IllegalArgumentException;

  /**
   * Clears all the shapes and snapshots in the model.
   */
  void clearAlbum();

  /**
   * Removes a shape from the album given a name. Does nothing if name cannot be found.
   * @param name name
   * @throws IllegalArgumentException if name empty or null
   */
  void remove(String name) throws IllegalArgumentException;

}
