package controller.command;

/**
 * A class that designates the target and functionality of the previous button.
 */
public class PreviousCommand implements ICommand {
  private ICommandDelegate target;

  /**
   * A constructor for the PreviousCommand that accepts a target.
   * @param target target
   */
  public PreviousCommand(ICommandDelegate target) {
    this.target = target;
  }

  /**
   * Executes the previous function of the target.
   */
  public void execute() {
    this.target.previous();
  }
}
