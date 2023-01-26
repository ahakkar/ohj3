/*
 * COMP.CS.140 Ohjelmointi 3
 * H4 Standings
 *
 * Antti Hakkarainen / K79735
 * antti.i.hakkarainen@tuni.fi 
 */

package round4.Standings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Standings {      

    private ArrayList<Team> teams = new ArrayList<Team>();

    public class Team {

        String name;
        int wins;
        int ties;
        int losses;
        int scored;
        int allowed;
        int points;

        private Team(String n) {
            name = n;
        }

        public String getName() {
            return "";
        }
    
        public int getWins() {
            return wins;
        }
    
        public int getTies() {
            return ties;
        }
    
        public int getLosses() {
            return losses;
        }
    
        public int getScored() {
            return scored;
        }
    
        public int getAllowed() {
            return allowed;
        }
    
        public int getPoints() {
            return points;
        }

    }

    public Standings() {}
    
    public Standings(String filename) {
        readMatchData(filename);
    }

    public void addMatchResult(String teamNameA,
                                int goalsA,
                                int goalsB,
                                String teamNameB
    ){
        System.out.printf("%s %d-%d %s\n", teamNameA, goalsA, goalsB, teamNameB);
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void printStandings() {

    }

    public void readMatchData(String filename) {
        String fn = String.format("D:\\GDrive\\study\\TUNI2022\\ohj3\\round4\\Standings\\%s.txt", filename);

        try(var file = new BufferedReader(new FileReader(fn))) {
            String line;
            while((line = file.readLine()) != null) {
                String[] parts = line.split("\\t");
                String[] goals = parts[1].split("-");    
                addMatchResult(parts[0],
                               Integer.parseInt(goals[0]),
                               Integer.parseInt(goals[1]), 
                               parts[1]
                              );                            
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }  
    }   
    
    public static void main(String[] args) {
        Standings standings = new Standings();
        standings.addMatchResult("Tonga", 0, 3, "Cook Islands");
        standings.addMatchResult("Samoa", 3, 2, "American Samoa");
        standings.addMatchResult("Cook Islands", 1, 0, "Samoa");
        standings.addMatchResult("Tonga", 1, 2, "American Samoa");
        standings.addMatchResult("Tonga", 0, 3, "Samoa");
        standings.addMatchResult("American Samoa", 2, 0, "Cook Islands");
        standings.printStandings();
    }
}
