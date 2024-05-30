package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import model.IAlbum;
import model.IAlbumShape;
import view.IWebView;

/**
 * A web controller that builds the html based of the model and passes it to the web view
 * to present.
 */
public class WebController implements IController {
  private IAlbum model;
  private IWebView view;
  private StringBuilder builder;
  private int x;
  private int y;

  /**
   * A constructor that takes in a model and a view to implement its functionality
   * on. Also takes in dimensions for the svg of screenshots.
   * @param model model
   * @param view view
   * @param x x
   * @param y y
   */
  public WebController(IAlbum model, int x, int y, IWebView view) {
    this.model = model;
    this.view = view;
    this.builder = new StringBuilder();
    this.x = x;
    this.y = y;
  }

  /**
   * Pieces together all of the components.
   */
  private void buildFile() {
    int index = 0; // Maintain an index to get each snapshot description
    builder.append("<!DOCTYPE html>\n");
    builder.append("<html>\n");
    builder.append("<body>\n");
    builder.append("<h1>Web View of Shape Album</h1>\n");
    for (Map.Entry<String,
            List<IAlbumShape>> entry : this.model.getSnapshotDetails().entrySet()) {
      builder.append("<div>\n");
      builder.append("<h2>" + entry.getKey() + "\n"
              + this.model.getDescription().get(index) + "</h2>\n");
      builder.append("<svg width=" + this.x + " height=" + this.y + ">\n");
      for (IAlbumShape each : entry.getValue()) {
        if (String.valueOf(each.getShape().getClass()).contains("Rectangle")) {
          String total = "<rect" + " id=" + each.getName()
                  + " x=" + each.getShape().getLocation().getX()
                  +  " y= " + each.getShape().getLocation().getY() +
                  " width=" + each.getShape().getXSize() + " height="
                  + each.getShape().getYSize() + " fill= rgb("
                  + each.getShape().getColor().getFirst() + "," + each.getShape().getColor().get(2)
                  + "," + each.getShape().getColor().getLast() + ")></rect>\n";
          builder.append(total);
        }
        if (String.valueOf(each.getShape().getClass()).contains("Oval")) {
          String total = "<ellipse" + " id=" + each.getName() + " cx="
                  + each.getShape().getLocation().getX() + " cy="
                  + each.getShape().getLocation().getY() +  " rx=" + each.getShape().getXSize()
                  + " ry=" + each.getShape().getYSize()
                  + " fill= rgb(" + each.getShape().getColor().getFirst()
                  + "," + each.getShape().getColor().get(2)
                  + "," + each.getShape().getColor().getLast() + ")></ellipse>\n";
          builder.append(total);
        }
      }
      index++;
      builder.append("</svg>\n");
      builder.append("</div>\n");
    }
    builder.append("</body>\n");
    builder.append("</html>");
  }

  /**
   * Activates the controller and builds the html file based off the model. Passes the
   * html to the view to output.
   * @throws IOException
   */
  @Override
  public void go() throws IOException {
    this.buildFile();
    this.view.outputFile(this.builder.toString());
  }
}
