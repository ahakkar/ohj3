package fi.tuni.prog3.wordle;
/*
 * COMP.CS.140 Ohjelmointi 3
 * H12 Wordle
 *
 * Antti Hakkarainen / K79735
 * antti.i.hakkarainen@tuni.fi 
 */

import java.util.ArrayList;

/**
 * WordleCore handles the logic of the game.
 */
public class WordleCore {

    private Integer correctWordLength = -1;
    private String correctWord = "";
    private WordRepository repo;
    private static final String WORDLE_FILE = "words.txt";

    private ArrayList<Guess> guesses = new ArrayList<Guess>();

    /**
     * Constructor for WordleCore, calls the WordRepository
     * which reads the wordle word file and handles words.
     */
    public WordleCore() {
        repo = new WordRepository(WORDLE_FILE);
        correctWord = repo.getNextWord();
        correctWordLength = correctWord.length();
        printDebugMessages();
    }

    /**
     * Returns the length of the correct word.
     * @return Integer length of the correct word
     */
    public Integer getWordLength() {
        return correctWordLength;
    }

    /**
     * Returns the word.
     * @return String the correct word
     */
    public String getCorrectWord() {
        return correctWord;
    }

    private void printDebugMessages() {
        System.out.println("Word length: " + correctWordLength);
        System.out.println("Word: " + correctWord);
    }

    /**
     * Updates the LetterTile objects based on the guess results.
     */
    public Guess gradeAGuess(String currentWord, String correctWord) {
        Guess guess = new Guess(currentWord, correctWord);
        guess.gradeGuess();  
        guesses.add(guess);  

        return guess;
    }
    
}
