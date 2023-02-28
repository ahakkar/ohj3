import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Stream;

/*
 * COMP.CS.140 Ohjelmointi 3
 * H8 Streams
 *
 * Antti Hakkarainen / K79735
 * antti.i.hakkarainen@tuni.fi 
 */

 public class MovieAnalytics {


    ArrayList <Movie> movies ;


    // empty public constructor
    public MovieAnalytics() {
        movies = new ArrayList <Movie> ();
    }


    // static member method Consumer<Movie> showInfo()
    public static Consumer<Movie> showInfo() {
        return (Movie m) -> System.out.println(m.toString());
    }


    // member method populateWithDat(String filename) which reads the file and populates the list
    public void populateWithData(String filename) {
        try {
            Scanner sc = new Scanner(new File(filename));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(";");

                if (parts.length != 6) {
                    System.out.println("Invalid line: " + line);
                    continue;
                }

                Movie m = new Movie(
                    parts[0],
                    Integer.parseInt(parts[1]),
                    Integer.parseInt(parts[2]),
                    parts[3],
                    Double.parseDouble(parts[4]),
                    parts[5]);

                movies.add(m);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }


    /* Member function Stream<Movie> moviesAfter(int year)
       that returns a stream that lists all movies released 
       in the year year or later.
    */
    public Stream<Movie> moviesAfter(int year) {
        return movies.stream().filter(m -> m.getReleaseYear() >= year).sorted();
    }


    /*
     * Member function Stream<Movie> moviesBetween(int yearA, int yearB)
     * that returns a stream that lists all movies released between
     * the years yearA and yearB, including yearA and yearB.
     */
    public Stream<Movie> moviesBetween(int yearA, int yearB) {
        return movies.stream().filter(m -> m.getReleaseYear() >= yearA && m.getReleaseYear() <= yearB).sorted();
    }


    /* Member function Stream<Movie> moviesBefore(int year) that 
    returns a stream that lists all movies released in the year 
    year or earlier. 
    */
    public Stream<Movie> moviesBefore(int year) {
        return movies.stream().filter(m -> m.getReleaseYear() <= year).sorted();
    }


    /* Member function Stream<Movie> moviesByDirector(String director)
     that returns a stream that lists all movies directed by director. 
     */
    public Stream<Movie> moviesByDirector(String director) {
        return movies.stream().filter(m -> m.getDirector().equals(director)).sorted();
    }

 }