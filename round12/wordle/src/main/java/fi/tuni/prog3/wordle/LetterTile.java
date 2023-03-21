package fi.tuni.prog3.wordle;
/*
 * COMP.CS.140 Ohjelmointi 3
 * H12 Wordle
 *
 * Antti Hakkarainen / K79735
 * antti.i.hakkarainen@tuni.fi 
 */

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
 
/**
 * Custom JavaFX Node class representing a word tile. Each tile contains one letter.
 */
public class LetterTile extends Region {

    private class Coordinates {
        private int x;
        private int y;
    
        public Coordinates(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private Character letter;
    private Coordinates Coords;
    private int TILE_SIZE;
    private Rectangle tile;
    private Text text;

    public LetterTile(Character letter, int x, int y, int TILE_SIZE) {
        this.letter = letter;
        this.Coords = new Coordinates(x, y);
        this.TILE_SIZE = TILE_SIZE;
        this.initGraphics();
    }

    private void initGraphics() {
        tile = new Rectangle(TILE_SIZE, TILE_SIZE);
        tile.setFill(Color.WHITE);
        tile.setStroke(Color.BLACK);

        text = new Text(Character.toString(letter).toUpperCase());
        text.setFont(Font.font(TILE_SIZE / 1.2));


        // Set the text color to white
        text.setFill(Color.BLACK);

        // Set the position of the text node
        text.setLayoutX(10);
        text.setLayoutY(40);

        this.getChildren().addAll(tile, text);
        this.setLayoutX(Coords.x * TILE_SIZE);
        this.setLayoutY(Coords.y * TILE_SIZE);
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
    public Character getLetter() {
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
     * Sets the text of the tile.
     * @param letter Character
     */
    public void setLetter(Character letter, Color color) {
        this.letter = letter;
        text.setText(Character.toString(letter).toUpperCase());
        text.setFill(color);
    }

    /**
     * Sets the color of the tile.
     */
    public void setTileColor(Color color) {
        tile.setFill(color);
    }    
}
