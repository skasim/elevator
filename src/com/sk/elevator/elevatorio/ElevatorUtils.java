package com.sk.elevator.elevatorio;

import com.sk.elevator.Elevator;
import com.sk.elevator.button.Button;
import com.sk.elevator.person.Person;
import com.sk.elevator.stack.LinkedListStack;
import com.sk.elevator.stack.SNode;

public class ElevatorUtils {

    public static void loadPerson(Person person, LinkedListStack elevator, Button button) {
        elevator.push(person);
        button.pushFloorRequestedButton(person.getExitFloor());
        // TODO print name of person getting on plus floor
    }

    public static void finalUnload(LinkedListStack elevator, Button button) {
        if (!elevator.isEmpty()) {
            LinkedListStack auxStack = new LinkedListStack();

            while(!elevator.isEmpty()) {
                Person tmpPerson = elevator.pop();
                button.calculateMaxFloorForFinalUnload(tmpPerson.getExitFloor());
                auxStack.push(tmpPerson);
            }

            while(!auxStack.isEmpty()) {
                Person tmpPerson = auxStack.pop();
                elevator.push(tmpPerson);
            }

            while(button.getMaxFloor() > button.getCurrentFloor() && !elevator.isEmpty()) {
                LinkedListStack tmpStack = new LinkedListStack();
                button.setCurrentFloor(button.getCurrentFloor()+1);
                if (button.returnNumberOfFloorRequests(button.getCurrentFloor()) > 0) {
                    unloadPeople(button.getCurrentFloor(), tmpStack, elevator, button);
                }
            }
            // now we are at max floor, so we should go down
            while (button.getCurrentFloor() !=1 || !elevator.isEmpty()) {
                LinkedListStack tmpStack = new LinkedListStack();
                button.setCurrentFloor(button.getCurrentFloor()-1);
                if (button.returnNumberOfFloorRequests(button.getCurrentFloor()) > 0) {
                    unloadPeople(button.getCurrentFloor(), tmpStack, elevator, button);
                }

            }


        }
        System.out.println("current floor=" + button.getCurrentFloor());

        System.out.println("max floor=" + button.getMaxFloor());
        elevator.display();

    }

    public static void unloadPeople(int currentFloor, LinkedListStack auxStack, LinkedListStack elevator,
                                    Button button) {
        int numOfPeopleToUnload = button.returnNumberOfFloorRequests(currentFloor);

        int i = 0;
        while (i < numOfPeopleToUnload) {
            System.out.println(button.toString());
            System.out.println("numOfppltounload=" + numOfPeopleToUnload);
            System.out.println("ELEVATOR SIZE="+elevator.size());
            Person person = elevator.pop();
            if (person.getExitFloor() == currentFloor) {
                System.out.println("OUTTA HERE=" + person.toString());
                i++;
                // TODO add a print out to document
            } else {
                person.setExitTracker(person.getExitTracker()+1);
                auxStack.push(person);
                System.out.println("sadly this person had to temp get off=" + person.getName());
                // TODO write this out to document
            }
        }
        button.zeroOutButtonForFloor(currentFloor);
        reloadPeople(auxStack, elevator);
    }

    public static void reloadPeople(LinkedListStack auxStack, LinkedListStack elevator) {
        while (!auxStack.isEmpty()) {
            Person person = auxStack.pop();
            elevator.push(person);
        }
    }
}
