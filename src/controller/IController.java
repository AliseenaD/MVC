package controller;

import java.io.IOException;

/**
 * A controller that alters the model based off inputted text files.
 */
public interface IController {

  /**
   * Begins the functionality of the controller and its functions
   * @throws IOException error
   */
  void go() throws IOException;
}
