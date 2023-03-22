package fi.tuni.prog3.wordle;
/*
 * COMP.CS.140 Ohjelmointi 3
 * H12 Wordle
 *
 * Antti Hakkarainen / K79735
 * antti.i.hakkarainen@tuni.fi 
 */

import java.util.ArrayList;

import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import javafx.beans.property.StringProperty;
import javafx.beans.property.BooleanProperty;

/**
 * WordleCore handles the logic of the game.
 */
public class WordleCore {

    private Scene scene;
    private VBox root;
    private WordleGUI currentGameInstance = null;
    private boolean gameOver = false;

    private Integer attempts = 0;
    private String currentWord = "";
    private Integer correctWordLength = -1;
    private String correctWord = "";


    private WordRepository repo;
    private WordleCore wc;  
    private ArrayList<Guess> guesses; // actually these are not used for anything


    /**
     * Constructor for WordleCore, calls the WordRepository
     * which reads the wordle word file and handles words.
     */
    public WordleCore(VBox root, Scene scene) {
        this.root = root;
        this.scene = scene; 

        repo = new WordRepository(Constants.WORDLE_FILE);
    }


    /**
     * Binds the currentWordProperty to the currentWord variable.
     * @param currentWordProperty the property to be bound
     */
    public void bindToCurrentWord(StringProperty currentWordProperty) {
        currentWordProperty.addListener((obs, oldValue, newValue) -> {
            currentWord = newValue;
        });
    }

    /**
     * Binds the enterKeyPressedProperty to the gradeAGuess method.
     * @param enterKeyPressedProperty the property to be bound 
     */
    public void bindToEnterKeyPressed(BooleanProperty enterKeyPressedProperty) {
        enterKeyPressedProperty.addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                if (!gameOver) {
                    this.checkAGuess();
                }
                enterKeyPressedProperty.set(false);
            }
        });
    }

    /**
     * Binds the newGameButtonPressedProperty to the startNewGame method.
     * @param newGameButtonPressedProperty the property to be bound
     */
    public void bindToNewGameButtonPressed(BooleanProperty newGameButtonPressedProperty) {
        newGameButtonPressedProperty.addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                startNewGame();
                // newGameButtonPressedProperty.set(false);
            }
        });
    }


    /**
     * Returns the correct word.
     * @return String the correct word
     */
    public String getCorrectWord() {
        return correctWord;
    }


    /**
     * Returns the length of the correct word.
     * @return Integer length of the correct word
     */
    public Integer getCorrectWordLength() {
        return correctWordLength;
    }


    /**
     * Check's the user's guess    
     */
    public void checkAGuess() {
        attempts++;

        Guess guess = new Guess(currentWord, correctWord);
        guess.gradeGuess();  
        currentGameInstance.updateRowAfterGuess(guess.getResult());  

        // check if guess was correct
        if(guess.isCorrect()) {
            currentGameInstance.setInfoMsgLabel("Congratulations, you won!"); 
            setGameOver(true);
        }
        // otherwise check if game was lost
        else if (attempts == Constants.MAX_ROWS) {
            currentGameInstance.setInfoMsgLabel("Game over, you lost!"); 
            setGameOver(true);
        }
        else {
            currentGameInstance.setInfoMsgLabel("Try again!"); 
        }

        guesses.add(guess);          

    }    

    public void setCoreInstance(WordleCore wc) {
        this.wc = wc;
    }


    private void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
        currentGameInstance.gameOver = gameOver;
    }


    /**
     * Initially start a new game with the first word picked from list.
     * Starts a new game by picking the next word from the list. 
     * The GUI is also updated.     
     */
    public void startNewGame() {
        attempts = 0; // reset guess attempts
        guesses = new ArrayList<Guess>(); // reset guesses

        correctWord = repo.getNextWord();
        correctWordLength = correctWord.length();

        // Remove the old game instance from the root container
        if (currentGameInstance != null) {    
            if (root instanceof Pane) {
                ((Pane) root).getChildren().clear();
                ((Pane) root).requestLayout(); // Force a layout pass  
            }
        }

        // create a new instance and add it to root
        currentGameInstance = new WordleGUI(root, scene, correctWordLength);
        if (root instanceof Pane) {
            ((Pane) root).getChildren().add(currentGameInstance);
        }

        setGameOver(false);

        // bind props so core knows when user does something in GUI
        wc.bindToCurrentWord(currentGameInstance.currentWordProperty());
        wc.bindToEnterKeyPressed(currentGameInstance.enterKeyPressedProperty());
        wc.bindToNewGameButtonPressed(currentGameInstance.newGameButtonPressedProperty());
    }



}
