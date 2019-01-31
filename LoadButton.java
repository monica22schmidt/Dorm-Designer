import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoadButton extends Button implements DormGUI {
    private float[] loadedPosition;
    private String type;
    private int rotations;


    // Creates the new object
    public LoadButton(float x, float y, PApplet processing) {
        super(x,y,processing);
        super.label = "Load Room";
    }

    /*
     * Checks to see if the mouse is on the object and then loads all the furniture It does this by
     * reading the contents of the file with strict rules regarding it
     */
    public void mouseDown(Furniture[] furniture) {
        try {
            // Tries to import a file
            File saveFile = new File("RoomData.ddd");
            // Tries to read the file
            Scanner scr = new Scanner(saveFile);
            // Allows us to count how many items are in the file
            int itemCount = 0;
            // Iterates through the objects in the array
            for (int x = 0; x < furniture.length; x++) {
                // Sets furniture values to null
                furniture[x] = null;
            }
            // Checks to see if the file has another line and loops until it doesn't
            while (scr.hasNextLine()) {
                // Creates a new set of position values for each line
                this.loadedPosition = new float[2];
                // Allows us to see the line brought by the scanner
                String lineRetrieved = scr.nextLine();
                // Turns the line into the an array and takes out the : and ,
                String[] furnitureLoaded = lineRetrieved.replace(":", ",").split(",");
                // Checks to see if the line has four items when turned into an array
                if (furnitureLoaded.length == 4) {
                    // Counts how many items have been created cap at 6 items
                    if (itemCount < 6) {
                        // Gets the type of furniture
                        this.type = furnitureLoaded[0].trim();
                        // Gets the x value
                        this.loadedPosition[0] = Float.parseFloat(furnitureLoaded[1].trim());
                        // Gets the y value
                        this.loadedPosition[1] = Float.parseFloat(furnitureLoaded[2].trim());
                        // Gets number of rotations
                        this.rotations = Integer.parseInt(furnitureLoaded[3].trim());
                        // Creates new furniture with the value of contents of the file
                        furniture[itemCount] = new Furniture(this.loadedPosition, this.rotations,
                                        this.type, this.processing);
                        // Increases the count
                        itemCount++;
                    } else {
                        // Prints out if there are more than 6 items exist in the file
                        System.out.print("WARNING: Unable to load more furniture");
                    }
                } else {
                    // Prints out if the file has more than or less than four items
                    System.out.print("WARNING: Found incorrectly formatted line in file: "
                                    + lineRetrieved);
                }
            }
            scr.close(); // closes the scanner
            // Catches the exception thrown if no file exists.
        } catch (FileNotFoundException e) {
            System.out.print("WARNING: Could not load room contents from file RoomData.ddd.");
        }
    }
}
