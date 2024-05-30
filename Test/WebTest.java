import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import controller.IController;
import controller.WebController;
import model.Album;
import model.IAlbum;
import view.IWebView;
import view.WebView;

/**
 * A class meant to test the functionality and outpyt of the web controller
 * and the web view.
 */
public class WebTest {
  String testFile = "testFile.txt";
  IAlbum model = new Album();
  IWebView view = new WebView(testFile);
  IController controller = new WebController(model, 100,100, view);

  /**
   * Tests to ensure that the functionality of the web controller and view outputs a text file.
   */
  @Test
  void testOutputFile() throws IOException {
    controller.go();
    File file = new File("testFile.txt");
    Assertions.assertTrue(file.exists());
  }

  /**
   * Tests to ensure that the content of the ouput file is also correct.
   */
  @Test
  void testContentFile() throws IOException {
    // First add content to model and do some changes
    model.createShape("One","Rectangle",5,10,12,
            13,6,8,7);
    model.createShape("Two","Oval",3,67,12,23,67,87,44);
    model.changeYSize("Two",100);
    String id = model.snapshot("Test");
    controller.go();
    String content = String.valueOf(Files.readAllLines(Paths.get(testFile)));
    String expected = "<!DOCTYPE html>\n" + "<html>\n" + "<body>\n"
            + "<h1>Web View of Shape Album</h1>\n" + "<div>\n"
            + "<h2>" + id + "\n" + "Test" + "</h2>\n" + "<svg width=100 height=100>\n"
            + "<rect id=One" + " x=" + model.getShape("One").getLocation().getX()
            + " y=" + model.getShape("One").getLocation().getY()
            + " width=" + model.getShape("One").getXSize() + " height="
            + model.getShape("One").getYSize()
            + " fill= rgb(" + model.getShape("One").getColor().getFirst()
            + "," + model.getShape("One").getColor().get(1) + ","
            + model.getShape("One").getColor().getLast() + ")></rect>\n"
            + "<ellipse" + " id=Two" + " cx=" + model.getShape("Two").getLocation().getX()
            + " cy=" + model.getShape("Two").getLocation().getY()
            + " rx=" + model.getShape("Two").getXSize() + " ry="
            + model.getShape("Two").getYSize() + " fill= rgb("
            + model.getShape("Two").getColor().getFirst()
            + "," + model.getShape("Two").getColor().get(1) + ","
            + model.getShape("Two").getColor().getLast() + ")></ellipse>\n"
            + "</svg>\n" + "</div>\n" + "</body>\n" + "</html>";
  }
}
