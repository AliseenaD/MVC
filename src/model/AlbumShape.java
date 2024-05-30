package model;

import java.util.Objects;

import model.shapes.IShape;
import model.shapes.Shape;

/**
 * Describes the methods for an album object instance.
 */
public class AlbumShape implements IAlbumShape {
  private String name;
  private IShape shape;

  /**
   * Creates a new album shape with a name attached to it.
   * @param name name
   * @param shape shape
   * @throws IllegalArgumentException if name is empty or null or if shape is null
   */
  public AlbumShape(String name, IShape shape) throws IllegalArgumentException {
    if (name == null || name.isEmpty() || shape == null) {
      throw new IllegalArgumentException("Cannot have empty or null shape or name!");
    }
    this.name = name;
    this.shape = shape;
  }

  /**
   * Copy constructor of another album shape.
   * @param other other album shape
   */
  public AlbumShape(AlbumShape other) {
    this.name = other.name;
    this.shape = other.shape;
  }

  /**
   * Gets the name of the AlbumShape
   * @return name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Creates a deep copy of the album shape.
   * @return album shape
   */
  @Override
  public IAlbumShape copy() {
    return new AlbumShape(this.name, this.shape.copy());
  }

  /**
   * Gets the shape of the AlbumShape.
   * @return shape
   */
  public IShape getShape() {
    return this.shape;
  }

  /**
   * Returns a string describing the instance of the shape in album.
   * @return description
   */
  public String toString() {
    return "Name: " + this.name + "\n" + this.getShape();
  }

  /**
   * Checks if a shape instance is the same by looking at name and the
   * actual shape of the two instances.
   * @param o other
   * @return true or false
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof AlbumShape that)) return false;
    return Objects.equals(getName(), that.getName())
            && this.shape.equals(((AlbumShape) o).getShape());
  }

  /**
   * Updates hash code.
   * @return hash
   */
  @Override
  public int hashCode() {
    return Objects.hash(getName(), getShape());
  }
}
