package com.sk.elevator.fileio;

import com.sk.elevator.exceptions.NotValidInputException;
import com.sk.elevator.person.Person;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileUtils {

    // TODO This method can probably be removed
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

    // TODO Remove this as well
    public static void scanFile(String inFilepath) {
        try {
            Scanner scanner = new Scanner(new File(inFilepath));
            while(scanner.hasNextLine()) {

                String line = scanner.nextLine();
                System.out.println(line);
                Person person = new Person();
                parseLineToCreatePerson(line, person);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static int convertCharToInt(char c) {
        if (c == '1') {
            return 1;
        }
        else if (c == '2') {
            return 2;
        }
        else if (c == '3') {
            return 3;
        }
        else if (c == '4') {
            return 4;
        }
        else if (c == '5') {
            return 5;
        }
        else {
            return 0;
        }
    }

    public static Person parseLineToCreatePerson(String line, Person person) {
        int intCount = 0;
        String name = "";
        for (char c : line.toCharArray()) {
            try {
                if (c=='/') {
                    throw new NotValidInputException("[" + c + "] is not valid input.");
                } else if (c==' ' || c=='\t') {
                    // account for spaces or tabs
                }
                else if ((c=='1' || c =='2' || c =='3' || c=='4' || c=='5') && intCount==0) {
                    intCount++;
//                    System.out.println("entry floor=" + convertCharToInt(c));
                    person.setEntryFloor(convertCharToInt(c));
                } else if ((c=='1' || c =='2' || c =='3' || c=='4' || c=='5') && intCount==1) {
//                    System.out.println("exit floor=" + convertCharToInt(c));
                    person.setExitFloor(convertCharToInt(c));
                } else {
                    name = name + c;
                }
            } catch(NotValidInputException e) {
                System.err.println(e);
                return null;
            }
        }
//        System.out.println("name=" + name);
        person.setName(name);
        System.out.println("next in line="+person.toString());
        return person;
    }

    // This is the method that can changed depending on what you need done
    // This method can probably be removed
    private static void readWriteLine(BufferedReader reader, File outFile) {
        String line;
        BufferedWriter writer = createWriter(outFile);
        try {
            while ((line=reader.readLine()) != null) {
                System.out.println(line);
                writeFileLineByLine(outFile, line);
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

//    public static void writeFileLineByLine(BufferedWriter writer, String line) throws IOException {
//        try {
//            writer.newLine();
//            if (line != null) {
//                writer.write(line);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        writer.close();
//    }

    public static void writeFileLineByLine(File outFile, String line) throws IOException {
        BufferedWriter writer = createWriter(outFile);
        try {
            writer.newLine();
            if (line != null) {
                writer.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.close();
    }
}

