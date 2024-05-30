package view;

import java.awt.*;
import java.util.List;

import controller.command.ICommand;

/**
 * An interface that sets forth the functions involved with the buttons within
 * the user interface.
 */
public interface IView {

  /**
   * Draws and fills a shape based off of the designation of the controller.
   * @param shape shape type
   * @param x x
   * @param y y
   * @param xLength x length
   * @param yLength y length
   * @param r r
   * @param g g
   * @param b b
   */
  void drawShape(String shape, double x, double y, double xLength,
                 double yLength, int r, int g, int b);

  /**
   * Sets the previous button to a command that will take to previous snapshot if possible.
   * @param command command
   */
  void setPreviousCommand(ICommand command);

  /**
   * Sets the select button to a command that pops up all snapshots and
   * allows user to choose from them.
   * @param command command
   */
  void setSelectCommand(ICommand command);

  /**
   * Sets the next button to a command that will take to next snapshot if possible.
   * @param command command
   */
  void setNextCommand(ICommand command);

  /**
   * Sets the quit button to a command that will exit the screen.
   * @param command command
   */
  void setQuitCommand(ICommand command);

  /**
   * Sets the window to being visible.
   */
  void displayWindow();

  /**
   * Displays a message, specifically important for letting
   * the client know of any errors that may occur.
   * @param message message
   * @throws IllegalArgumentException if message is empty or null
   */
  void displayMessage(String message) throws IllegalArgumentException;

  /**
   * Closes the window under any other circumstance such as error handling or
   * when client presses the quit button.
   */
  void closeWindow();

  /**
   * Generates a selection menu with options based off of the values of the list
   * @param selections selections
   * @param index index of starting option
   */
  String displaySelectionMenu(List selections, int index);

  /**
   * draws the snapshot id and description (if present) at the top of the frame.
   * @param id id
   * @param description description
   */
  void drawLabel(String id, String description);

  /**
   * Clears all the shapes on the frame so that new shapes can be drawn.
   */
  void clearShapes();
}
