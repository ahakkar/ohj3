/*
 * COMP.CS.140 Ohjelmointi 3
 * H5 Wordgame
 *
 * Antti Hakkarainen / K79735
 * antti.i.hakkarainen@tuni.fi 
 */

package round5.wordgame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class WordGame {

    public static class WordGameState {

        private String word;
        private int mistakes;
        private int mistakeLimit;
        private int missingChars;

        public String getWord() {
            return word;
        }
        public int getMistakes() {
            return mistakes;
        } 
        public int getMistakeLimit() {
            return mistakeLimit;
        }

        public int getMissingChars() {
            return missingChars;
        }

        public void word(String word) {
            this.word = word;
        }

        public void mistakes(int mistakes) {
            this.mistakes = mistakes;
        }

        public void mistakeLimit(int mistakeLimit) {
            this.mistakeLimit = mistakeLimit;
        }

        public void missingChars(int missingChars) {
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
        File file = new File("D:\\GDrive\\study\\TUNI2022\\ohj3\\round5\\wordgame\\" + wordFilename);
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
        gameState.word(words.get(wordIndex));
        gameState.mistakes(mistakeLimit);
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
        if (gameState == null) {
            throw new GameStateException(ERROR_NO_GAME);
        }
        return this.gameState;        
    }

    public WordGameState guess(String word) throws GameStateException {
        if (gameState == null) {
            throw new GameStateException(ERROR_NO_GAME);
        }
        return this.gameState;        
    }    
}
