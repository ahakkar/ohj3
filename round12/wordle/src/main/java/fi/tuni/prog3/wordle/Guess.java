package fi.tuni.prog3.wordle;
/*
 * COMP.CS.140 Ohjelmointi 3
 * H12 Wordle
 *
 * Antti Hakkarainen / K79735
 * antti.i.hakkarainen@tuni.fi 
 */

import java.util.ArrayList;
import java.util.Collections;

/**
 * Grades a guess and contains the results
 */
public class Guess {

    private String guessedWord;
    private String correctWord;
    private int correctLetters = 0;
    private boolean isCorrect = false;
    private ArrayList<GuessResult> result;

    /**
     * Constructor for Guess, initializes an empty result list with the length 
     * of the correct word. Each index holds one letter.
     * @param guessedWord
     * @param correctWord
     */
    public Guess(String guessedWord, String correctWord) {
        this.result = new ArrayList<GuessResult>(Collections.nCopies(correctWord.length(), null));
        this.guessedWord = guessedWord;        
        this.correctWord = correctWord;
    }
   

    /**
     * Returns the result of the guess
     * @return ArrayList<GuessResult> result
     */
    public ArrayList<GuessResult> getResult() {
        return result;
    }


    /**
     * Grades the guess and fills the result list with the results.
     * Also sets the isCorrect variable to true if the guess was 100% correct.
     * Each letter is graded to be wrong, correct or misplaced.
     */
    public void gradeGuess() {
    
        // Iterate through each character in the current word
        for (int i = 0; i < correctWord.length(); i++) {
            char currentChar = guessedWord.charAt(i);
            char correctChar = correctWord.charAt(i);
    
            // The current letter is correct
            if (currentChar == correctChar) {                
                result.set(i, GuessResult.CORRECT);
                correctLetters++;
            }
            // The current letter is in the correct word, but in the wrong position
            else if (correctWord.indexOf(currentChar) >= 0) {                
                result.set(i, GuessResult.MISPLACED);
            } 
            // The current letter is completely wrong
            else {                
                result.set(i, GuessResult.WRONG);
            }
        }

        if (correctLetters == correctWord.length()) {
            isCorrect = true;
        }
    } 


    /**
     * Returns if the guess was correct or incorrect
     * @return boolean
     */
    public boolean isCorrect() {
        return isCorrect;
    }
}
