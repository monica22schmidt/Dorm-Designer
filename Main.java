//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P04 Dorm Designer 4000
// Files: N/A
// Course: CS 300, Spring, and 2018
//
// Author: Monica Schmidt
// Email: meschmidt6@wisc.edu
// Lecturer's Name: Alexi Brooks
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates
// strangers, etc do. If you received no outside help from either type of
// source, then please explicitly indicate NONE.
//
// Persons: None
// Online Sources: Piazza
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.ArrayList;

public class Main {
    private PApplet processing;
    private PImage backgroundImage;
    private ArrayList<DormGUI> guiObjects;

    public static void main(String[] args) {
        Utility.startApplication();
    }

    // Max number of furniture that LoadButton will be allowed to load at once.
    private final static int MAX_LOAD_FURNITURE = 100;

    /**
     * This method creates a new Furniture[] for the old mouseDown() methods to make use of. It does
     * so by copying all Furniture references from this.guiObjects into a temporary array of size
     * MAX_LOAD_FURNITURE.
     * 
     * @return that array of Furniture references.
     */
    private Furniture[] extractFurnitureFromGUIObjects() {
        Furniture[] furniture = new Furniture[MAX_LOAD_FURNITURE];
        int nextFreeIndex = 0;
        for (int i = 0; i < guiObjects.size() && nextFreeIndex < furniture.length; i++)
            if (guiObjects.get(i) instanceof Furniture)
                furniture[nextFreeIndex++] = (Furniture) guiObjects.get(i);
        return furniture;
    }

    /**
     * This method first removes all Furniture references from this.guiObjects, and then adds back
     * in all of the non-null references from it's parameter.
     * 
     * @param furniture contains the only furniture that will be left in this.guiObjects after this
     *        method is invoked (null references ignored).
     */
    private void replaceFurnitureInGUIObjects(Furniture[] furniture) {
        for (int i = 0; i < this.guiObjects.size(); i++)
            if (this.guiObjects.get(i) instanceof Furniture)
                this.guiObjects.remove(i--);
        for (int i = 0; i < furniture.length; i++)
            if (furniture[i] != null)
                this.guiObjects.add(furniture[i]);
    }

    /*
     * This method starts up the pop up window. It initializes the bedPosition array. It also loads
     * and sizes the images for the screen and creates and sizes the first bed.
     */
    public Main(PApplet processing) {
        this.processing = processing;
        this.backgroundImage = processing.loadImage("images/background.png");
        this.guiObjects = new ArrayList<DormGUI>();
        this.guiObjects.add(new CreateFurnitureButton("Bed",50, 24, processing));
        this.guiObjects.add(new CreateFurnitureButton("Sofa",150, 24, processing));
        this.guiObjects.add(new CreateFurnitureButton("Dresser",250, 24, processing));
        this.guiObjects.add(new CreateFurnitureButton("Desk",350, 24, processing));
        this.guiObjects.add(new CreateFurnitureButton("Sink",450, 24, processing));
        this.guiObjects.add(new ClearButton(550, 24, processing));
        this.guiObjects.add(new SaveButton(650, 24, processing));
        this.guiObjects.add(new LoadButton(750, 24, processing));
    }

    /*
     * This method if responsible for constantly updating the screen It puts in the background color
     * and picture, it also places bed and allows them to be dragged on the screen.
     */
    public void update() {
        processing.background(100, 150, 250);
        processing.image(backgroundImage, processing.width / 2, processing.height / 2);
        for (int i = 0; i < this.guiObjects.size(); i++) {
            this.guiObjects.get(i).update();
        }

    }

    /*
     * This method checks to see the mouse is being clicked if it is it finds which furniture
     * matches the index that the mouse is touching. It also creates new bed and new couches when
     * the buttons are pressed
     */
    public void mouseDown() {
        Furniture[] furniture = extractFurnitureFromGUIObjects();;
        for (int element = this.guiObjects.size() -1; element >= 0; element--) {
            furniture = extractFurnitureFromGUIObjects();
            if (guiObjects.get(element).isMouseOver()) {
                guiObjects.get(element).mouseDown(furniture);
                break;
            }

        }
        replaceFurnitureInGUIObjects(furniture);
    }

    /*
     * This method checks to see if the mouse is no longer being clicked It changes the drag index
     * to -1 so it will no longer be dragged by the mouse
     */
    public void mouseUp() {
        // Iterates through the guiobjects
        for (int x = this.guiObjects.size() - 1; x >= 0; x--) {
            // make sure the object is not null
            if (this.guiObjects.get(x) instanceof Furniture) {
                // puts down the furniture
                this.guiObjects.get(x).mouseUp();
            }
        }
    }

    /*
     * This method checks to see if the d or D key is clicked by the user. If it is clicked it
     * deletes a furniture object from the array for. Also checks if r or R is clicked if so the
     * furniture rotates
     */
    public void keyPressed() {
        Furniture[] furniture = extractFurnitureFromGUIObjects();
        int furnCount = furniture.length-1;
        // Checks for user input
        if (processing.key == 'd' || processing.key == 'D') {
            // Iterates through the furniture objects
            for (int x = guiObjects.size() - 1; x >= 0; x--) {
                // Checks to make sure there is a value
                if (this.guiObjects.get(x) instanceof Furniture) {
                    // checks to see if the mouse is over the object
                    if (this.guiObjects.get(x).isMouseOver()) {
                        // deletes object
                        this.guiObjects.remove(x);
                        furniture[furnCount] = null;
                        furnCount--;
                    }

                }
            }
        }
        furnCount = guiObjects.size()-1;
        if (processing.key == 'r' || processing.key == 'R') {
            // Iterates through the furniture objects
            for (int x = guiObjects.size() - 1; x >= 0; x--) {
                // Checks to make sure there is a value
                if (this.guiObjects.get(x) instanceof Furniture) {
                    // checks to see if the mouse is over the object
                    if (this.guiObjects.get(x).isMouseOver() == true) {
                        // rotates object
                        furniture[furnCount] = (Furniture)(this.guiObjects.get(x));
                        furniture[furnCount].rotate();
                        furnCount--;
                    } 
                }
            }
        }
    }
}
