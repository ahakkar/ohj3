
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/*
 * COMP.CS.140 Ohjelmointi 3
 * H8 Streams2
 *
 * Antti Hakkarainen / K79735
 * antti.i.hakkarainen@tuni.fi 
 */

 public class MovieAnalytics2 {
    ArrayList <Movie> movies ;


    // empty public constructor
    public MovieAnalytics2() {
        movies = new ArrayList <Movie> ();
    }


    // static member method Consumer<Movie> showInfo()
    public static Consumer<Movie> showInfo() {
        return (Movie m) -> System.out.println(m.toString());
    }


    // Implement file reading with stream instead of loop
    public void populateWithData(String filename) {
        try {
            movies = Files.lines(Paths.get(filename))
                .map(line -> line.split(";"))
                .filter(parts -> parts.length == 6)
                .map(parts -> new Movie(
                                    parts[0],
                                    Integer.parseInt(parts[1]),
                                    Integer.parseInt(parts[2]),
                                    parts[3],
                                    Double.parseDouble(parts[4]),
                                    parts[5]))
                .collect(Collectors.toCollection(ArrayList::new));
        } 
        catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } 
        catch (IOException e) {
            System.out.println("Something went wrong with IO: " + e.getMessage());
        }
    }


    
     

    /* 
    Member function void printCountByDirector(int n) that prints out the n directors that
    have directed the most movies, in descending order of the number of movies.
    Directors with equally many movies are ordered by their names using the
    default order of the String objects. Each director is printed in the ###m 
    "director: x movies", where x is the number of movies directed by that 
    particular director. The output is printed in a single line that ends 
    to a new line character.
    */
    public void printCountByDirector(int n) {
        movies.stream()
            .collect(Collectors.groupingBy(Movie::getDirector, Collectors.counting()))
            .entrySet()
            .stream()
            .sorted((e1, e2) -> {
                // sort by movie count descending    
                int countCompare = e2.getValue().compareTo(e1.getValue());                            
                if (countCompare != 0) {
                    return countCompare; 
                // IF movie count is equal, sort by director name ascending
                } 
                else {
                    return e1.getKey().compareTo(e2.getKey()); 
                }
            })
            .limit(n)
            .forEach(e -> System.out.print(e.getKey() + ": " + e.getValue() + " movies\n"));
    }


    /* 
    Member function void printAverageDurationByGenre() that prints out all movie genres of 
    the database in ascending order of the average duration of movies in that 
    genre. Movies in the same genre are ordered by title using the default order
    of the String objects. Each genre is printed in the ###m "genre: average",
    where average is the average duration of movies in that genre, using two 
    decimals of precision. The output is printed in a single line that ends
    to a new line character.
    */
    public void printAverageDurationByGenre() {
        movies.stream()
            .collect(Collectors.groupingBy(Movie::getGenre, Collectors.averagingDouble(Movie::getDuration)))
            .entrySet()
            .stream()
            .sorted((e1, e2) -> {
                // sort by movie average duration ascending    
                int countCompare = e1.getValue().compareTo(e2.getValue());                            
                if (countCompare != 0) {
                    return countCompare; 
                // IF movie average duration is equal, sort by genre name ascending
                } 
                else {
                    return e1.getKey().compareTo(e2.getKey()); 
                }
            })
            .forEach(e -> System.out.print(e.getKey() + ": " + String.format("%.2f", e.getValue()).replace(',', '.') + "\n"));
    }


    /*
    Member function void printAverageScoreByGenre() that prints out all movie
    genres of the database in descending order of the average rating scores
    of movies in that genre. Genres with the same average rating are ordered 
    by genre name using the default order of the String objects. Each genre 
    is printed in the ###m "genre: average", where average is the average
    rating score of movies in that genre, using two decimals of precision. 
    The output is printed in a single line that ends to a new line character.
    */
    public void printAverageScoreByGenre() {
        movies.stream()
            .collect(Collectors.groupingBy(Movie::getGenre, Collectors.averagingDouble(Movie::getScore)))
            .entrySet()
            .stream()
            .sorted((e1, e2) -> {
                // sort by movie count descending    
                int countCompare = e2.getValue().compareTo(e1.getValue());                            
                if (countCompare != 0) {
                    return countCompare; 
                // IF movie count is equal, sort by director name ascending
                } 
                else {
                    return e1.getKey().compareTo(e2.getKey()); 
                }
            })
            .forEach(e -> 
                System.out.print(
                    e.getKey() +
                    ": " + 
                    String.format("%.2f", e.getValue()).replace(',', '.') +
                     "\n"));
    }
 }