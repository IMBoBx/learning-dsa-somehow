// similar to Stack except FIFO (First In, First Out)

public final class Queue<T> implements Iterable<T> {

    public DoublyLinkedList<T> queue = new DoublyLinkedList<>();

    public Queue() {
    }

    public Queue(T firstItem) {
        enqueue(firstItem);
    }

    public int size() {
        return queue.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void enqueue(T item) { // adding to the last - aka adding/offering
        queue.addLast(item);
    }

    public T dequeue() { // remove from first - aka polling
        if (isEmpty()) {
            throw new java.util.EmptyStackException();
        }
        return queue.removeFirst();
    }

    public T peek() {
        if (isEmpty()) {
            throw new java.util.EmptyStackException();
        }
        return queue.first();
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return queue.iterator();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
