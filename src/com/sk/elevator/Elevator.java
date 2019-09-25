package com.sk.elevator;

import com.sk.elevator.button.Button;
import com.sk.elevator.exceptions.NotValidInputException;
import com.sk.elevator.metrics.ElevatorMetrics;
import com.sk.elevator.person.Person;
import com.sk.elevator.stack.LinkedListStack;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static com.sk.elevator.elevatorio.ElevatorUtils.*;
import static com.sk.elevator.fileio.FileUtils.*;

/**
 *
 */
public class Elevator {
    // TODO write a report
    // TODO java doc all over
    // TODO remove sys.out and put in sys.errors
    /**
     * Main class to enter the program.
     *
     * @param args Takes two command line arguments, the input filepath and the output filepath
     */
    public static void main(String[] args) {

        if (args.length != 2) {
            System.err.println("Input and output file paths must be provided to run this simulation. Exiting now.");
            System.exit(1);
        }

        String inFilepath = args[0];
        String outFilepath = args[1];
        File outFile = new File(outFilepath);

        try {
            Scanner scanner = new Scanner(new File(inFilepath));
            LinkedListStack elevator = new LinkedListStack();
            Button button = new Button();
            ElevatorMetrics eMetrics = new ElevatorMetrics();
            try {
                writeFileLineByLine(outFile, "########################################\n");
                writeFileLineByLine(outFile, "#    ABC Corp. Elevator Simulation.    #\n");
                writeFileLineByLine(outFile, "########################################\n\n");
                writeFileLineByLine(outFile, "\n Begin Elevator Simulation.\n");

            } catch (IOException e) {
                System.err.println(e.toString());
            }
            while(scanner.hasNextLine()) {

                String line = scanner.nextLine();
                Person person = new Person();
                person = parseLineToCreatePerson(line, person);  // TODO this is where junk gets processed

                // TODO insert here
                processPerson(person, elevator, button, eMetrics, outFile);
                // TODO ENDS HERE
            }

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

    /**
     *
     * @param person: Person object representing the person to load onto the elevator.
     * @param elevator: Elevator object representing the elevator.
     * @param button: Button object representing the button panel in an elevator.
     * @param eMetrics: ElevatorMetrics object representing overall metrics tracked for the elevator.
     * @param outFile: File object representing the output file.
     */
    private static void processPerson(Person person, LinkedListStack elevator, Button button, ElevatorMetrics eMetrics,
                                     File outFile) {
        // TODO buff read here instead
        if (person != null) {

            try {

                if (person.getEntryFloor() == 0 || person.getExitFloor() == 0 || person.getEntryFloor() < 1 ||
                        person.getEntryFloor() > 5 || person.getExitFloor() < 1 || person.getExitFloor() > 5) {
                    throw new NotValidInputException("Floor provided in input is not valid must be between " +
                            "1 and 5.");
                }

                else {
                    eMetrics.setTotalPeopleWhoWantedToRideElevator(
                            eMetrics.getTotalPeopleWhoWantedToRideElevator() + 1);

                    if (button.getCurrentFloor() == person.getEntryFloor()) {
                        loadPerson(person, elevator, button, eMetrics, outFile);
                    }

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

