public class GTUArrayList<E> implements GTUList<E>{
    private Object[] elements;
    private int size;
    private int capacity;

    @SuppressWarnings("unchecked")
    public GTUArrayList() {
        this.capacity = 1000; 
        this.elements = new Object[capacity];
        this.size = 0;
    }

    @Override
    public void add(E element) {
        if (size == capacity) {
            resize();
        }
        elements[size++] = element;
    }

    @Override
    public void remove(E element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                for (int j = i; j < size - 1; j++) {
                    elements[j] = elements[j + 1];
                }
                elements[--size] = null;
                return;
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (E) elements[index];
    }

    @Override
    public int size() {
        return size;
    }
    @SuppressWarnings("unchecked")
    private void resize() {
        capacity *= 2;
        Object[] newElements = new Object[capacity];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }

    @Override
    public boolean contains(E element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                return true;
            }
        }
        return false;
    }
    
}
