package controller.command;

/**
 * A class that designates the target and functionality of the quit button.
 */
public class QuitCommand implements ICommand{
  private ICommandDelegate target;

  /**
   * Sets the target to the passed target for the constructor.
   * @param target target
   */
  public QuitCommand(ICommandDelegate target) {
    this.target = target;
  }

  /**
   * Invokes the quit functionality of the target.
   */
  public void execute() {
    this.target.quit();
  }
}
