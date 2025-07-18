public interface GTUList <E> {
    
    void add(E element);
    void remove(E element);
    E get(int index);
    int size();
    boolean contains(E element);
    
}
