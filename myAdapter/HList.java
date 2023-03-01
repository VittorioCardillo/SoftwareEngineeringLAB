package myAdapter;

public interface HList extends HCollection {

    void add(int index, Object element);

    boolean add(Object o);

    boolean addAll(HCollection c);

    boolean addAll(int index, HCollection c);

    void clear();

    boolean contains(Object o);

    boolean containsAll(HCollection c);

    boolean equals(Object o);

    Object get(int index);

    int hashCode();

    int indexOf(Object o);

    boolean isEmpty();
    
    HIterator iterator();

    int lastIndexOf(Object o);

    HListIterator listIterator();

    HListIterator listIterator(int index);

    Object remove(int index);

    boolean remove(Object o);

    boolean removeAll(HCollection c);

    boolean retainAll(HCollection c);

    Object set(int index, Object element);

    int size();

    HList subList(int fromIndex, int toIndex);

    Object[] toArray();

    Object[] toArray(Object[] a);
}