package controller.command;

import java.io.IOException;

import controller.IController;
import controller.ModelGraphicalAdapter;
import model.IAlbum;
import view.IView;

public class GraphicalCommand implements IController, ICommandDelegate {
  private IView view;
  private IAlbum model;
  private ModelGraphicalAdapter adapter;
  private int snapshotIndex;

  public GraphicalCommand(IView view, IAlbum model) {
    this.view = view;
    this.model = model;
    this.view.setPreviousCommand(new PreviousCommand(this));
    this.view.setSelectCommand(new SelectCommand(this));
    this.view.setNextCommand(new NextCommand(this));
    this.view.setQuitCommand(new QuitCommand(this));
    this.snapshotIndex = 0;
    this.adapter = new ModelGraphicalAdapter(this.model, this.view);
  }

  /**
   * Passes the shape in to the view to create a node and draw the shape.
   */
  private void draw() {
    System.out.println(this.snapshotIndex);
    this.adapter.adaptGraphical(this.snapshotIndex, this.view);
  }

  public void go() throws IOException {
    // Display error message if no snapshot exists already
    if (this.model.getSnapshotHistory().isEmpty()) {
      this.view.displayMessage("No snapshots to present!");
      this.view.closeWindow();
    }
    else {
      this.draw();
      this.view.drawLabel(this.model.getSnapshotHistory().get(this.snapshotIndex),
              this.model.getDescription().get(snapshotIndex));
      this.view.displayWindow();
    }
  }

  /**
   * Presents the previous snapshot if possible, otherwise returns a message saying
   * that there is no previous.
   */
  @Override
  public void previous() {
    // Check if there is a previous snapshot
    if (this.snapshotIndex == 0) {
      this.view.displayMessage("There are no previous snapshots!");
    }
    else {
      this.view.clearShapes();
      // Go back to previous snapshot and draw the shapes
      this.snapshotIndex -= 1;
      this.draw();
      this.view.drawLabel(this.model.getSnapshotHistory().get(this.snapshotIndex),
              this.model.getDescription().get(snapshotIndex));
    }
  }

  /**
   * Allows user to select a snapshot from snapshot history.
   */
  @Override
  public void select() {
    String snapshot = this.view.displaySelectionMenu(this.model.getSnapshotHistory(),
            this.snapshotIndex);
    // If cancel or x clicked on selection menu do nothing
    if (snapshot == null) {
      return;
    }
    this.view.clearShapes();
    this.snapshotIndex = this.model.getSnapshotHistory().indexOf(snapshot);
    this.view.drawLabel(this.model.getSnapshotHistory().get(this.snapshotIndex),
            this.model.getDescription().get(snapshotIndex));
    this.draw();
  }

  /**
   * Presents the next snapshot if possible, otherwise presents a message saying
   * no next snapshot.
   */
  @Override
  public void next() {
    if (this.snapshotIndex == this.model.getSnapshotHistory().size() - 1) {
      this.view.displayMessage("This is the last snapshot!");
    }
    else {
      this.view.clearShapes();
      this.snapshotIndex += 1;
      this.view.drawLabel(this.model.getSnapshotHistory().get(this.snapshotIndex),
              this.model.getDescription().get(snapshotIndex));
      this.draw();
    }
  }

  /**
   * Exits out the window.
   */
  @Override
  public void quit() {
    this.view.closeWindow();
  }
}
