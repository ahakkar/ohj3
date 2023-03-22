package fi.tuni.prog3.wordle;
/*
 * COMP.CS.140 Ohjelmointi 3
 * H12 Wordle
 *
 * Antti Hakkarainen / K79735
 * antti.i.hakkarainen@tuni.fi 
 */

import java.util.ArrayList;
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
    private WordleGUI GUI = null;
    private boolean gameOver = false;

    private Integer attempts = 0;
    private String currentWord = "";
    private Integer correctWordLength = -1;
    private String correctWord = "";
    private WordRepository repo;

    private WordleCore wc;    

    private ArrayList<Guess> guesses;


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

    public void setCoreInstance(WordleCore wc) {
        this.wc = wc;
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
        GUI = new WordleGUI(root, scene, correctWordLength);

        setGameOver(false);

        // bind props so core knows when user does something in GUI
        wc.bindToCurrentWord(GUI.currentWordProperty());
        wc.bindToEnterKeyPressed(GUI.enterKeyPressedProperty());
        wc.bindToNewGameButtonPressed(GUI.newGameButtonPressedProperty());
    }


    /**
     * Returns the length of the correct word.
     * @return Integer length of the correct word
     */
    public Integer getCorrectWordLength() {
        return correctWordLength;
    }


    /**
     * Returns the correct word.
     * @return String the correct word
     */
    public String getCorrectWord() {
        return correctWord;
    }


    /**
     * Check's the user's guess    
     */
    public void checkAGuess() {
        attempts++;

        Guess guess = new Guess(currentWord, correctWord);
        guess.gradeGuess();  
        GUI.updateRowAfterGuess(guess.getResult());  

        // check if guess was correct
        if(guess.isCorrect()) {
            GUI.setInfoMsgLabel("Congratulations, you won!"); 
            setGameOver(true);
        }
        // otherwise check if game was lost
        else if (attempts == Constants.MAX_ROWS) {
            GUI.setInfoMsgLabel("Game over, you lost!"); 
            setGameOver(true);
        }
        else {
            GUI.setInfoMsgLabel("Try again!"); 
        }

        guesses.add(guess);          

    }    

    private void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
        GUI.gameOver = gameOver;
    }
}
