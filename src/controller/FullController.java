package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import controller.command.GraphicalCommand;
import controller.command.ICommand;
import controller.command.TextCommand;
import model.IAlbum;
import view.IView;
import view.IWebView;
import view.SwingView;
import view.WebView;

/**
 * A class for the controller that operates the entire program.
 */
public class FullController implements IController {
  private List<String> args;
  private IAlbum model;
  List<Integer> dimensions;

  /**
   * A constructor for the full controller that takes in the command
   * arguments and a model.
   * @param args
   * @param model
   */
  public FullController(String[] args, IAlbum model) {
    this.args = Arrays.stream(args).collect(Collectors.toList());
    this.model = model;
    this.dimensions = new ArrayList<>();
  }

  /**
   * Strips the brackets off the arguments for easier processing.
   */
  private void strip() {
    // Strip off bracket if they are included
    for (int i=0;i<args.size();i++) {
      String arg = this.args.get(i);
      arg = arg.replaceAll("\\[|\\]", "");
      this.args.set(i, arg);
    }
  }

  /**
   * Finds and adds any numbers for dimensions within the command arguments to a
   * list that it then presents.
   * @return a list of dimensions
   */
  private boolean findNumbers() {
    for (String each : this.args) {
      boolean isDigit = true;
      for (char c : each.toCharArray()) {
        // go through each character in argument, if they are all integers then it is a dimension
        if (!Character.isDigit(c)) {
          isDigit = false;
        }
      }
      // add to dimensions
      if (isDigit) {
        this.dimensions.add(Integer.parseInt(each));
      }
    }
    return !this.dimensions.isEmpty();
  }

  /**
   * Delegates tasks and acts as controller. Passes the file for TextCommand to
   * process and determines whether a GraphicalCommand or WebCommand be made and
   * implemented.
   * @throws IOException error
   */
  public void go() throws IOException {
    strip();
    // Delegate file to TextCommand
    String file = this.args.get(this.args.indexOf("-in") + 1);
    ICommand text = new TextCommand(model, file);
    text.execute();
    // Find dimensions if they were passed in
    boolean dimensions = findNumbers();
    // now find view
    String viewType = this.args.get(Math.max(this.args.indexOf("-v"),
            this.args.indexOf("-view")) + 1);
    // Determine if view is web or graphical and delegate
    if (viewType.equalsIgnoreCase("graphical")) {
      IView graphView;
      // If dimensions are passed in make view with those, otherwise just pass in 1000 1000
      if (dimensions) {
        graphView = new SwingView("Graphical View of Shape Album",
                this.dimensions.getFirst(), this.dimensions.getLast());
      }
      else {
        graphView = new SwingView("Graphical View of Shape Album", 1000, 1000);
      }
      IController graphical = new GraphicalCommand(graphView, this.model);
      graphical.go();
    }
    else if (viewType.equalsIgnoreCase("web")) {
      // Get the out file name
      String outfile = this.args.get(this.args.indexOf("-out") + 1);
      // Create the html file
      IWebView webView = new WebView(outfile);
      IController webController;
      if (dimensions) {
        webController = new WebController(this.model, this.dimensions.getFirst(),
                this.dimensions.getLast(), webView);
      }
      else {
        webController = new WebController(this.model, 1000, 1000, webView);
      }
      webController.go();
    }
  }
}
