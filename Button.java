public class Button {
    private static final int WIDTH = 96;
    private static final int HEIGHT = 32;
    protected PApplet processing;
    private float[] position;
    protected String label;
     
    public Button(float x, float y, PApplet processing) {
        this.processing = processing;
        // creates new array
        this.position = new float[2];
        // initializes array
        this.position[0] = x;
        this.position[1] = y;
        this.label = "Button";
    }
     
    public void update() {
     // Checks to see if isMouseOver is true
        if (isMouseOver() == true) {
            // changes color
            processing.fill(100);
            // Checks to see if isMouseOver is false
        } else if (isMouseOver() == false) {
            // changes color
            processing.fill(200);
        }
        processing.rect((this.position[0]-48), (this.position[1]+16), (this.position[0]+48), this.position[1]-16);
        processing.fill(0);
        processing.text(label, this.position[0], this.position[1]);
    }
    public void mouseDown(Furniture[] furniture) {
        System.out.print("A button was pressed.");
    }
    public void mouseUp() {
        
    }
    public boolean isMouseOver() {
        boolean answer = false;
        // Checks to see if the mouse is within the bound of the object
        if (processing.mouseX > position[0] - WIDTH / 2
                        && processing.mouseX < position[0] + WIDTH / 2
                        && processing.mouseY > position[1] - HEIGHT / 2
                        && processing.mouseY < position[1] + HEIGHT / 2) {
            answer = true;
        }
        return answer;
        
    }
}
