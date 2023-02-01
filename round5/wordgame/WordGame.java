/*
 * COMP.CS.140 Ohjelmointi 3
 * H5 Wordgame
 *
 * Antti Hakkarainen / K79735
 * antti.i.hakkarainen@tuni.fi 
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class WordGame {

    protected static class WordGameState {

        private String original_word;
        private String guessed_word;
        private int mistakes;
        private int mistakeLimit;
        private int missingChars;

        protected String getWord() {
            return guessed_word;
        }

        protected String getOriginalWord() {
            return original_word;
        }        

        protected int getMistakes() {
            return mistakes;
        } 
        protected int getMistakeLimit() {
            return mistakeLimit;
        }

        protected int getMissingChars() {
            return missingChars;
        } 

        protected void setOriginalWord(String word) {
            original_word = word;
        }

        protected void setGuessedWord(String word) {
            guessed_word = word;
        }

        protected void incrementMistakes() {
            this.mistakes++;
        }

        protected void setMistakeLimit(int mistakeLimit) {
            this.mistakeLimit = mistakeLimit;
        }

        protected void setMissingChars(int missingChars) {
            this.missingChars = missingChars;
        }
    }

    private ArrayList<String> words;
    private WordGameState gameState;
    private final String ERROR_NO_GAME = "There is currently no active word game!";

    /*Tiedoston oletetaan sisältävän yhden sanan per rivi ilman ylimääräistä 
    tyhjää tilaa.

    Rakentimen tulee tallettaa sanat WordGame-olioon samassa järjestyksessä
    kuin ne ovat tiedostossa (esim. ArrayList<String> lienee sopiva tietorakenne).

    Alempana viitataan sanan indeksiin. Ensimmäisen luetun sanan indeksi on 0,
    toiseksi luetun sanan indeksi on 1, jne. Lisäksi alempana oletetaan, että 
    sanojen kokonaislukumäärä on N.
    */
    public WordGame(String wordFilename) {
        words = new ArrayList<String>();
        File file = new File(wordFilename);
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                words.add(sc.nextLine());
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } 
    }

    public void initGame(int wordIndex, int mistakeLimit) {
        gameState = new WordGameState();
        try {
            gameState.setOriginalWord(words.get(wordIndex % words.size()));
            gameState.setMistakeLimit(mistakeLimit);;
            gameState.setMissingChars(count_unique_chars(gameState.getOriginalWord()));
            gameState.guessed_word = "_".repeat(gameState.original_word.length());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: " + e.getMessage());
        }        
    }

    private int count_unique_chars(String word) {
        HashSet<Character> set = new HashSet<Character>(); 
        for (var ch : word.toCharArray()) {
            set.add(ch);
        }
        return set.size();
    }

    public boolean isGameActive() {
        if (gameState == null) {
            return false;
        }
        return true;
    }

    public WordGameState getGameState() throws GameStateException {
        if (gameState == null) {
            throw new GameStateException(ERROR_NO_GAME);
        }
        return this.gameState;  
    }

    public WordGameState guess(char c) throws GameStateException {
        int i = 0;
        int count_missing = 0;
        boolean correct_guess = false;
        Character ch = c;
        ch = Character.toLowerCase(ch);

        if (gameState == null) {
            throw new GameStateException(ERROR_NO_GAME);
        } 

        while (i < gameState.getOriginalWord().length()) {
            if (gameState.getOriginalWord().charAt(i) == ch) {
                if (gameState.getWord().charAt(i) == '_') {
                    gameState.setGuessedWord(
                        gameState.getWord().substring(0, i) +
                        ch +
                        gameState.getWord().substring(i + 1)
                    );    

                correct_guess = true; 
                }                           
            }
            i++;
        }
        for (var asdf : gameState.getWord().toCharArray()) {
            if (asdf == '_') {
                count_missing++;
            } 
        }
        gameState.setMissingChars(count_missing);
        if(!correct_guess) {
            gameState.incrementMistakes();
        }       
        if (gameState.getMissingChars() == 0 ||
            gameState.getMistakes() > gameState.getMistakeLimit())
        {
            WordGameState temp = gameState;
            gameState = null;
            return temp;
        }
        return this.gameState;        
    }

    public WordGameState guess(String guess) throws GameStateException {
        if (gameState == null) {
            throw new GameStateException(ERROR_NO_GAME);
        }

        if (guess.equals(gameState.getOriginalWord())) {
            gameState.setMissingChars(0);
            gameState.setGuessedWord(gameState.getOriginalWord());
        } else {
            gameState.incrementMistakes();
        }
        if (gameState.getMissingChars() == 0 ||
            gameState.getMistakes() > gameState.getMistakeLimit())
        {
            WordGameState temp = gameState;
            gameState = null;
            return temp;
        }
        return this.gameState;        
    } 

}
