import java.io.IOException;

import controller.FullController;
import controller.IController;
import model.Album;
import model.IAlbum;

/**
 * The main class at which the client can access the photo album.
 */
public class PhotoAlbumMain {
  public static void main(String[] args) throws IOException {
    IAlbum model = new Album();
    IController controller = new FullController(args, model);
    controller.go();
  }
}
