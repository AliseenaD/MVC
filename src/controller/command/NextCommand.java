package controller.command;

/**
 * A class that designates the target and functionality of the next button.
 */
public class NextCommand implements ICommand {
  private ICommandDelegate target;

  /**
   * Constructor that sets the target to the passed value.
   * @param target target
   */
  public NextCommand(ICommandDelegate target) {
    this.target = target;
  }

  /**
   * Executes the next function of the target.
   */
  public void execute() {
    this.target.next();
  }
}
