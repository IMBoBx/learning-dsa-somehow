
import java.util.EmptyStackException;

public class Stack<T> implements Iterable<T> {

    private final DoublyLinkedList<T> list = new DoublyLinkedList<>(); // using DoublyLinkedList.java, can also be implemented using java.util.LinkedList

    public Stack() {
    }

    public Stack(T item) {
        push(item);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public final void push(T item) {
        list.addLast(item);
    }

    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list.removeLast();
    }

    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list.last();
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return list.iterator();
    }
    
    @Override
    public String toString() {
        return list.toString();
    }
}
