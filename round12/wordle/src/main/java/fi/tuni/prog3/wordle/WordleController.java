package fi.tuni.prog3.wordle;
/*
 * COMP.CS.140 Ohjelmointi 3
 * H12 Wordle
 *
 * Antti Hakkarainen / K79735
 * antti.i.hakkarainen@tuni.fi 
 */

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * WordleController class.
 */
public class WordleController extends Wordle {

    WordleCore core;
    GridPane letterGrid;
    private Integer wordLength = -1; 
    private String word;
    private Scene scene;
    private Pane root;
    private static final Integer ROWS = 6;
    private static final Integer TILE_SIZE = 50;
    private Integer X_PADDING = 20;
    private Integer Y_PADDING = 20;
    private static final Integer GAP = 10;

    private Integer current_col = 0;
    private Integer current_row = 0;
    
    /**
     * Constructor.
     * @param root The root pane.
     * @param scene The scene.
     */
    public WordleController(Pane root, Scene scene) {
        this.root = root;
        this.scene = scene;

        System.out.println("Initializing wordle core..");
        core = new WordleCore();
        wordLength = core.getWordLength();
        word = core.getWord();        
        
        setupWindowSize();
        setupWindowStyle();
        setupLetterGrid();   
        addKeyEventHandlers();     
    }


    /**
     * Add an event handler to the scene to listen for key events
     * and update the LetterTile objects accordingly.
     */
    private void addKeyEventHandlers() {      
        System.out.println("Key event handler method called..");
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                System.out.println("Key pressed: " + event.getText() + " " + event.getCode());
                if (event.getCode().isLetterKey()) {
                    if (current_col < wordLength) {
                        Character inputChar = event.getText().charAt(0);
                        updateLetterTile(current_col, current_row, inputChar);
                        current_col++;  
                    }                  
                }

                // backspace & delete keys remove the last typed letter
                else if (event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE) {
                    if (current_col > 0) {
                        current_col--;
                        updateLetterTile(current_col, current_row, '\0');
                    }                    
                }
                // enter grades the guess, colors the correct letters green and the incorrect letters orange
                else if (event.getCode() == KeyCode.ENTER) {
                   
                }  
            }
        });
    }
      

    /**
     * Sets up the window size.
     */
    private void setupWindowSize() {
        System.out.println("Setting up window size..");
        int windowWidth = wordLength * TILE_SIZE + X_PADDING + (wordLength + 2 )* GAP;
        int windowHeight = ROWS * TILE_SIZE + Y_PADDING + (ROWS + 1 )* GAP*2;

        System.out.println(windowWidth + "x" + windowHeight);
        scene.getWindow().setWidth(windowWidth);
        scene.getWindow().setHeight(windowHeight);
    }


    /**
     * Sets up the window style.
     */
    private void setupWindowStyle() {
        System.out.println("Setting up window style..");
        scene.setFill(Color.GRAY);;
    }


    /**
     * Sets up the letter grid, adds it to the scene.
     */
    private void setupLetterGrid() {
        letterGrid = new GridPane();
        letterGrid.setPadding(new Insets(GAP));
        letterGrid.setHgap(GAP);
        letterGrid.setVgap(GAP);
        root.getChildren().add(letterGrid);

        renderLetterBoxes();
    }


    /**
     * Renders wordLength boxes to a row, each box containing a letter from the word.
     * There are N rows total.
     */
    private void renderLetterBoxes() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < wordLength; col++) {
                // put a letter only to the first row
                try {
                    LetterTile lt = new LetterTile('\0', col, row, TILE_SIZE);   
                    letterGrid.add(lt.getLetterTile(), col, row);   
                }
                catch (Exception e) {
                    System.out.println("Exception while trying to add a LetterTile: " + e);
                    e.printStackTrace();
                    System.exit(1);
                }   
            }
        }
    }  

    
    /**
     * Update a LetterTile at the given coordinates.
     * @param x the x coordinate
     * @param y the y coordinate
     * @param letter the new letter to be displayed      
     */
    public void updateLetterTile(int x, int y, Character letter) {
        LetterTile lt = getLetterTileAt(x, y);
        lt.setLetter(letter);   
    }


    /**
     *  Find the LetterTile object at grid coordinates x, y
     * @param gridPane the GridPane to search
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the LetterTile object at the given coordinates, or null if not found
     */
    public LetterTile getLetterTileAt(int x, int y) {    
        for (Node node : letterGrid.getChildren()) {
            if (node instanceof LetterTile) {
                LetterTile letterTile = (LetterTile) node;
                if (GridPane.getColumnIndex(letterTile) == x && GridPane.getRowIndex(letterTile) == y) {
                    return letterTile;
                }
            }
        }
        return null;
    }
}
