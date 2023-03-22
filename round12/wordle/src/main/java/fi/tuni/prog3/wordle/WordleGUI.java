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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Wordle's GUI class. A new instance is created for each new game.
 */
public class WordleGUI extends Pane {

    private Scene scene;
    private Pane root;   
    private GridPane letterGrid;
    private Label infoMsgLabel;
    private Button startNewGameButton;

    private Integer correctWordLength = -1; 
    private Integer current_col = 0;
    private Integer current_row = 0;
    protected boolean gameOver = false;

    private StringProperty currentWord;
    private BooleanProperty enterKeyPressed;
    private BooleanProperty newGameButtonPressed;
    
    /**
     * Constructor. Creates a new WordleGUI instance, setups the game 
     * window and adds event handlers so the GUI can communicate with Core
     * about user input.
     * @param root The root pane.
     * @param scene The scene.
     */
    public WordleGUI(VBox root, Scene scene, Integer correctWordLength) {
        this.root = root;
        this.scene = scene; 

        this.correctWordLength = correctWordLength;
        // fill the string property with correctwordlength spaces    
        this.currentWord = new SimpleStringProperty(" ".repeat(correctWordLength)); 
        this.enterKeyPressed = new SimpleBooleanProperty(false); 
        this.newGameButtonPressed = new SimpleBooleanProperty(false); 
        
        setupWindowSize();
        setupWindowStyle();        
        setupLetterGrid(); 
        setupGameControls();  
        addKeyEventHandlers();  

        // highlight the initial tile
        getLetterTileAt(0, 0).higlightTile(true);
    }


    /**
     * Add an event handler to the scene to listen for key events
     * and update the LetterTile objects accordingly.
     */
    private void addKeyEventHandlers() {   
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {  
                if (gameOver) {
                    return;
                }

                // System.out.println("Key pressed: " + event.getText() + " " + event.getCode());
                if (event.getCode().isLetterKey()) {                    
                    handleLetterKey(event);                                      
                }
                // backspace remvoes the selected letter and moves 1 letter to the left
                else if (event.getCode() == KeyCode.BACK_SPACE) {
                    if (current_col > 0) {
                        handleBackspaceKey(event);
                    }
                } 
                // delete key removes the selected letter and doesn't move
                else if (event.getCode() == KeyCode.DELETE) {   
                    handleDeleteKey(event);
                                        
                }
                // enter grades the guess, colors the correct letters green and the incorrect letters orange
                else if (event.getCode() == KeyCode.ENTER) {
                    //System.out.println(getCurrentWord() + " " + getCurrentWord().length());
                    if (!getCurrentWord().contains(Constants.EMPTY_STR)) {
                        handleEnterKey(event);
                    } 
                    else {
                        infoMsgLabel.setText("Give a complete word before pressing Enter!");
                    }                                   
                }
                // switch through tiles left and right with left/right arrow keys
                else if (event.getCode() == KeyCode.LEFT) {
                    if (current_col > 0) {
                        getLetterTileAt(current_col, current_row).higlightTile(false);
                        current_col--;
                        getLetterTileAt(current_col, current_row).higlightTile(true);
                    }                    
                }
                else if (event.getCode() == KeyCode.RIGHT) {
                    if (current_col < correctWordLength - 1) {
                        getLetterTileAt(current_col, current_row).higlightTile(false);
                        current_col++;
                        getLetterTileAt(current_col, current_row).higlightTile(true);
                    }                    
                }
            }
        });
    }

    /**
     * @return the currentWordProperty.
     */
    public StringProperty currentWordProperty() {
        return currentWord;
    }

    /**
     * When user presses enter key, send info to WordleCore about it.
     * @return
     */
    public BooleanProperty enterKeyPressedProperty() {
        return enterKeyPressed;
    }

    /**
     * Find the LetterTile object at grid coordinates x, y
     * @param gridPane the GridPane to search
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the LetterTile object at the given coordinates, or null if not found
     */
    public LetterTile getLetterTileAt(int x, int y) {    
        for (Node node : letterGrid.getChildren()) {
            if (node instanceof LetterTile) {
                LetterTile letterTile = (LetterTile) node;

                if (GridPane.getColumnIndex(letterTile) == x &&
                    GridPane.getRowIndex(letterTile) == y)
                {
                    return letterTile;
                }
            }
        }
        return null;
    }

 
    /**
     * 
     * @return
     */
    private String getCurrentWord() {
        return currentWord.get();
    }


    /**
     * When user presses backspace or delete keys, remove the last typed letter.
     * @param event
     */
    private void handleBackspaceKey(KeyEvent event) {
        // replace 1 char from the current_col position of the string with Constants.EMPTY_STR
        setCurrentWord(getCurrentWord().substring(0, current_col) + Constants.EMPTY_STR + getCurrentWord().substring(current_col + 1));

        // de-higlight current tile and erase its letter
        getLetterTileAt(current_col, current_row).higlightTile(false);
        updateLetterTile(
            current_col,
            current_row,
            Constants.EMPTY_STR, 
            Constants.COLOR_TEXT_UNGRADED, 
            Constants.COLOR_TILE_UNGRADED
            );  
        current_col--;

        // higlight the new selected tile
        getLetterTileAt(current_col, current_row).higlightTile(true);     
    }

    private void handleDeleteKey(KeyEvent event) {
        // replace 1 char from the current_col position of the string with Constants.EMPTY_STR
        setCurrentWord(getCurrentWord().substring(0, current_col) + Constants.EMPTY_STR + getCurrentWord().substring(current_col + 1));

        updateLetterTile(
            current_col,
            current_row,
            Constants.EMPTY_STR, 
            Constants.COLOR_TEXT_UNGRADED, 
            Constants.COLOR_TILE_UNGRADED
            );  
    }


    /**
     * When user presses enter key, grade the guess and color the correct 
     * letters green and the incorrect letters orange, partial hits are colored grey.
     * @param event
     */
    private void handleEnterKey(KeyEvent event) {
        setEnterKeyPressed(true);
        setCurrentWord(" ".repeat(correctWordLength));

        // de-highlight the old tile before switching rows
        getLetterTileAt(current_col, current_row).higlightTile(false);
        current_col = 0;
        current_row++;

        // highlight the first tile of the row
        getLetterTileAt(0, current_row).higlightTile(true);
    }

    private static String replaceCharAtIndex(String input, String replacement, Integer index) {
        if (index < 0 || index >= input.length()) {
            throw new IllegalArgumentException("Index out of range: " + index + " for string: " + input); 
        }
        return input.substring(0, index) + replacement + input.substring(index + 1);
    }


    /**
     * When use presses a letter key, add the letter to the current word.
     * @param event
     */
    private void handleLetterKey(KeyEvent event) {
        String inputChar = event.getText();

        // add the typed letter to the current word
        setCurrentWord(replaceCharAtIndex(getCurrentWord(), inputChar.toLowerCase(), current_col));
        updateLetterTile(
            current_col,
            current_row,
            inputChar,
            Constants.COLOR_TEXT_UNGRADED, 
            Constants.COLOR_TILE_UNGRADED
            );

        // if the current word has no empty spaces, do not move to next column
        if (getCurrentWord().contains(Constants.EMPTY_STR)
            && (current_col < correctWordLength - 1)) 
            {
            // de-higlight current tile and erase its letter
            getLetterTileAt(current_col, current_row).higlightTile(false);
            current_col++;

            // higlight the new selected tile
            getLetterTileAt(current_col, current_row).higlightTile(true);
        }
            
 
    }


    /**
     * Sends info to Core that new game button was pressed.
     * @return
     */
    public BooleanProperty newGameButtonPressedProperty() {
        return newGameButtonPressed;
    }


    /**
     * Renders wordLength boxes to a row, each box containing a letter from the word.
     * There are N rows total.
     */
    private void renderLetterBoxes() {
        for (int row = 0; row < Constants.MAX_ROWS; row++) {
            for (int col = 0; col < correctWordLength; col++) {
                // put a letter only to the first row
                try {
                    LetterTile lt = new LetterTile(
                        Constants.EMPTY_STR,
                        col,
                        row, 
                        Constants.TILE_SIZE
                        );                     
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
     * Sets the current word StringProperty.
     * @param word
     */
    public void setCurrentWord(String word) {
        currentWord.set(word);
    }


    /**
     * sets the enterKeyPressed Booleanproperty.
     * @param value
     */
    public void setEnterKeyPressed(boolean value) {
        enterKeyPressed.set(value);
    }


    /**
     * Sets the game info label.     
     * @param msg The message to be displayed.
     */
    public void setInfoMsgLabel(String msg) {
        infoMsgLabel.setText(msg);
    }


    /**
     * Sets the newGameButtonPressed BooleanProperty.
     * @param value
     */
    public void setNewGameButtonPressed(boolean value) {
        newGameButtonPressed.set(value);
    }


    /**
     * Setup the game controls - start new game button, infomsglabel.
     */
    private void setupGameControls() {
        // A start button which restarts the game with a new word.

        HBox infoHBox = new HBox();
        infoHBox.setPadding(new Insets(Constants.GAP));
        infoHBox.setSpacing(Constants.GAP);
        startNewGameButton = new Button("Start new game");   
        
        // prevent the button from being activated when user presses enter
        startNewGameButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {           
                    event.consume();
                }
            }
        });
        // above might not be neccessary, but at least this helps!
        startNewGameButton.setFocusTraversable(false);
        startNewGameButton.setId("newGameBtn");
        startNewGameButton.setOnAction(event -> setNewGameButtonPressed(true));
       

        // A label which shows status messages like game won etc.
        infoMsgLabel = new Label("");
        infoMsgLabel.setId("infoBox");

        infoHBox.getChildren().addAll(startNewGameButton, infoMsgLabel);
        root.getChildren().add(infoHBox);

    }
      

    /**
     * Sets up the window size.
     */
    private void setupWindowSize() {
        int windowWidth = correctWordLength * Constants.TILE_SIZE + 
            Constants.X_PADDING + (correctWordLength + 1)* Constants.GAP;

        int windowHeight = Constants.MAX_ROWS * Constants.TILE_SIZE + 
            Constants.Y_PADDING + (Constants.MAX_ROWS + 1)* Constants.GAP * 2;

        // System.out.println(windowWidth + "x" + windowHeight);
        scene.getWindow().setWidth(windowWidth);
        scene.getWindow().setHeight(windowHeight);
    }


    /**
     * Sets up the window background color.
     */
    private void setupWindowStyle() {
        scene.setFill(Constants.COLOR_GAME_BACKGROUND);;
    }


    /**
     * Sets up the letter grid, adds it to the scene.
     */
    private void setupLetterGrid() {
        letterGrid = new GridPane();
        letterGrid.setPadding(new Insets(Constants.GAP));
        letterGrid.setHgap(Constants.GAP);
        letterGrid.setVgap(Constants.GAP);
        root.getChildren().add(letterGrid);

        renderLetterBoxes();
    }

    
    /**
     * Update a LetterTile at the given coordinates.
     * @param x the x coordinate
     * @param y the y coordinate
     * @param letter the new letter to be displayed      
     */
    public void updateLetterTile(
        int x,
        int y,
        String letter,
        Color charColor, 
        Color tileColor) 
    {
        LetterTile lt = getLetterTileAt(x, y);
        lt.setLetter(letter, charColor);   
        lt.setTileColor(tileColor);
    }

    /**
     * Updates the row's box and text colors after user enters a guess.
     * Boxes are colored based on how correct the letter in corresponding
     * LetterTile object was.
     * @param currentGuess
     */
    public void updateRowAfterGuess(ArrayList<GuessResult> result) {
        for (int col = 0; col < correctWordLength; col++) {
            String letter = (String) getCurrentWord().substring(col, col + 1);            
            if (result.get(col) == GuessResult.CORRECT) {
                updateLetterTile(
                    col,
                    current_row,
                    letter,
                    Constants.COLOR_TEXT_GRADED,
                    Constants.COLOR_TILE_CORRECT
                    );
                // set next row's letter too if there is one to help the player
                /*/
                if (current_row < Constants.MAX_ROWS - 1) {
                    updateLetterTile(
                        col,
                        current_row + 1,
                        letter,
                        Constants.COLOR_TEXT_GRADED,
                        Constants.COLOR_TILE_CORRECT
                        );
                }
                */
            }
            else if (result.get(col) == GuessResult.MISPLACED) {
                updateLetterTile(
                    col,
                    current_row, 
                    letter,
                    Constants.COLOR_TEXT_GRADED, 
                    Constants.COLOR_TILE_PARTIAL
                    );
            }
            else if (result.get(col) == GuessResult.WRONG) {
                updateLetterTile(
                    col,
                    current_row, 
                    letter,
                    Constants.COLOR_TEXT_GRADED,
                    Constants.COLOR_TILE_WRONG
                    );
            }
        }  
    }
}
