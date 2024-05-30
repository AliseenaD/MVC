package controller.command;

/**
 * A class that designates the target and functionality of the select button.
 */
public class SelectCommand implements ICommand {
  private ICommandDelegate target;

  /**
   * A constructor that assigns a target.
   * @param target target
   */
  public SelectCommand(ICommandDelegate target) {
    this.target = target;
  }

  /**
   * Invokes the target to execute the select function.
   */
  public void execute() {
    this.target.select();
  }
}
