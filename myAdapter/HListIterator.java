package myAdapter;

public interface HListIterator extends HIterator {

    void add(Object o);

    boolean hasNext();

    boolean hasPrevious();

    Object next();

    int nextIndex();

    Object previous();

    int previousIndex();

    void remove();

    void set(Object o);
}