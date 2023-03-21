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

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import fi.tuni.prog3.wordle.GuessResult;

/**
 * WordleController class.
 */
public class WordleController extends Wordle {

    WordleCore core;
    GridPane letterGrid;
    private Integer wordLength = -1; 
    private String correctWord;
    private String currentWord = "";
    private Scene scene;
    private Pane root;
   
    private Integer X_PADDING = 20;
    private Integer Y_PADDING = 20;

    private static final Integer ROWS = 6;
    private static final Integer TILE_SIZE = 50;
    private static final Integer GAP = 10;
    private static final Character EMPTY_CHAR = '\0';

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
        correctWord = core.getCorrectWord();        
        
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
                // System.out.println("Key pressed: " + event.getText() + " " + event.getCode());
                if (event.getCode().isLetterKey()) {
                    if (current_col < wordLength) {
                        handleLetterKey(event);
                    }                  
                }

                // backspace & delete keys remove the last typed letter
                else if (event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE) {
                    if (current_col > 0) {
                        handleBackspacAndDeleteKeys(event);
                    }                    
                }
                // enter grades the guess, colors the correct letters green and the incorrect letters orange
                else if (event.getCode() == KeyCode.ENTER) {
                    if (current_col == wordLength) {
                        handleEnterKey(event);
                    }                                    
                }  
            }
        });
    }


    /**
     * When use presses a letter key, add the letter to the current word.
     * @param event
     */
    private void handleLetterKey(KeyEvent event) {
        Character inputChar = event.getText().charAt(0);
        // add the typed letter to the current word
        currentWord += Character.toLowerCase(inputChar);
        updateLetterTile(current_col, current_row, inputChar, Color.BLACK, Color.WHITE);
        current_col++;  
    }


    /**
     * When user presses backspace or delete keys, remove the last typed letter.
     * @param event
     */
    private void handleBackspacAndDeleteKeys(KeyEvent event) {
        // remove 1 char from end of current word
        currentWord = currentWord.substring(0, currentWord.length() - 1);
        current_col--;
        updateLetterTile(current_col, current_row, EMPTY_CHAR, Color.BLACK, Color.WHITE);       
    }


    /**
     * When user presses enter key, grade the guess and color the correct 
     * letters green and the incorrect letters orange, partial hits are colored grey.
     * @param event
     */
    private void handleEnterKey(KeyEvent event) {
        Guess result = core.gradeAGuess(currentWord, correctWord);   
        updateRowAfterGuess(result);    

        // check if guess was correct
        if(result.isCorrect) {

        }   

        currentWord = "";
        current_col = 0;
        current_row++;
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
        scene.setFill(Color.rgb(200, 200, 200));;
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

    private void updateRowAfterGuess(Guess currentGuess) {
        ArrayList<GuessResult> result = currentGuess.getResult();
        for (int i = 0; i < wordLength; i++) {
            Character letter = currentWord.charAt(i);            
            if (result.get(i) == GuessResult.CORRECT) {
                updateLetterTile(i, current_row, letter, Color.WHITE, Color.GREEN);
            }
            else if (result.get(i) == GuessResult.MISPLACED) {
                updateLetterTile(i, current_row, letter, Color.WHITE, Color.ORANGE);
            }
            else if (result.get(i) == GuessResult.WRONG) {
                updateLetterTile(i, current_row, letter, Color.WHITE, Color.GREY);
            }
        }  
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
                    LetterTile lt = new LetterTile(EMPTY_CHAR, col, row, TILE_SIZE);   
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
    public void updateLetterTile(int x, int y, Character letter, Color charColor, Color color) {
        LetterTile lt = getLetterTileAt(x, y);
        lt.setLetter(letter, charColor);   
        lt.setTileColor(color);
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
