import java.io.IOException;
public class MovieTest2b {
  public static void main(String[] args)
          throws IOException {
    MovieAnalytics2b ma = new MovieAnalytics2b();
    ma.populateWithData("input1.txt");
    int n = 10;

    System.out.format("Top %d directors with highest number of movies:%n", n);
    ma.printCountByDirector(n);

    System.out.println();
    System.out.println("Average duration by genre:");
    ma.printAverageDurationByGenre();

    System.out.println();
    System.out.println("Average score by genre:");
    ma.printAverageScoreByGenre();
  }

}
