package com.sk.elevator.fileio;

import com.sk.elevator.exceptions.NotValidInputException;
import com.sk.elevator.person.Person;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A class representing utility methods to facilitate reading of input file and writing to output file. Since the input
 * is read character by character, there are some unique helperr methods that were needed such as convertingCharToInt to
 * return the integer representation of a character (ex., int 5 is returned for char 53). The building of the Person
 * object is also handled in this class. Finally, the class also includes methods responsible for writing to output file.
 *
 * @author Samra Kasim
 */
public class FileUtils {

    /**
     * A helper method to convert char input to an integer.
     * @param c is a Char representing a character from input
     * @return int value.
     */
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

    /**
     * Helper method to parse a line of input character by character into a Person object.
     * @param line: String value representing a line of input text.
     * @param person: Person object representing a rider
     * @return Person object with Person.name, Person.ExitFloor, Person.EntryFloor values provided
     */
    public static Person parseLineToCreatePerson(String line, Person person) {
        int intCount = 0;
        String name = "";
        for (char c : line.toCharArray()) {
            try {
                if (c=='/') {
                    throw new NotValidInputException("[" + c + "] is not valid input.");
                }
                else if ((c=='1' || c =='2' || c =='3' || c=='4' || c=='5') && intCount==0) {
                    intCount++;
                    person.setEntryFloor(convertCharToInt(c));
                } else if ((c=='1' || c =='2' || c =='3' || c=='4' || c=='5') && intCount==1) {
                    person.setExitFloor(convertCharToInt(c));
                } else {
                    if ( c != ' ' && c != '\t') {
                        name = name + c;
                    }
                }
                person.setName(name);
            } catch(NotValidInputException e) {

                System.err.println(e);
                return null;
            }
        }
        return person;
    }

    /**
     * Helper method to take a File object representing the output file and return a BufferedWrite object.
     * @param outFile: String value representing the output file
     * @return a BufferedWriter object
     */
    private static BufferedWriter createWriter(File outFile) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(outFile, true));
        } catch (IOException e) {
            System.err.println(e.toString());
        }
        return writer;

    }

    /**
     * Helper method to facilitate the writing of a file line by line (by appending to a file).
     * @param outFile: File object representing the output file.
     * @param line: String value representing a line of input text.
     * @throws IOException
     */
    public static void writeFileLineByLine(File outFile, String line) throws IOException {
        BufferedWriter writer = createWriter(outFile);
        try {
            writer.newLine();
            if (line != null) {
                writer.write(line);
            }
        } catch (IOException e) {
            System.err.println(e.toString());
        }
        writer.close();
    }
}
