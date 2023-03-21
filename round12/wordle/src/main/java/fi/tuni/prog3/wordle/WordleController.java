package fi.tuni.prog3.wordle;
/*
 * COMP.CS.140 Ohjelmointi 3
 * H12 Wordle
 *
 * Antti Hakkarainen / K79735
 * antti.i.hakkarainen@tuni.fi 
 */

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

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
    }
      

    /**
     * Sets up the window size.
     */
    private void setupWindowSize() {
        System.out.println("Setting up window size..");
        int windowWidth = wordLength * TILE_SIZE + X_PADDING + (wordLength + 2 )* GAP;
        int windowHeight = ROWS * TILE_SIZE + Y_PADDING + (ROWS + 10 )* GAP*2;

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
                    LetterTile lt = new LetterTile(row == 0 ? word.charAt(col) : '\0', col, row, TILE_SIZE);   
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
}
