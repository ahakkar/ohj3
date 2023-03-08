
/*
 * COMP.CS.140 Ohjelmointi 3
 * H8 Streams2
 *
 * Antti Hakkarainen / K79735
 * antti.i.hakkarainen@tuni.fi 

Here are some short examples of what these generic type parameters typically 
represent:

E - Element: This is commonly used as a placeholder for the type of element in a 
    collection or data structure, and is used extensively by the Java Collections 
    Framework. For example, List<E> represents a list of elements of type E.

K - Key: This is often used as a placeholder for the type of key in a key-value
    pair, such as in a Map<K, V> where K represents the type of the key and V 
    represents the type of the value.

N - Number: This is commonly used as a placeholder for numeric types, such 
    as Integer, Double, or BigDecimal. For example, List<N> represents a list 
    of numbers.

T - Type: This is a generic placeholder for any type, and is often used when
    the specific type is not important or is not known at compile time. For 
    example, a generic method that operates on any type might be declared 
    as public <T> void doSomething(T arg).

V - Value: This is often used as a placeholder for the type of value in
    a key-value pair, such as in a Map<K, V> where K represents the type of
    the key and V represents the type of the value.

S, U, V, etc. - 2nd, 3rd, 4th types: These are typically used when a 
    method or class has multiple type parameters, and the type names T, U, 
    and V are already in use. For example, a method that takes two lists of
    different types might be declared as public <S, T> void 
    doSomething(List<S> list1, List<T> list2).

 */
 
import java.util.ArrayList;
import java.util.Collections;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MovieAnalytics2b {
    ArrayList <Movie> movies;


    // empty public constructor
    public MovieAnalytics2b() {
        movies = new ArrayList <Movie> ();
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

    public void printCountByDirector(int n) {
        movies.stream()
            .collect(Collectors.groupingBy(Movie::getDirector, Collectors.counting()))
            .entrySet()
            .stream()
            .sorted(comparingByValueDescThenByKeyAsc())
            .limit(n)
            .forEach(e -> System.out.printf("%s: %d movies\n", e.getKey(), e.getValue()));
    }
    
    public void printAverageDurationByGenre() {
        movies.stream()
            .collect(Collectors.groupingBy(Movie::getGenre, Collectors.averagingDouble(Movie::getDuration)))
            .entrySet()
            .stream()
            .sorted(comparingByValueDescThenByKeyAsc())
            .forEach(e -> System.out.printf("%s: %.2f\n", e.getKey(), e.getValue()));
    }

    public void printAverageScoreByGenre() {
        movies.stream()
            .collect(Collectors.groupingBy(Movie::getGenre, Collectors.averagingDouble(Movie::getScore)))
            .entrySet()
            .stream()
            .sorted(comparingByValueAscThenByKeyAsc())
            .forEach(e -> System.out.printf("%s: %.2f\n", e.getKey(), e.getValue()));
    }
    
    private static <K, V extends Comparable<V>> Comparator<Map.Entry<K, V>> comparingByValueDescThenByKeyAsc() {
        return Comparator.<Map.Entry<K, V>>comparing(Map.Entry::getValue).reversed().thenComparing(Map.Entry::getKey);
    }

    private static <K, V extends Comparable<V>> Comparator<Map.Entry<K, V>> comparingByValueAscThenByKeyAsc() {
        return Comparator.<Map.Entry<K, V>>comparing(Map.Entry::getValue).thenComparing(Map.Entry::getKey);
    }

/* 
    private final <T> void printStatsByCategory(
        Function <Movie2<String, Integer, Double>, T> category,
        Function <Movie2<String, Integer, Double>, ?> value,
        Boolean isDouble,
        Comparator<Map.Entry<T, Long>> sortingComparator,
        int limit,
        String format)
    {
        Collector<Movie2<String, Integer, Double>, ?, Map<T, ?>> collector = isDouble ? 
        Collectors.groupingBy(category, Collectors.averagingDouble(value)) : 
        Collectors.groupingBy(category, Collectors.counting());
        
        movies.stream()
            .collect(Collectors.collectingAndThen(collector, map -> map.entrySet().stream()))            
            .sorted(sortingComparator)       
            .limit(limit > 0 ? limit : movies.size())
            .forEach(e -> 
                System.out.printf(format, e.getKey(), e.getValue())
            );
    }


    public void printCountByDirector(int limit) {
        printStatsByCategory(
            Movie2::getDirector,
            Movie2::getScore,
            false,
            (e1, e2) -> {
                // sort by count descending    
                int countCompare = e2.getValue().compareTo(e1.getValue());                            
                if (countCompare != 0) {
                    return countCompare; 
                // IF count is equal, sort by String ascending
                } 
                else {
                    return ((String) e1.getKey()).compareTo((String) e2.getKey()); 
                }
            },  
            limit, 
            "%s: %s movies\n");
    } */


/*     public void printAverageDurationByGenre() {
        printStatsByCategory(
            Movie2::getGenre,
            Movie2::getDuration,
            false,
            (e1, e2) -> {
                // sort by count descending    
                int countCompare = e2.getValue().compareTo(e1.getValue());                            
                if (countCompare != 0) {
                    return countCompare; 
                // IF count is equal, sort by String ascending
                } 
                else {
                    return ((String) e1.getKey()).compareTo((String) e2.getKey()); 
                }
            },  
            10, 
            "%s: %.2f\n");
    }

    public void printAverageScoreByGenre() {
        printStatsByCategory(
            Movie2::getGenre,
            Movie2::getScore,
            false,
            (e1, e2) -> {
                // sort by count descending    
                int countCompare = e2.getValue().compareTo(e1.getValue());                            
                if (countCompare != 0) {
                    return countCompare; 
                // IF count is equal, sort by String ascending
                } 
                else {
                    return ((String) e1.getKey()).compareTo((String) e2.getKey()); 
                }
            },  
            10, 
            "%s: %.2f\n");
    } */



}