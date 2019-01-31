
public class CreateFurnitureButton extends Button implements DormGUI{
    // Creates the new object
    public CreateFurnitureButton(String type, float x, float y, PApplet processing) {
        super(x,y,processing);
        super.label = type;
    }
    @Override
    // Checks to see if the mouse is on the object
    public void mouseDown(Furniture[] furniture) {
        // checks to see if the mouse is over the object
        if (super.isMouseOver() == true) {
            for (int x = 0; x < furniture.length; x++) {
                // Check for a null value in the furniture array
                if (furniture[x] == null) {
                    String fileName = super.label.toLowerCase();
                    // Adds a bed if bed button clicked
                    furniture[x] = new Furniture(fileName, processing); 
                    break;
                }
            }
        }
    }
}
