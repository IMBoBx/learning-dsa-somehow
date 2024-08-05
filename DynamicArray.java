
public class DynamicArray<T> implements Iterable<T> {

    private T[] arr;
    private int len = 0; // "length" of the array returned to the user
    private int capacity = 0; // actual "length" of the array -> not shown to the user

    public DynamicArray() {
        this(16);
    }

    public DynamicArray(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Illegal capacity: " + capacity);
        }

        this.capacity = capacity;
        arr = (T[]) new Object[capacity];
    }

    public int size() {
        return len;
    }

    public boolean isEmpty() {
        return len == 0;
    }

    public T get(int index) {
        return arr[index];
    }

    public void replace(int index, T item) {
        arr[index] = item;
    }

    public void clear() {
        for (int i = 0; i < len; i++) {
            arr[i] = null;
        }
        len = 0;
    }

    public void add(T item) {
        if (capacity <= len + 1) {
            if (capacity == 0) {
                capacity = 1;
            } else {
                capacity *= 2; // or just increase the size by some 
                T[] newArr = (T[]) new Object[capacity];
                for (int i = 0; i < len; i++) {
                    newArr[i] = arr[i];
                }
                arr = newArr;
            }
        }

        arr[len++] = item;
    }

    public T removeAt(int removedIndex) {
        if (removedIndex >= len) {
            throw new IndexOutOfBoundsException();
        }

        T data = arr[removedIndex];

        T[] newArr = (T[]) new Object[len];
        for (int i = 0, j = 0; i < len; i++, j++) {
            if (j == removedIndex) {
                j--;
            } else {
                newArr[j] = arr[i];
            }
        }
        arr = newArr;
        capacity = --len;
        return data;
    }

    public boolean remove(T item) {
        for (int i = 0; i < len; i++) {
            if (arr[i].equals(item)) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    public int indexOf(T item) {
        for (int i = 0; i < len; i++) {
            if (arr[i].equals(item)) {
                return i;
            }
        }
        return -1; // not found
    }

    public boolean contains(T item) {
        return indexOf(item) != -1;
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return new java.util.Iterator<T>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < len;
            }

            @Override
            public T next() {
                return arr[index++];
            }
        ;
    }

    ;
    }   

    
    @Override
    public String toString() {
        if (len == 0) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder(len).append("[");
            for (int i = 0; i < len - 1; i++) {
                sb.append(arr[i] + ", ");
            }
            return sb.append(arr[len - 1] + "]").toString();
        }
    }
}
