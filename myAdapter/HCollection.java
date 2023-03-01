package myAdapter;

public interface HCollection {

    boolean add(Object o);

    boolean addAll(HCollection c);

    void clear();

    boolean contains(Object o);

    boolean containsAll(HCollection c);

    boolean equals(Object o);

    int hashCode();

    boolean isEmpty();

    HIterator iterator();

    boolean remove(Object o);

    boolean removeAll(HCollection c);

    boolean retainAll(HCollection c);

    int size();

    Object[] toArray();

    Object[] toArray(Object[] a);
}