package controller.command;

/**
 * An interface that describes the buttons that provide functionality to the interface.
 */
public interface ICommandDelegate {
  /**
   * Presents the previous snapshot.
   */
  void previous();

  /**
   * Allows user to select a snapshot from snapshot history.
   */
  void select();

  /**
   * Presents the next snapshot.
   */
  void next();

  /**
   * Exits out the window.
   */
  void quit();
}
