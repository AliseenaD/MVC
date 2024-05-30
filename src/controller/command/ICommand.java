package controller.command;

import java.io.IOException;

/**
 * A command interface that provides an execute function for the controller
 * upon interaction with the view.
 */
public interface ICommand {

  /**
   * Executes a specific function depending on the button that was clicked.
   */
  void execute();
}
