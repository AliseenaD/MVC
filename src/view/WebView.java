package view;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import model.IAlbumShape;


/**
 * Creates a web HTML page that acts as the "view" for the album.
 */
public class WebView implements IWebView {
  private String file;

  public WebView(String file) {
    this.file = file;
  }

  /**
   * Outputs the html file.
   */
  public void outputFile(String html) throws IOException {
    FileWriter fileStream = new FileWriter(file);
    BufferedWriter out = new BufferedWriter(fileStream);
    out.append(html);
    out.close();
    fileStream.close();
  }
}
