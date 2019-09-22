package com.sk.elevator;

import com.sk.elevator.button.Button;
import com.sk.elevator.elevatorio.ElevatorUtils;
import com.sk.elevator.fileio.FileUtils;
import com.sk.elevator.person.Person;
import com.sk.elevator.stack.LinkedListStack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static com.sk.elevator.fileio.FileUtils.*;

public class Elevator {
    // TODO write a report
    // TODO java doc all over
    /**
     * Main class to enter the program.
     *
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

//        Button button = new Button();
//        button.pushFloorRequestedButton(3);
//        button.pushFloorRequestedButton(4);
//        button.pushFloorRequestedButton(1);
//        button.pushFloorRequestedButton(3);
//        System.out.println(button.toString());
//        System.out.println("***");
//        button.setCurrentFloor(4);
//        System.out.println(button.determineNextFloor(3));


        try {
            Scanner scanner = new Scanner(new File(inFilepath));
            LinkedListStack elevator = new LinkedListStack();
            Button button = new Button();

            while(scanner.hasNextLine()) {

                String line = scanner.nextLine();
//                System.out.println(line);
                Person person = new Person();
                person = parseLineToCreatePerson(line, person);  // TODO this is where junk gets processed


                if (button.getCurrentFloor() == person.getEntryFloor()) {

                    if (elevator.maxCapacityReached()) {
                        System.out.println("wont be getting on bc of max capacity= " + person.getName());
                    } else {
                        ElevatorUtils.loadPerson(person, elevator, button);
                    }

                } else {
                    button.setCurrentFloor(button.determineNextFloor(person.getEntryFloor()));
                    System.out.println("next floor=" + button.getCurrentFloor());
                    while(person.getEntryFloor() != button.getCurrentFloor()) {
                        LinkedListStack auxStack = new LinkedListStack();
                        // TODO could do an if statement here to check if there is anybody on this floor
                        ElevatorUtils.unloadPeople(button.getCurrentFloor(), auxStack, elevator, button);
                        button.zeroOutButtonForFloor(button.getCurrentFloor());
                        button.setCurrentFloor(button.determineNextFloor(person.getEntryFloor()));
                    }
                    LinkedListStack auxStack = new LinkedListStack();
                    ElevatorUtils.unloadPeople(button.getCurrentFloor(), auxStack, elevator, button);
                    button.zeroOutButtonForFloor(button.getCurrentFloor());
                    ElevatorUtils.loadPerson(person, elevator, button);
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }




    }
}

