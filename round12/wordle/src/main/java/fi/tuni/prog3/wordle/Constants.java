package fi.tuni.prog3.wordle;
/*
 * COMP.CS.140 Ohjelmointi 3
 * H12 Wordle
 *
 * Antti Hakkarainen / K79735
 * antti.i.hakkarainen@tuni.fi 
 */

import javafx.scene.paint.Color;

public class Constants {

    // File where the words are read from
    public static final String WORDLE_FILE = "words.txt";

    // Empty LetterTile's string
    public static final String EMPTY_STR = " ";

    // Tile styles and grid layout parameters
    public static final Integer X_PADDING = 20;
    public static final Integer Y_PADDING = 20;
    public static final Integer MAX_ROWS = 6;
    public static final Integer TILE_SIZE = 50;
    public static final Integer GAP = 10;
    

    // colors
    public static final Color COLOR_GAME_BACKGROUND = Color.rgb(200, 200, 200);
    public static final Color COLOR_TILE_CORRECT = Color.GREEN;
    public static final Color COLOR_TILE_MISPLACED = Color.ORANGE;
    public static final Color COLOR_TILE_WRONG = Color.GREY;
    public static final Color COLOR_TILE_UNGRADED = Color.WHITE;
    public static final Color COLOR_TILE_ERROR = Color.rgb(255,0,255);

    public static final Color COLOR_TEXT_UNGRADED = Color.BLACK;
    public static final Color COLOR_TEXT_GRADED = Color.WHITE;
    
}


