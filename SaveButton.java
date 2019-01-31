import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SaveButton extends Button implements DormGUI {
    
    private float[] savePosition;
    private String type;
    private int rotations;

    // Creates the new object
    public SaveButton(float x, float y, PApplet processing) {
        super(x,y,processing);
        super.label = "Save Room";
    }
    @Override
    // Checks to see if the mouse is on the object
    public void mouseDown(Furniture[] furniture) {
        try {
            // Imports a file
            File saveFile = new File("RoomData.ddd");
            // Creates a writer to write to the file
            PrintWriter pw = new PrintWriter(saveFile);
            // Iterates through the furniture array
            for (int x = 0; x < furniture.length; x++) {
                // checks to see if furniture is null
                if (furniture[x] != null) {
                    // Gets the positions from furniture
                    this.savePosition = furniture[x].getPositions();
                    // Gets the type from furniture
                    this.type = furniture[x].getType().trim();
                    // Gets the rotations from furniture
                    this.rotations = furniture[x].getRotations();
                    pw.println(this.type + ":" + savePosition[0] + "," + savePosition[1] + "," + rotations);
                }
            }
            // Closes and flushes the PrintWriter
            pw.close();
            // Catches the exception thrown if no file exists
        } catch (FileNotFoundException e) {
            System.out.print("WARNING: Could not save room contents to file RoomData.ddd.");
        }
    }
}
