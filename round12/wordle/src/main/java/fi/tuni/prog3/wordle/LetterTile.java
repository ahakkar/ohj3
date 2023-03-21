package fi.tuni.prog3.wordle;
/*
 * COMP.CS.140 Ohjelmointi 3
 * H12 Wordle
 *
 * Antti Hakkarainen / K79735
 * antti.i.hakkarainen@tuni.fi 
 */

import javafx.scene.Group;
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

    public LetterTile(Character letter, int x, int y, int TILE_SIZE) {
        this.letter = letter;
        this.Coords = new Coordinates(x, y);
        this.TILE_SIZE = TILE_SIZE;
        this.initGraphics();
    }

    private void initGraphics() {
        Rectangle tile = new Rectangle(TILE_SIZE, TILE_SIZE);
        tile.setFill(Color.WHITE);
        tile.setStroke(Color.BLACK);

        Text text = new Text(Character.toString(letter).toUpperCase());
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

    public int getX() {
        return Coords.x;
    }

    public int getY() {
        return Coords.y;
    }

    public Character getLetter() {
        return letter;
    }

    public Node getLetterTile() {
        return this;
    }
    
}
