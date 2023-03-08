/*
 * COMP.CS.140 Ohjelmointi 3
 * H8 Streams
 *
 * Antti Hakkarainen / K79735
 * antti.i.hakkarainen@tuni.fi 
 */

 public class Movie2<T, N, S> {
    // instance variables
    private T title;
    private N releaseYear;
    private N duration;
    private T genre;
    private S score;
    private T director;


    // constructor
    public Movie2(
            T title, // String
            N releaseYear, // int
            N duration, // int
            T genre, // String
            S score, // double
            T director) // String
            {

        this.title = title;
        this.releaseYear = releaseYear;
        this.duration = duration;
        this.genre = genre;
        this.score = score;
        this.director = director;
    }


    // get methods for all class variables
    public T getTitle() {
        return title;
    }


    public N getReleaseYear() {
        return releaseYear;  
    }


    public N getDuration() {
        return duration;
    }


    public T getGenre() {
        return genre;
    }


    public S getScore() {
        return score;
    }


    public T getDirector() {
        return director;
    }
}