package fi.tuni.prog3.wordle;
/*
 * COMP.CS.140 Ohjelmointi 3
 * H12 Wordle
 *
 * Antti Hakkarainen / K79735
 * antti.i.hakkarainen@tuni.fi 
 */

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.text.Font;

 
/**
 * Custom JavaFX Node class representing a word tile. Each tile contains one letter.
 */
public class LetterTile extends Label {

    private class Coordinates {
        private int x;
        private int y;
    
        public Coordinates(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private String letter;
    private Coordinates Coords;
    private int tileSize;

    public LetterTile(String letter, int x, int y, int tileSize ) {
        this.letter = letter;
        this.Coords = new Coordinates(x, y);
        this.tileSize = tileSize;
        this.initGraphics();
    }

    /**
     * Returns the x coordinate of the tile.
     * @return int x
     */
    public int getX() {
        return Coords.x;
    }

    /**
     * Returns the y coordinate of the tile.
     * @return int y
     */
    public int getY() {
        return Coords.y;
    }

    /**
     * Returns the letter of the tile.
     * @return Character letter
     */
    public String getLetter() {
        return letter;
    }

    /**
     * Returns the tile as a Node.
     * @return Node tile
     */
    public Node getLetterTile() {
        return this;
    }


    /**
     * Toggles tile highlight on/off
     * @param state
     */
    public void higlightTile(boolean state) {
        if (state) {
            setStyle("-fx-border-color: red; -fx-border-width: 5px;");
        } else {
            setStyle("-fx-border-color: black; -fx-border-width: 1px;");
        }
    }

    /**
     * Initializes the graphics of the tile, 
     * a rectangle with a letter in the middle.
     */
    private void initGraphics() { 
        // setup tile
        setMinSize(tileSize, tileSize);
        setMaxSize(tileSize, tileSize);
        setPrefSize(tileSize, tileSize);
        setTileColor(Constants.COLOR_TILE_UNGRADED);

        // setup text
        setText(letter.toUpperCase());
        setFont(Font.font(tileSize / 2));
        setTextFill(Constants.COLOR_TEXT_UNGRADED);
        setStyle("-fx-border-color: black; -fx-border-width: 1px;");

        setAlignment(Pos.CENTER);
        setLayoutX(Coords.x * tileSize);
        setLayoutY(Coords.y * tileSize);

        setId(String.format("%d_%d", Coords.y, Coords.x));
    }


    /**
     * Sets the text of the tile.
     * @param letter Character
     */
    public void setLetter(String letter, Color color) {
        this.letter = letter;
        setText(letter.toUpperCase());
        setTextFill(color);
    }

    /**
     * Sets the color of the tile.
     */
    public void setTileColor(Color color) {
        setBackground(
            new Background(
                new BackgroundFill(
                    color,
                    CornerRadii.EMPTY,
                    javafx.geometry.Insets.EMPTY
                    )
                )
            );
    }    
}
