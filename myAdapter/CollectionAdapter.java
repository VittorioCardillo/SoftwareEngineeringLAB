package myAdapter;

import java.util.NoSuchElementException;
import java.util.Vector;

public class CollectionAdapter implements HCollection {
    

    /**
     * Store the CollectionAdapter information.
     */
    private Vector vett;


    /**
     * Create a new CollectionAdapter, initialized at zero.
     */
    public CollectionAdapter() {

        vett = new Vector();
    }

    /**
     * Appends the specified element to the end of this collection.
     *
     * In particular, the collection will refuse to add null elements
     *
     * @param   o - element to be appended to this collection.
     * @return  {@code true} (as per the general contract of the {@link HCollection#add} method).
     * @throws  NullPointerException - if the specified element is null.
     */
    public boolean add(Object o) {

        if (o == null)
            throw new NullPointerException("myAdapter.CollectionAdapter.add: o cannot be null");

        vett.addElement(o);
        return true;
    }



    /**
     * Appends all of the elements in the specified collection to the end of this collection, 
     * in the order that they are returned by the specified collection's iterator. 
     * The behavior of this operation is unspecified if the specified collection is modified while the operation is in progress.
     * (Note that this will occur if the specified collection is this collection, and it's non empty.)
     *
     * @param   c - collection whose elements are to be added to this collection. 
     * @return  {@code true} if this collection changed as a result of the call.
     * @throws  NullPointerException - if the specified collection is {@code null} or contains one or more null objects.
     */
    public boolean addAll(HCollection c) {
        
        if (c == null)
            throw new NullPointerException("myAdapter.CollectionAdapter.addAll: c cannot be null");
        HIterator check = c.iterator();
        while (check.hasNext())
            if (check.next() == null)
                throw new NullPointerException("myAdapter.CollectionAdapter.addAll: c cannot contain null elements");

        HIterator it = c.iterator();
        while (it.hasNext())
            add(it.next());
        
        return !c.isEmpty();
    }

    /**
     * Removes all of the elements from this collection. 
     * This collection will be empty after this call returns (unless it throws an exception). 
     */
    public void clear() {

        vett.removeAllElements();
    }
    /**
     * Returns {@code true} if this collection contains the specified element. 
     * More formally, returns {@code true} if and only if this collection contains at least one element {@code e} such
     * {@code (o==null ? e==null : o.equals(e))}.
     *
     * @param   o - element whose presence in this collection is to be tested.
     * @return  {@code true} if this collection contains the specified element.
     * @throws  NullPointerException - if the specified element is {@code null}.
     */
    public boolean contains(Object o) {

        if (o == null)
            throw new NullPointerException();

        return vett.contains(o);
    }

    /**
     * Returns true if this collection contains all of the elements of the specified collection.
     *
     * @param   c - collection to be checked for containment in this collection.
     * @return  {@code true} if this collection contains all of the elements of the specified collection. 
     * @throws  NullPointerException - if the specified collection contains one or more null elements.
     */    
    public boolean containsAll(HCollection c) {

        if (c == null)
            throw new NullPointerException("MyAdapter.CollectionAdapter.containsAll: c cannot be null");
        HIterator check = c.iterator();
            while (check.hasNext())
                if (check.next() == null)
                    throw new NullPointerException("MyAdapter.CollectionAdapter.containsAll: c cannot contain null elements");

        HIterator it = c.iterator();
        while (it.hasNext())
            if (!vett.contains(it.next()))
                return false;

        return true;
    }

    /**
     * Compares the specified object with this collection for equality. Returns {@code true} if and only if the specified object is also
     * a CollectionAdapter, both CollectionAdapter have the same size, and all corresponding pairs of elements in the two collections are <i>equal</i>.
     * (Two elements {@code e1} and {@code e2} are <i>equal</i> if {@code (e1==null ? e2==null : e1.equals(e2)).)} 
     * In other words, two collections are defined to be <i>equal</i> if they contain the same elements in the same order.
     *
     * @param   o - the object to be compared for equality with this collection.
     * @return  {@code true} if the specified object is equal to this collection.
     */
    public boolean equals(Object o) {

        if (vett == null && o == null)
            return true;
        if (vett == null || o == null || !(ListAdapter.class.isInstance(o)))
            return false;
        if (vett.size() != ((CollectionAdapter)o).size())
            return false;

        HIterator it = ((CollectionAdapter)o).iterator();

        for (int i = 0; i < vett.size(); i++)
            if (!(vett.elementAt(i)).equals(it.next()))
                return false;
        return true;
    }

    /**
     * Returns the hash code value for this collection. The hash code of a collection is defined to be the result of the following calculation:
     *  {@code hashCode = 1;
     *   HIterator i = list.iterator();
     *   while (i.hasNext()) {
     *       Object obj = i.next();
     *       hashCode = 31*hashCode + (obj==null ? 0 : obj.hashCode());
     *   }
     *  }
     * This ensures that {@code list1.equals(list2)} implies that {@code list1.hashCode()==list2.hashCode()} for any two collections, 
     * {@code list1} and {@code list2}, as required by the general contract {@code of Object.hashCode}.
     *
     * @return  the hash code value for this collection.
     */
    public int hashCode() {
        
        int hashCode = 1;
        HIterator i = iterator();
        while (i.hasNext()) {
            Object obj = i.next();
            hashCode = 31*hashCode + (obj==null ? 0 : obj.hashCode());
        }
        
        return hashCode;
    }

    /**
     * Returns {@code true} if this collection contains no elements.
     *
     * @return  {@code true} if this collection contains no elements.
     */
    public boolean isEmpty() {

        return vett.isEmpty();
    }

    /**
     * Returns an iterator over the elements in this collection in proper sequence.
     *
     * @return  an iterator over the elements in this collection in proper sequence.
     */
    public HIterator iterator() {

        return new Iterator(vett);
    }

    /**
     * Removes the first occurrence in this collection of the specified element. If this  collection does not contain the element, it is unchanged. 
     * More formally, removes the element with the lowest index i such that {@code (o==null ? get(i)==null : o.equals(get(i)))} (if such an element exists).
     *
     * @param   o - element to be removed from this collection, if present.
     * @return  {@code true} if this collection contained the specified element.
     * @throws  NullPointerException - if the specified element is null.
     */
    public boolean remove(Object o) {

        if (o == null)
            throw new NullPointerException("MyAdapter.CollectionAdapter.remove: o cannot be null");

        return vett.removeElement(o);
    }

    /**
     * Removes from this collection all the elements that are contained in the specified collection.
     *
     * @param   c - collection that defines which elements will be removed from this collection.
     * @return  {@code true} if this collection changed as a result of the call.
     * @throws  NullPointerException - if the specified collection is null or contains null elements.
     */
    public boolean removeAll(HCollection c) {

        if (c == null)
            throw new NullPointerException("MyAdapter.CollectionAdapter.removeAll: c cannot be null");
        HIterator check = c.iterator();
            while (check.hasNext())
                if (check.next() == null)
                    throw new NullPointerException("MyAdapter.CollectionAdapter.removeAll: c cannot contain null elements");

        HIterator it = c.iterator();
        int removed = 0;
        while (it.hasNext()) {
            if (remove(it.next()))
                removed++;
        }
            
        return (removed != 0);
    }

    /**
     * Retains only the elements in this collection that are contained in the specified collection. 
     * In other words, removes from this list all the elements that are not contained in the specified collection.
     *
     * @param   c - collection that defines which elements this set will retain.
     * @return  {@code true} if this collection changed as a result of the call.
     * @throws  NullPointerException - if the specified collection is null or contains null elements.
     */
    public boolean retainAll(HCollection c) {

        if (c == null)
            throw new NullPointerException("MyAdapter.CollectionAdapter.retainAll: c cannot be null");
        HIterator check = c.iterator();
            while (check.hasNext())
                if (check.next() == null)
                    throw new NullPointerException("MyAdapter.CollectionAdapter.retainAll: c cannot contain null elements");
        
        int removed = 0;
        HIterator it = iterator();
        while(it.hasNext())
            if(!c.contains(it.next()))
                it.remove();

        return (removed != 0);
    }

    /**
     * Returns the number of elements in this collection.
     *
     * @return the number of elements in this collection.
     */
    public int size() {

        return vett.size();
    }

    /**
     * Returns an array containing all of the elements in this collection in proper sequence.
     *
     * @return an array containing all of the elements in this collection in proper sequence.
     */
    public Object[] toArray() {

        Object[] arry = new Object[size()];

        for (int i = 0; i < size(); i++)
            arry[i] = vett.elementAt(i);

        return arry;
    }

    /**
     * Returns an array containing all of the elements in this collection in proper sequence; the runtime type of the returned array is that of the specified array.
     * Obeys the general contract of the {@code Collection.toArray(Object[])} method. 
     * 
     * @param   a - the array into which the elements of this collection are to be stored, if it is big enough; otherwise, a new {@code Object[]} array is created for this purpose.
     * @return  an array containing the elements of this collection.
     * @throws  ArrayStoreException - if the runtime type of the specified array is not a supertype of the runtime type of every element in this list.
     * @throws  NullPointerException - if the specified array is {@code null}.
     */
    public Object[] toArray(Object[] a) {

        if (a == null)
            throw new NullPointerException("MyAdapter.CollectionAdapter.toArray: a cannot be null");

        if (a.length < size())
            return toArray();

        for (int i = 0; i < size(); i++) {
            a[i] = vett.elementAt(i);
        }
        
        return a;
    }


    
    /*   INNER CLASS ITERATOR   */

    /**
     * Simple iterator over a CollectionAdapter object. It can traverse the vector associated
     * with the CollecitonAdapter forwards, perform remove operations.
     */
    private class Iterator implements HIterator {

        /**
         * Store the Iterator information.
        */
        protected Vector it_vec;

        /**
         * Current iterator position.
        */
        protected int index;

        /**
         * Used on the {@code remove} method, if {@code true} it is possible to call the {@code remove} method.
        */
        protected boolean next_called;
        

        /**
         * Create a new iterator on a specified vector {@code v}.
         * 
         * @param   v - Vector used from the iterator.
         */
        public Iterator(Vector v) {

            this(v, 0);
        }

        /**
         * Create a new iterator on a specified vector {@code v} starting from a specified position {@code i}
         * 
         * @param   v - Vector used from the iterator.
         * @param   i - Starting index.
         */
        public Iterator(Vector v, int i) {

            it_vec = v;
            index = i;
            next_called = false;
        }
        
        /**
         * Returns {@code true} if the iteration has more elements. 
         * (In other words, returns {@code true} if {@code next} would return an element rather than throwing an exception.) 
         */
        public boolean hasNext() {

            if (index < it_vec.size())
                return true;
            return false;
        }

        /**
         * Returns the next element in the iteration. 
         * 
         * @return  the next element in the iteration. 
         * @throws  NoSuchElementException - iteration has no more elements.
         */
        public Object next() {

            if (!hasNext())
                throw new NoSuchElementException("MyAdapter.CollectionAdapter.Iterator.next: there are no other elements");

            Object obj = it_vec.elementAt(index);
            index++;
            next_called = true;
            return obj;
        }
    
        /**
         * Removes from the underlying collection the last element returned by the iterator. 
         * This method can be called only once per call to {@code next}. 
         * The behavior of an iterator is unspecified if the underlying collection is modified while the 
         * iteration is in progress in any way other than by calling this method. 
         */
        public void remove() {

            if (!next_called)
                throw new IllegalStateException("MyAdapter.CollectionAdapter.Iterator.remove: can call remove only after a next() call");

            index--;
            next_called = false;
            it_vec.removeElementAt(index);
        }
    }

}
