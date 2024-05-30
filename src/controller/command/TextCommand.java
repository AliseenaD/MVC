package controller.command;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.IAlbum;

/**
 * A class for IAlbum controller. Reads a passed text file and alters model based off
 * of that.
 */
public class TextCommand implements ICommand {
  private IAlbum model;
  private List<String> functions = new ArrayList<>();
  private BufferedReader reader;
  private String file;

  /**
   * A constructor that takes in a file and model. The file will be given by an overarching
   * controller.
   * @param model model
   * @param file file
   */
  public TextCommand(IAlbum model, String file) {
    String[] commands = {"shape", "move", "color", "resize", "remove", "snapshot"};
    functions.addAll(List.of(commands));
    this.model = model;
    this.file = file;
  }

  /**
   * Reads through the file and processes the alterations within the file.
   */
  @Override
  public void execute() {
    try {
      this.reader = new BufferedReader(new FileReader(this.file));
      String line;
      // Read through each line
      while ((line = this.reader.readLine()) != null) {
        // Strip of any white space before or after
        String s= line.strip();
        // Split into words
        String[] words = s.split("\\s+");
        // If line starts with # then move to next line
        if (words[0].contentEquals("#")) {
          continue;
        }
        // If line starts with one of the function words
        else if (this.functions.contains(words[0].toLowerCase())) {
          // get first word
          String funct = this.functions.get(this.functions.indexOf(words[0].toLowerCase()));
          switch (funct) {
            case "shape":
              this.model.createShape(words[1],words[2],Double.parseDouble(words[3]),
                      Double.parseDouble(words[4]), Integer.parseInt(words[5]),
                      Integer.parseInt(words[6]),
                      Integer.parseInt(words[7]),Integer.parseInt(words[8]),
                      Integer.parseInt(words[9]));
              break;
            case "move":
              this.model.changeLocation(words[1],
                      Double.parseDouble(words[2]), Double.parseDouble(words[3]));
              break;
            case "color":
              this.model.changeColor(words[1], Integer.parseInt(words[2]),
                      Integer.parseInt(words[3]), Integer.parseInt(words[4]));
              break;
            case "resize":
              this.model.changeXSize(words[1], Double.parseDouble(words[2]));
              this.model.changeYSize(words[1], Double.parseDouble(words[3]));
              break;
            case "remove":
              this.model.remove(words[1]);
              break;
            case "snapshot":
              // Check to see if there is a description by looking if index is greater than 1
              if (words.length == 1) {
                this.model.snapshot("");
              }
              // Else add up each successive word into string and use that as description
              else {
                String total = "";
                for (int i=1;i<words.length;i++) {
                  total += words[i] + " ";
                }
                this.model.snapshot(total);
                break;
              }
          }
        }
      }
      this.reader.close();
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
