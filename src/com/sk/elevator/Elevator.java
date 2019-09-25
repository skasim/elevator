package com.sk.elevator;

import com.sk.elevator.button.Button;
import com.sk.elevator.exceptions.NotValidInputException;
import com.sk.elevator.fileio.FileUtils;
import com.sk.elevator.metrics.ElevatorMetrics;
import com.sk.elevator.person.Person;
import com.sk.elevator.stack.LinkedListStack;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import static com.sk.elevator.elevatorio.ElevatorUtils.*;
import static com.sk.elevator.fileio.FileUtils.*;

/**
 * Lab 1: Elevator Simulation
 * This program simulates the functioning of an elevator in a building with floors one through five. The elevator is
 * narrow so individuals can only be loaded on and off in the first in first out manner of a stack. The purpose
 * of this program is to demonstrate knowledge of utilizing a stack class to solve such a problem as well as using logic
 * to determine how to move the elevator and when and how to load and offload riders. The program is limited to using
 * Java primitives in it's execution exception in the case of File input out.
 *
 * To execute the program, read the README.
 *
 * @author Samra Kasim
 */
public class Elevator {
    // TODO write a report
    // TODO java doc all over
    // TODO remove sys.out and put in sys.errors
    /**
     * Main class to enter the program. Input and output filepaths are provided as arguments in the command line.
     * The class reads each row of input text character by character. After conducting validation checks of each
     * character the Person object is created. The main class also instantiates the LinkedListStack elevator object,
     * the Button object (representing the button panel of an elevator). After the creation of the Person object, it is
     * passed on for further processing with the processPerson method. This method utilizes methods such as loadPerson,
     * unloadPeople, determineNextFloor, etc. to process the Person. Writes are made to the output file throughout the
     * execution of the program. After all the input is processed, a finalUnload method is run to ensure that anyone
     * remaining on the elevator will also be off loaded. Finally, a metrics report is written to output providing a
     * comprehensive overview of the functioning of the elevator along key metrics.
     *
     * @param args Takes two command line arguments, the input filepath and the output filepath
     */
    public static void main(String[] args) {

        if (args.length != 2) {
            System.err.println("Input and output file paths must be provided to run this simulation. Exiting now.");
            System.exit(1);
        }
        // Variables related to program IO
        FileReader inputStream = null;
        String inFilepath = args[0];
        String outFilepath = args[1];
        File outFile = new File(outFilepath);

        try {
            // Instantiate objects used throughout project execution
            Scanner scanner = new Scanner(new File(inFilepath));
            LinkedListStack elevator = new LinkedListStack();
            Button button = new Button();
            ElevatorMetrics eMetrics = new ElevatorMetrics();

            // write outs to outfile at the beginning of execution
            try {
                writeFileLineByLine(outFile, "########################################\n");
                writeFileLineByLine(outFile, "#    ABC Corp. Elevator Simulation.    #\n");
                writeFileLineByLine(outFile, "########################################\n\n");
                writeFileLineByLine(outFile, "\n Begin Elevator Simulation.\n");

            } catch (IOException e) {
                System.err.println(e.toString());
            }
//            while(scanner.hasNextLine()) {
//
//                String line = scanner.nextLine();
//                Person person = new Person();
//                person = parseLineToCreatePerson(line, person);  // TODO this is where junk gets processed
//                processPerson(person, elevator, button, eMetrics, outFile);
//            }


            /// TODO TOP
            // Begin reading the input file character by character
            try {
                inputStream = new FileReader(inFilepath);

                int c;
                String name = "";
                int entryFl = 0;
                int exitFl = 0;
                int intCount =0;
                while ((c = inputStream.read()) != -1) {
                    char character = (char) c;
                    // Account for / and # to ignore comment lines in input files
                    if (character=='/' || character=='#') {
                        try {
                            throw new NotValidInputException("[" + character + "] is not valid input.");
                        } catch (NotValidInputException e) {
                            System.err.println(e.toString());
                        }
                    }
                    // Begin building the person object by identifying the entry and exit floors
                    else if ((character=='1' || character =='2' || character =='3' || character=='4' || character=='5')
                            && intCount==0) {
                        intCount++;
                        entryFl = convertCharToInt(character);
                    } else if ((character=='1' || character =='2' || character =='3' || character=='4' || character=='5')
                            && intCount==1) {
                        exitFl = convertCharToInt(character);
                    } else {
                        // Build the name string by appending characters not found above
                        if (character != '\n' && intCount !=1 && character!=' ' && character!='\t') {
                            name = name + character;
                        }
                    }

                    // If the character is a new liine then stop reading input text and begin processing
                    if (character == '\n') {
                        // Create a Person object
                        Person person = new Person(name, entryFl, exitFl);
                        try {
                            // Run validation on the Person object to ensure it has the right floor values
                            if (person.getEntryFloor() == 0 || person.getExitFloor() == 0 || person.getEntryFloor() < 1
                                    || person.getEntryFloor() > 5 || person.getExitFloor() < 1
                                    || person.getExitFloor() > 5) {
                                throw new NotValidInputException("Invalid floor values provided. Must be be > 1 and < 5");
                            } else {
                                // Process the person
                                processPerson(person, elevator, button, eMetrics, outFile);
                            }
                        } catch (NotValidInputException e) {
                            System.err.println(e.toString());
                        }
                        // Resent key fields to begin processing a new line of input
                        name = "";
                        intCount = 0;
                        entryFl = 0;
                        exitFl = 0;
                    }
                }

            } finally {
                if (inputStream != null) inputStream.close();
            }

            /// TODO BOTTOM

            // TODO gotta get rid of sue
            finalUnload(elevator, button, outFile);

            try {
                writeFileLineByLine(outFile, "\nEnd Elevator Simulation.");
            } catch (IOException e) {
                System.err.println(e.toString());
            }
            System.out.println(eMetrics.toString()); //TODO remove

            try {
                writeFileLineByLine(outFile, "\n########################################\n");
                writeFileLineByLine(outFile, "#             Metrics Report           #\n");
                writeFileLineByLine(outFile, "########################################\n\n");
                writeFileLineByLine(outFile, eMetrics.toString());
            } catch (IOException e) {
                System.err.println(e.toString());
            }
            scanner.close();
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }
    //TODO FINISH THIS
    /**
     * Method handles logic for processing a Person object. It enables riders to be loaded onto the elevator,
     * offloaded when it's their requested floor, and moves elevator to next floor (based on either the floor requests
     * of the riders in the elevator or the entry floor of the next person waiting determined by whichever is nearer.
     * @param person: Person object representing the person to load onto the elevator.
     * @param elevator: Elevator object representing the elevator.
     * @param button: Button object representing the button panel in an elevator.
     * @param eMetrics: ElevatorMetrics object representing overall metrics tracked for the elevator.
     * @param outFile: File object representing the output file.
     */
    private static void processPerson(Person person, LinkedListStack elevator, Button button, ElevatorMetrics eMetrics,
                                     File outFile) {
        // TODO buff read here instead
        // Check if person object is null. if it is then exit method
        if (person != null) {
            // If person object is not null check if valid floors are provided. If not, throw an error and exit method
            try {

                if (person.getEntryFloor() == 0 || person.getExitFloor() == 0 || person.getEntryFloor() < 1 ||
                        person.getEntryFloor() > 5 || person.getExitFloor() < 1 || person.getExitFloor() > 5) {
                    throw new NotValidInputException("Floor provided in input is not valid must be between " +
                            "1 and 5.");
                }
                // If floors are valid and the floor the next rider is getting on are the same then load rider
                // and increment the necessary metrics
                else {
                    eMetrics.setTotalPeopleWhoWantedToRideElevator(
                            eMetrics.getTotalPeopleWhoWantedToRideElevator() + 1);

                    if (button.getCurrentFloor() == person.getEntryFloor()) {
                        loadPerson(person, elevator, button, eMetrics, outFile);
                    }
                    // If the floor for the rider is different (this accounts for possibly needing to skip floors)
                    // then determine the next floor and check if elevator is not empty. If elevator is not empty,
                    // unload riders if this is the floor they need to get off on.
                    // Continue to do this until you are at the floor that the next rider wants to get on
                    // and then load that person
                    else {
                        button.setCurrentFloor(button.determineNextFloor(person.getEntryFloor()));

                        while (person.getEntryFloor() != button.getCurrentFloor()) {

                            if (!elevator.isEmpty()) {
                                unloadPeople(button.getCurrentFloor(), elevator, button, outFile);
                            }

                            else {
                                eMetrics.setTotalEmptyElevator(eMetrics.getTotalEmptyElevator() + 1);
                            }
                            button.setCurrentFloor(button.determineNextFloor(person.getEntryFloor()));
                        }

                        if (!elevator.isEmpty()) {
                            unloadPeople(button.getCurrentFloor(), elevator, button, outFile);
                        }

                        else {
                            eMetrics.setTotalEmptyElevator(eMetrics.getTotalEmptyElevator() + 1);
                        }
                        loadPerson(person, elevator, button, eMetrics, outFile);
                    }
                }
            } catch (NotValidInputException e) {
                System.err.println(e.toString());
            }
        } // TODO EO of buff reader
    }
}

