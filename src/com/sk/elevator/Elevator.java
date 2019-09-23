package com.sk.elevator;

import com.sk.elevator.button.Button;
import com.sk.elevator.elevatorio.ElevatorUtils;
import com.sk.elevator.fileio.FileUtils;
import com.sk.elevator.metrics.ElevatorMetrics;
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


        try {
            Scanner scanner = new Scanner(new File(inFilepath));
            BufferedWriter writer = FileUtils.createWriter(new File(outFilepath));
            LinkedListStack elevator = new LinkedListStack();
            Button button = new Button();
            ElevatorMetrics eMetrics = new ElevatorMetrics();
            try {
                FileUtils.writeFileLineByLine(new File(outFilepath), "Begin Elevator Simulation.\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
            while(scanner.hasNextLine()) {

                String line = scanner.nextLine();
//                System.out.println(line);
                Person person = new Person();
                person = parseLineToCreatePerson(line, person);  // TODO this is where junk gets processed

                // TODO buff read here instead
                if (person != null) {
                    eMetrics.setTotalPeopleWhoWantedToRideElevator(eMetrics.getTotalPeopleWhoWantedToRideElevator() + 1);
                    if (button.getCurrentFloor() == person.getEntryFloor()) {

                        if (elevator.maxCapacityReached()) {
                            System.out.println("wont be getting on bc of max capacity= " + person.getName());
                            try {
                                FileUtils.writeFileLineByLine(new File(outFilepath), "Elevator at max capacity! [" + person.getName() + "] was not able to get on at floor [" + button.getCurrentFloor() +"]");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            eMetrics.setTotalTurnaways(eMetrics.getTotalTurnaways() + 1);
                        } else {
                            ElevatorUtils.loadPerson(person, elevator, button, eMetrics, outFilepath);
                        }

                    } else {
                        button.setCurrentFloor(button.determineNextFloor(person.getEntryFloor()));
                        elevator.display();
                        System.out.println("next floor=" + button.getCurrentFloor());
                        while(person.getEntryFloor() != button.getCurrentFloor()) {
                            LinkedListStack auxStack = new LinkedListStack();
                            // TODO could do an if statement here to check if there is anybody on this floor
                            if (!elevator.isEmpty()) {
                                ElevatorUtils.unloadPeople(button.getCurrentFloor(), auxStack, elevator, button, writer, outFilepath);
                            } else {
                                System.out.println("elev be empty yo");
                                eMetrics.setTotalEmptyElevator(eMetrics.getTotalEmptyElevator() + 1);
                            }
//                        button.zeroOutButtonForFloor(button.getCurrentFloor());
                            button.setCurrentFloor(button.determineNextFloor(person.getEntryFloor()));
                            elevator.display();
                            System.out.println("next floor=" + button.getCurrentFloor());
                        }
                        LinkedListStack auxStack = new LinkedListStack();
                        if (!elevator.isEmpty()) {
                            ElevatorUtils.unloadPeople(button.getCurrentFloor(), auxStack, elevator, button, writer, outFilepath);
                        } else {
                            System.out.println("elev be empty");
                            eMetrics.setTotalEmptyElevator(eMetrics.getTotalEmptyElevator() + 1);
                        }
//                    button.zeroOutButtonForFloor(button.getCurrentFloor());
                        if (elevator.maxCapacityReached()) {
                            try {
                                FileUtils.writeFileLineByLine(new File(outFilepath), "Elevator at max capacity! [" + person.getName() + "] was not able to get on at floor [" + button.getCurrentFloor() +"]");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            eMetrics.setTotalTurnaways(eMetrics.getTotalTurnaways() + 1);
                            System.out.println("wont be getting on bc of max capacity2= " + person.getName());
                        } else {
                            ElevatorUtils.loadPerson(person, elevator, button, eMetrics, outFilepath);
                            System.out.println("ELEV DISPLAY!!!!!");
                            elevator.display();
                            System.out.println(button.isGoingUp());
                        }
                    }
                } // TODO EO of buff reader

            }

            // gotta get rid of sue
            ElevatorUtils.finalUnload(elevator, button, writer, outFilepath);

            System.out.println("Ending Elevator Simulation.");
            try {
                FileUtils.writeFileLineByLine(new File(outFilepath), "\nEnd Elevator Simulation.");
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(eMetrics.toString());

            try {
                FileUtils.writeFileLineByLine(new File(outFilepath), eMetrics.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

