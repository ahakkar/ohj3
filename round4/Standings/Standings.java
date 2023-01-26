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
    private int longest_name = 0;

    public class Team {

        public String name;
        public int wins;
        public int ties;
        public int losses;
        public int scored;
        public int allowed;
        public int points;
        public int games;

        private Team(String n) {
            name = n;
        }

        public String getName() {
            return name;
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

    private Team find_team(String name) {
        return teams.stream().filter(team -> name.equals(team.getName())).findFirst().orElse(null);
    }

    public void addMatchResult(String teamNameA,
                                int goalsA,
                                int goalsB,
                                String teamNameB
    ){
        // System.out.printf("%s %d-%d %s\n", teamNameA, goalsA, goalsB, teamNameB);

        // voitosta 3 pistettä, tasapelistä 1 pisteen ja häviöstä 0 pistettä
        // find team a and b objects and modify them
        // all this code below was generated with GitHub Copilot
        Team teamA = find_team(teamNameA);
        Team teamB = find_team(teamNameB);

        if (teamA == null) {
            teamA = new Team(teamNameA);
            teams.add(teamA);
            if (teamNameA.length() > longest_name) {
                longest_name = teamNameA.length();
            }
        }
        
        if (teamB == null) {
            teamB = new Team(teamNameB);
            teams.add(teamB);
            if (teamNameB.length() > longest_name) {
                longest_name = teamNameB.length();
            }
        }

        teamA.scored += goalsA;
        teamA.allowed += goalsB;
        teamB.scored += goalsB;
        teamB.allowed += goalsA;
        teamA.games++;
        teamB.games++;
        
        if (goalsA > goalsB) {
            teamA.wins++;
            teamB.losses++;
            teamA.points += 3;
        }
        else if (goalsA < goalsB) {
            teamA.losses++;
            teamB.wins++;
            teamB.points += 3;
        }
        else {
            teamA.ties++;
            teamB.ties++;
            teamA.points++;
            teamB.points++;
        }
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void printStandings()
    {        
        System.out.println("Standings:");
        System.out.print(String.format("%-" + longest_name + "s   ", "Team"));
        System.out.println("Win Tie Lss Scr All Pts");
        System.out.println("-------------------------------------------------");
        teams.stream().sorted((a, b) -> {
            if (a.points == b.points) {
                if (a.wins == b.wins) {
                    if (a.scored - a.allowed == b.scored - b.allowed) {
                        return a.name.compareTo(b.name);
                    }
                    else {
                        return b.scored - b.allowed - a.scored + a.allowed;
                    }
                }
                else {
                    return b.wins - a.wins;
                }
            }
            else {
                return b.points - a.points;
            }
        }).forEach(team -> {
            System.out.print(String.format("%-" + longest_name + "s", team.name));
            System.out.printf("%4d %3d %3d %3d %3d-%d %3d\n", 
                                team.games,                                
                                team.wins,
                                team.ties, 
                                team.losses,
                                team.scored,
                                team.allowed,
                                team.points);
            }
        );
    }

    public void readMatchData(String filename) {
        String fn = 
            String.format("D:\\GDrive\\study\\TUNI2022\\ohj3\\round4\\Standings\\%s.txt", filename);

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
