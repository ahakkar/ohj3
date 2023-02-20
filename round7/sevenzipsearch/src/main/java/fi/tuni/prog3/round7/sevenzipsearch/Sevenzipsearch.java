package fi.tuni.prog3.round7.sevenzipsearch;

import java.io.FileNotFoundException;

/*
 * COMP.CS.140 Ohjelmointi 3
 * H7 Sevenzipsearch
 *
 * Antti Hakkarainen / K79735
 * antti.i.hakkarainen@tuni.fi 
 */

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import org.apache.commons.compress.archivers.sevenz.*;

public class Sevenzipsearch 
{
    public static void main( String[] args )
    {

        // throw out bad command line arguments
        if (args.length != 2) {
            System.out.println("Usage: java -jar sevenzipsearch.jar <filename> <search>");
            System.exit(1);
        }

        Sevenzipsearch sz = new Sevenzipsearch();
        sz.read7ZipData(args[0], args[1]);
    }

    public void read7ZipData(String filename, String search) {
        String fn = String.format(filename);
        //String fn = String.format("D:\\GDrive\\study\\TUNI2022\\ohj3\\round7\\sevenzipsearch\\%s", filename);

        try(SevenZFile file = new SevenZFile(new File(fn))) {
            SevenZArchiveEntry entry;
            while((entry = file.getNextEntry()) != null) {
                // name of the file found from the archive
                String str = entry.getName();

                // skip too short filenames
                if (str.length() < 4) {
                    continue;
                }

                // print the file name if it's to spec
                System.out.println(entry.getName());

                // read only text files
                if (str.substring(str.length() - 4).equals(".txt")) {   
                    readFileData(
                        file.getInputStream(entry),
                        search
                        );
                }   

                System.out.println();       
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } 
    }  
    
    public void readFileData(InputStream txtfile, String search) {

        String line;
        int row = 1;
        Scanner scn = new Scanner(txtfile);

        while (scn.hasNextLine()) {
            line = scn.nextLine(); 

            if (line.contains(search)) {
                // format row number beforehand to make it a bit easier to read
                String rown = String.format("%d: ", row);

                // use regex to find the search string case-insensitively
                System.out.println(rown + 
                    line.replaceAll(
                        String.format("(?i)%s", search),
                        search.toUpperCase()
                        )
                    );
            }
            row++;
        }

        scn.close();
    }  
}

