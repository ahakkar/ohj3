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

    public static class WordGameState {

        protected String original_word;
        protected String guessed_word;
        protected int mistakes;
        protected int mistakeLimit;
        protected int missingChars;

        public String getWord() {
            return guessed_word;
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

        public void add_mistake() {
            this.mistakes++;
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
        try {
            gameState.original_word = words.get(wordIndex % words.size());
            gameState.mistakeLimit = mistakeLimit;
            gameState.missingChars = count_unique_chars(gameState.original_word);
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

        while (i < gameState.original_word.length()) {
            if (gameState.original_word.charAt(i) == ch) {
                if (gameState.guessed_word.charAt(i) == '_') {
                    gameState.guessed_word =
                    gameState.guessed_word.substring(0, i) +
                    ch +
                    gameState.guessed_word.substring(i + 1);    

                correct_guess = true; 
                }                           
            }
            i++;
        }
        for (var asdf : gameState.guessed_word.toCharArray()) {
            if (asdf == '_') {
                count_missing++;
            } 
        }
        gameState.missingChars = count_missing;
        if(!correct_guess) {
            gameState.add_mistake();
        }       
        if (gameState.missingChars == 0 || gameState.mistakes > gameState.mistakeLimit) {
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

        if (guess.equals(gameState.original_word)) {
            gameState.missingChars = 0;
            gameState.guessed_word = gameState.original_word;
        } else {
            gameState.add_mistake();
        }
        if (gameState.missingChars == 0 || gameState.mistakes > gameState.mistakeLimit) {
            WordGameState temp = gameState;
            gameState = null;
            return temp;
        }
        return this.gameState;        
    }  
    
    public static void main(String args[]) {
        WordGame game = new WordGame("words.txt");
        game.initGame(0, 5);
        System.out.println(game.isGameActive());
        game.initGame(400, 10);
    }
}
