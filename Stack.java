// basically just an Array of sorts, which follows LIFO (Last In, First Out)

import java.util.EmptyStackException;

public class Stack<T> implements Iterable<T> {

    private final DoublyLinkedList<T> stack = new DoublyLinkedList<>(); // using DoublyLinkedList.java, can also be implemented using java.util.LinkedList

    public Stack() {
    }

    public Stack(T item) {
        push(item);
    }

    public int size() {
        return stack.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public final void push(T item) {
        stack.addLast(item);
    }

    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stack.removeLast();
    }

    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stack.last();
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return stack.iterator();
    }
    
    @Override
    public String toString() {
        return stack.toString();
    }
}
