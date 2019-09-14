package com.sk.elevator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

import static com.sk.elevator.fileio.FileUtils.*;

public class Elevator {

    /**
     * Main class to enter the program.
     * @param args Takes two command line agruments, the input filepath and the output filepath
     */
    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("Input and output file paths must be provided to run this simulation. Exiting now.");
            System.exit(1);
        }

        System.out.println("Beginning Elevator Simulation");

        String inFilepath = args[0];
        String outFilepath = args[1];
        String line;

        File inFile = new File(inFilepath);
        File outFile = new File(outFilepath);

        BufferedReader reader = createReader(inFile);
        BufferedWriter writer = createWriter(outFile);
        try {
            while ((line=reader.readLine()) != null) {
                System.out.println(line); // THIS IS WHERE THE JUNK GETS PROCESSED AND WHERE YOU WANT TO CREATE PERSON OBJ AND ENTER STACK PROGRAM
                writeFileLineByLine(writer, line);
            }
            writer.close();
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }
}
