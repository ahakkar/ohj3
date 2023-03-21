package fi.tuni.prog3.wordle;
/*
 * COMP.CS.140 Ohjelmointi 3
 * H12 Wordle
 *
 * Antti Hakkarainen / K79735
 * antti.i.hakkarainen@tuni.fi 
 */

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class WordRepository {

    private ArrayList<String> words = new ArrayList<String>();

    /**
     * Constructor for WordRepository, calls the filereader
     * @param wordleFile String name of the file to be read
     */
    public WordRepository(String wordleFile) {
        this.readFile(wordleFile);

    }


    /**
     * Returns a random word from the arraylist
     * @return String random word from the arraylist
     */
    public String getRandomWord() {        
        return words.get((int)(Math.random() * words.size()));
    }


    /**
     * Returns the first word in the arraylist
     * @return String first word in the arraylist
     */
    public String getFirstWord() {
        return words.get(0);
    }


    /**
     * Returns the size of wordlist
     * @return Integer size of wordlist
     */
    public Integer getAmountOfWords() {
        return words.size();
    }


    /**
     * Reads a .txt file and adds the words to the arraylist
     */
    private void readFile(String wordleFile) {  
        URL url = Wordle.class.getResource(wordleFile);
        try (BufferedReader br = new BufferedReader(new FileReader(url.getPath()))) {
            String line;
            while ((line = br.readLine()) != null) {
                words.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }    
    }    
}
