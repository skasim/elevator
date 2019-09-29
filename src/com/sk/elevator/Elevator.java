package com.sk.elevator;

import com.sk.elevator.button.Button;
import com.sk.elevator.metrics.ElevatorMetrics;
import com.sk.elevator.person.Person;
import com.sk.elevator.stack.LinkedListStack;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static com.sk.elevator.elevatorio.ElevatorUtils.*;
import static com.sk.elevator.fileio.FileUtils.*;
import static com.sk.elevator.person.Person.processPerson;

/**
 * Lab 1: Elevator Simulation
 * This program simulates the functioning of an elevator in a building with floors one through five. The elevator is
 * narrow so individuals can only be loaded on and off in the first in first out manner of a stack. The purpose
 * of this program is to demonstrate knowledge of utilizing a stack class to solve such a problem as well as using
 * algorithm logic to determine how to move the elevator and when and how to load and offload riders. The program is
 * limited to using Java primitives in it's execution except in the case of File input out. The primary driver
 * of this program is the determineNextFloor method, which is used to identify, which floor the elevator should travel
 * to next. A naive implementation would stop the floor at every floor during the elevator's ascent and descent.
 * However, in this program, the elevator "smartly" determines, which floor to go to next after analyzing the
 * floor requests of riders in the elevator, the entry floor of the person waiting to get on the elevator, and the
 * direction the elevator is moving.
 *
 * To execute the program, refer to the README.
 *
 * @author Samra Kasim
 */
public class Elevator {
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
        // Check if input and output filepaths are provided. If not, exit program execution.
        if (args.length != 2) {
            System.err.println("Input and output file paths must be provided to run this simulation. Exiting now.");
            System.exit(1);
        }
        // Variables related to program IO
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
                writeFileLineByLine(outFile, "#    By: Samra Kasim                   #\n");
                writeFileLineByLine(outFile, "########################################\n\n");
                writeFileLineByLine(outFile, "\nLEGEND:\n<==== Rider getting on elevator \n====> Rider " +
                        "getting off elevator \n<===> Rider's temporary Exit \n=xxx= Rider unable to get on because" +
                        " max capacity reached \n=ooo= Elevator is empty");
                writeFileLineByLine(outFile, "\n Begin Elevator Simulation.\n");
            } catch (IOException e) {
                System.err.println(e.toString());
            }

            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Person person = new Person();
                person = parseLineToCreatePerson(line, person);
                processPerson(person, elevator, button, eMetrics, outFile);
            }
            // Conduct a final unload of riders left in the elevator once the simulation comes to an end
            finalUnload(elevator, button, eMetrics, outFile);

            try {
                writeFileLineByLine(outFile, "\nEnd Elevator Simulation.");
                writeFileLineByLine(outFile, "\n########################################\n");
                writeFileLineByLine(outFile, "#             Metrics Report           #\n");
                writeFileLineByLine(outFile, "########################################\n\n");
                writeFileLineByLine(outFile, eMetrics.toString());
                System.out.println("File processing complete.");
            } catch (IOException e) {
                System.err.println(e.toString());
            }
            scanner.close();
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }
}
