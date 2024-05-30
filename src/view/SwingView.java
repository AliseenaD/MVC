package view;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import controller.command.ICommand;

public class SwingView extends JFrame implements IView {

  private JButton btnPrev;
  private JButton btnSelect;
  private JButton btnNext;
  private JButton btnQuit;
  private ShapePanel shapePanel;
  private JPanel buttonPanel;
  private List<ShapeNode> shapes;
  private JPanel captionPanel;
  private JLabel label;

  /**
   * A constructor for the view that generates a panel for the description of
   * snapshot, the shapes, as well as the buttons. Also instantiates the buttons. Takes
   * in an x and y for the screen size
   * @param caption caption
   * @param x x dimension
   * @param y y dimension
   */
  public SwingView(String caption, int x, int y) {
    super(caption);
    this.setSize(x,y);
    this.setLocation(0,0);
    this.shapes = new ArrayList<>();
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    this.buttonPanel = new JPanel(new GridLayout(0,4));
    // BUTTON SECTION
    // previous button
    btnPrev = new JButton("Previous");
    btnPrev.setActionCommand("previous");
    buttonPanel.add(btnPrev);
    // select button
    btnSelect = new JButton("Select");
    btnSelect.setActionCommand("select");
    buttonPanel.add(btnSelect);
    // next button
    btnNext = new JButton("Next");
    btnNext.setActionCommand("next");
    buttonPanel.add(btnNext);
    // quit button
    btnQuit = new JButton("Quit");
    btnQuit.setActionCommand("quit");
    buttonPanel.add(btnQuit);
    // Label
    this.label = new JLabel("" + "\n" + "");
    this.shapePanel = new ShapePanel();
    this.captionPanel = new JPanel();
    this.captionPanel.add(label);
    this.captionPanel.setBackground(Color.green);
    this.add(shapePanel, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.add(captionPanel, BorderLayout.NORTH);
  }

  /**
   * Create an inner ShapePanel class to use for the shapes that
   * overrides the paintComponent method so ONLY the ShapePanel is
   * drawn over when repaint occurs.
   */
  private class ShapePanel extends JPanel {
    /**
     * Overrides the paintComponent so only the ShapePanel undergoes
     * changes when shapes are painted.
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      for (ShapeNode shape : shapes) {
        g2.setColor(new Color(shape.getRed(), shape.getGreen(), shape.getBlue()));
        g2.fill(shape.getShape());
      }
    }
  }

  /**
   * Sets the window to being visible.
   */
  public void displayWindow() {
    this.setVisible(true);
  }

  /**
   * Sets the previous button to a command that will take to previous snapshot if possible.
   * @param command command
   */
  @Override
  public void setPreviousCommand(ICommand command) {
    btnPrev.addActionListener(evt -> command.execute());
  }

  /**
   * Sets the select button to a command that pops up all snapshots and
   * allows user to choose from them.
   * @param command command
   */
  @Override
  public void setSelectCommand(ICommand command) {
    btnSelect.addActionListener(evt -> command.execute());
  }

  /**
   * Sets the next button to a command that will take to next snapshot if possible.
   * @param command command
   */
  @Override
  public void setNextCommand(ICommand command) {
    btnNext.addActionListener(evt -> command.execute());
  }

  /**
   * Sets the quit button to a command that will exit the screen.
   * @param command command
   */
  @Override
  public void setQuitCommand(ICommand command) {
    btnQuit.addActionListener(evt -> command.execute());
  }

  /**
   * Displays a message, specifically important for letting
   * the client know of any errors that may occur.
   * @param message message
   * @throws IllegalArgumentException if message is empty or null
   */
  public void displayMessage(String message) {
    if (message == null || message.isEmpty()) {
      throw new IllegalArgumentException("Message cannot be empty or null!");
    }
    JOptionPane.showMessageDialog(this.shapePanel, message);
  }

  /**
   * Closes the window under any other circumstance such as error handling or
   * when client presses the quit button.
   */
  public void closeWindow() {
    this.dispose();
  }

  /**
   * Adds the shape to the list of shapes associated with the view and repaints
   * the view.
   * @param shape shape type
   * @param x x
   * @param y y
   * @param xLength x length
   * @param yLength y length
   * @param r r
   * @param g g
   * @param b b
   */
  public void drawShape(String shape, double x, double y,
                        double xLength, double yLength, int r, int g, int b) {
    this.shapes.add(new ShapeNode(shape, x, y, xLength, yLength, r, g, b));
    shapePanel.repaint();
  }

  /**
   * Generates a selection menu with options based off of the values of the list
   * passed in.
   * @param selections selections
   * @param index index of starting option
   * @return
   */
  public String displaySelectionMenu(List selections, int index) {
    Object[] possibilities = selections.toArray();
    String s = (String) JOptionPane.showInputDialog(null,
            "Choose a screenshot!", "Select Screenshot",
            JOptionPane.PLAIN_MESSAGE,
            null,
            possibilities,
            possibilities[index]);
    return s;
  }

  /**
   * Draws the label at the top of the window with id and description (if available).
   * @param id id
   * @param description description
   */
  public void drawLabel(String id, String description) {
    this.label.setText(id + "  " + description);
  }

  /**
   * Clears all the shapes off the panel to redraw.
   */
  public void clearShapes() {
    this.shapes.clear();
    this.repaint();
  }
}
