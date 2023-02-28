/*
 * COMP.CS.140 Ohjelmointi 3
 * H8 Streams
 *
 * Antti Hakkarainen / K79735
 * antti.i.hakkarainen@tuni.fi 
 */

 public class Movie implements Comparable<Movie> {

    // instance variables
    private String title;
    private int releaseYear;
    private int duration;
    private String genre;
    private double score;
    private String director;

    // constructor
    public Movie(String title,
            int releaseYear,
            int duration,
            String genre, 
            double score, 
            String director)
            {

        this.title = title;
        this.releaseYear = releaseYear;
        this.duration = duration;
        this.genre = genre;
        this.score = score;
        this.director = director;
    }

    /* Custom comparator method for ordering a Movie stream */    
    @Override
    public int compareTo(Movie m) {
        // list the movies in ascending order of release year
        int result = Integer.compare(this.releaseYear, m.releaseYear);
        
        // ordered by title using the default  order of the String objects
        if (result == 0) {
            result = this.title.compareTo(m.title);
        }
        
        return result;
    }


    // get methods for all class variables
    public String getTitle() {
        return title;
    }


    public int getReleaseYear() {
        return releaseYear;  
    }


    public int getDuration() {
        return duration;
    }


    public String getGenre() {
        return genre;
    }


    public double getScore() {
        return score;
    }


    public String getDirector() {
        return director;
    }


    public String toString() {
        String str = String.format("%s (By %s, %d)", title, director, releaseYear);
        return str;
    }
}