package controller;

import model.IAlbum;
import model.IAlbumShape;
import view.IView;

/**
 * This class takes in the model and retrieves the shape information to the view
 * to adapt into its own methods.
 */
public class ModelGraphicalAdapter {
  private IAlbum model;

  public ModelGraphicalAdapter(IAlbum model, IView view) {
    this.model = model;
  }

  /**
   * Takes in the information from the model and passes the shape info to the
   * view for it to process in its own way.
   * @param index index of snapshot to use
   */
  public void adaptGraphical(int index, IView view) {
    String id = this.model.getSnapshotHistory().get(index);
    for (IAlbumShape each : this.model.getSpecificSnapshot(id)) {
      view.drawShape(String.valueOf(each.getShape().getClass()), each.getShape().getLocation().getX(),
              each.getShape().getLocation().getY(), each.getShape().getXSize(),
              each.getShape().getYSize(), each.getShape().getColor().getFirst(),
              each.getShape().getColor().get(1), each.getShape().getColor().get(2));
    }
  }
}
