public class DoublyLinkedList<T> implements Iterable<T> {

    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;

    @SuppressWarnings("hiding")
    private class Node<T> {

        T data;
        Node<T> prev, next;

        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    public void clear() {
        Node<T> trav = head;
        while (trav != null) {
            Node<T> next = trav.next;
            trav.prev = trav.next = null;
            trav = next;
        }
        // head = tail = trav = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void addFirst(T item) {
        if (isEmpty()) {
            head = tail = new Node<>(item, null, null);
        } else {
            head.prev = new Node<>(item, null, head);
            head = head.prev;
        }
        size++;
    }

    public void addLast(T item) {
        if (isEmpty()) {
            head = tail = new Node<>(item, null, null);
        } else {
            tail.next = new Node<>(item, tail, null);
            tail = tail.next;
        }
        size++;
    }

    public void append(T item) {
        addLast(item);
    }

    public void add(T item) {
        addLast(item);
    }

    public T first() {
        return head.data;
    }

    public T last() {
        return tail.data;
    }

    public T removeFirst() {
        if (isEmpty()) {
            throw new RuntimeException("Empty list");
        }
        
        T removedData = head.data;
        head = head.next;
        --size;

        if (isEmpty()) {
            tail = null; 
        } else {
            head.prev = null;
        }

        return removedData;
    }

    public T removeLast() {
        if(isEmpty()) {
            throw new RuntimeException("Empty list");
        }

        T removedData = tail.data;
        tail = tail.prev;
        --size;

        if (isEmpty()) {
            head = null;
        } else {
            tail.next = null;
        }

        return removedData;
    }

    private T remove(Node<T> node) {
        if (node.prev == null) {return removeFirst();}
        else if (node.next == null) {return removeLast();}
        
        T removedData = node.data;
        node.prev.next = node.next;
        node.next.prev = node.prev;
        --size;

        return removedData;
    }

    public T removeAt(int index) {
        if (index < 0 || index >= size) {throw new IndexOutOfBoundsException();}

        Node<T> trav;

        if (index <= size/2) { // search from head side
            trav = head;
            for (int i = 0; i != index; i++) {
                trav = trav.next;
            }
        } else { // search from tail side
            trav = tail;
            for (int i = size - 1; i != index; i--) {
                trav = trav.prev;
            }
        }

        return remove(trav);
    }

    public int indexOf(Object item) {
        int index = 0;
        Node<T> trav;
        for (trav = head; trav != null; trav = trav.next, index++) {
            if (item.equals(trav.data)) {
                return index;
            }
        }
        return -1;
    }

    public boolean contains(Object item) {
        return indexOf(item) != -1;
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return new java.util.Iterator<T>() {
            private Node<T> trav = head;
            @Override public boolean hasNext() {
                return trav.next != null;
            }
            @Override public T next() {
                T currentData = trav.data;
                trav = trav.next;
                return currentData;
            }
        };
    }

    @Override public String toString() {
        StringBuilder sb = new StringBuilder().append("[");
        Node<T> trav = head;
        for (int i = 0; i < size - 1; i++, trav = trav.next) {
            sb.append(trav.data).append(", ");
        }
        sb.append(trav.data).append("]");
        return sb.toString();
    }
}
