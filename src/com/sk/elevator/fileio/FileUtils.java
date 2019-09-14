package com.sk.elevator.fileio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {

    public static void readWriteFile(String inFilepath, String outFilepath) {
        BufferedReader reader;
        File inFile = new File(inFilepath);
        File outFile = new File(outFilepath);

        try {
            reader = new BufferedReader(new FileReader((inFile)));
            readWriteLine(reader, outFile);
        } catch (FileNotFoundException e) {
            System.err.println(e.toString());
        }
    }
    // This is the method that can changed depending on what you need done
    private static void readWriteLine(BufferedReader reader, File outFile) {
        String line;
        BufferedWriter writer = createWriter(outFile);
        try {
            while ((line=reader.readLine()) != null) {
                System.out.println(line);
                writeFileLineByLine(writer, line);
            }
            writer.close();
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    public static BufferedWriter createWriter(File outFile) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(outFile, true));
        } catch (IOException e) {
            System.err.println(e.toString());
        }
        return writer;

    }

    public static BufferedReader createReader(File inFile) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(inFile));
        } catch (IOException e) {
            System.err.println(e.toString());
        }
        return reader;
    }

    public static BufferedWriter writeFileLineByLine(BufferedWriter writer, String line) {
        try {
            writer.newLine();
            if (line != null) {
                writer.write(line);
            }
        } catch (IOException e) {
            System.err.println(e.toString());;
        }
        return writer;
    }
}

