package model;

import model.shapes.IShape;

/**
 * An interface that sets forth the methods that will be provided
 * by shapes within the album.
 */
public interface IAlbumShape {

  /**
   * Returns the shape associated with the object.
   * @return shape
   */
  IShape getShape();

  /**
   * Gets the name associated with the object.
   * @return name
   */
  String getName();

  /**
   * Returns a deep copy of the album shape.
   * @return IAlbumShape copy
   */
  IAlbumShape copy();
}
