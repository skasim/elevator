package com.sk.elevator.person;

import com.sk.elevator.button.Button;
import com.sk.elevator.exceptions.NotValidInputException;
import com.sk.elevator.metrics.ElevatorMetrics;
import com.sk.elevator.stack.LinkedListStack;

import java.io.File;

import static com.sk.elevator.elevatorio.ElevatorUtils.loadPerson;
import static com.sk.elevator.elevatorio.ElevatorUtils.unloadPeople;

/**
 * Person object representing the rider.
 *
 * @author Samra Kasim
 */
public class Person {

    String name;
    int entryFloor;
    int exitFloor;
    int exitTracker;    // Track the number of temporary exits made by the rider

    /**
     * Constructor to instantiate object with no parameters provided.
     */
    public Person() {
        this.exitTracker = 0;
    }

    /**
     * Constructor to instantiate object by providing certain parameters.
     * @param name: String representing rider's name.
     * @param entryFloor: int representing the floor the rider got on the elevator.
     * @param exitFloor: int representing the floor the rider will get off.
     */
    public Person(String name, int entryFloor, int exitFloor) {
        this.name = name;
        this.entryFloor = entryFloor;
        this.exitFloor = exitFloor;
        this.exitTracker = 0;
    }

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
    public static void processPerson(Person person, LinkedListStack elevator, Button button, ElevatorMetrics eMetrics,
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
                                unloadPeople(button.getCurrentFloor(), elevator, button, eMetrics, outFile);
                            }

                            else {
                                eMetrics.setTotalEmptyElevator(eMetrics.getTotalEmptyElevator() + 1);
                            }
                            button.setCurrentFloor(button.determineNextFloor(person.getEntryFloor()));
                        }

                        if (!elevator.isEmpty()) {
                            unloadPeople(button.getCurrentFloor(), elevator, button, eMetrics, outFile);
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

    /**
     * Return string representing the Person object
     * @return String
     */
    public String toString( ) {
        return "Name: " + name + "; " +
                "Entry Floor: " + entryFloor + "; " +
                "Exit Floor: " + exitFloor + "; " +
                "Exit Tracker: " + exitTracker;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public int getEntryFloor() {
        return entryFloor;
    }

    public int getExitFloor() {
        return exitFloor;
    }

    public int getExitTracker() {
        return exitTracker;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEntryFloor(int entryFloor) {
        this.entryFloor = entryFloor;
    }

    public void setExitFloor(int exitFloor) {
        this.exitFloor = exitFloor;
    }

    public void setExitTracker(int exitTracker) {
        this.exitTracker = exitTracker;
    }
}
