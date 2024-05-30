package view;

import java.io.IOException;

/**
 * An interface that defines the functionality of a web view.
 */
public interface IWebView {

  /**
   * Outputs the file based off the string passed in.
   * @param string html code
   * @throws IOException
   */
  public void outputFile(String string) throws IOException;

}
