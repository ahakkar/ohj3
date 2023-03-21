package fi.tuni.prog3.wordle;
/*
 * COMP.CS.140 Ohjelmointi 3
 * H12 Wordle
 *
 * Antti Hakkarainen / K79735
 * antti.i.hakkarainen@tuni.fi 
 */

public class WordleCore {

    private Integer wordLength = -1;
    private String word = "";
    private WordRepository repo;
    private static final String WORDLE_FILE = "words.txt";

    public WordleCore() {
        repo = new WordRepository(WORDLE_FILE);
        word = repo.getFirstWord();
        wordLength = word.length();
        printDebugMessages();
    }

    public Integer getWordLength() {
        return wordLength;
    }

    public String getWord() {
        return word;
    }

    private void printDebugMessages() {
        System.out.println("Word length: " + wordLength);
        System.out.println("Word: " + word);
    }
    
}
