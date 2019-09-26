package com.sk.elevator.stack;

import com.sk.elevator.person.Person;

/**
 * Class to implement the LinkedListStack featuring Person as the data object. Object contains the key stack methods:
 * push, pop, peek, isEmpty and additional methods such as size, maxCapacity reached (i.e., size >= 5).
 *
 * @author Samra Kasim
 */
public class LinkedListStack {

    private SNode top;
    private int size;

    /**
     * Constuctor to instantiate a LinkedListStack object setting the top node to null and size to 0.
     */
    public LinkedListStack() {
        this.top = null;
        this.size = 0;
    }

    /**
     * Method to push a Person object into the stack and increment the size of the stack by one.
     * @param person Person object representing a rider
     */
    public void push(Person person) {
        SNode node = new SNode();
        node.data = person;
        SNode temp = this.top;
        node.next = temp;
        this.top = node;
        this.size++;
    }

    /**
     * Method to pop a node from the stack, decrement the size by one and return the Person object.
     * @return Person object
     */
    public Person pop() {
        SNode temp = this.top;
        this.top = this.top.next;
        temp.next = null;
        this.size--;
        return temp.data;
    }

    /**
     * Method to return if stack is empty
     * @return
     */
    public boolean isEmpty() {
        return (this.size == 0);
    }

    /**
     * Method to peek at the data in the top node without changing the stack.
     * @return Person object
     */
    public Person peek() {
        return this.top.data;
    }

    /**
     * Method to return the size of the stack.
     * @return int value representing the number of nodes in the stack.
     */
    public int size() {
        return this.size;
    }
}
