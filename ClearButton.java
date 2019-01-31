
public class ClearButton extends Button implements DormGUI {
    public ClearButton(float x, float y, PApplet processing) {
        super(x,y,processing);
        super.label = "Clear Room";
    }
    public void mouseDown(Furniture[] furniture) {
        if (super.isMouseOver() == true) {
            for (int x = 0; x < furniture.length; x++) {
                furniture[x] = null;
            }
        }
    }
}
