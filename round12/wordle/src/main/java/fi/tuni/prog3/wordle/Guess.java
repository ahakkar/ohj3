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

import fi.tuni.prog3.wordle.GuessResult;

/**
 * Grades a guess and contains the results
 */
public class Guess {

    private String guessedWord;
    private String correctWord;

    int correctLetters = 0;
    int misplacedLetters = 0;
    int wrongLetters = 0;

    private ArrayList<GuessResult> result;


    public Guess(String guessedWord, String correctWord) {
        this.result = new ArrayList<GuessResult>(Collections.nCopies(guessedWord.length(), null));
        this.guessedWord = guessedWord;        
        this.correctWord = correctWord;
    }

    public void gradeGuess() {
        correctLetters = 0;
        misplacedLetters = 0;
        wrongLetters = 0;
    
        // Iterate through each character in the current word
        for (int i = 0; i < guessedWord.length()-1; i++) {
            char currentChar = guessedWord.charAt(i);
            char correctChar = guessedWord.charAt(i);
    
            // The current letter is correct
            if (currentChar == correctChar) {                
                result.set(i, GuessResult.CORRECT);
                correctLetters++;
            }
            // The current letter is in the correct word, but in the wrong position
            else if (correctWord.indexOf(currentChar) >= 0) {                
                result.set(i, GuessResult.MISPLACED);
                misplacedLetters++;
            } 
            // The current letter is completely wrong
            else {                
                result.set(i, GuessResult.WRONG);
                wrongLetters++;
            }
        }
    }    

    public ArrayList<GuessResult> getResult() {
        return result;
    }
}
