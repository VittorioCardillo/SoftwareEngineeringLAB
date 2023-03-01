package myAdapter;

import java.util.NoSuchElementException;
import java.util.Vector;

public class ListAdapter implements HList {
    
    /**
     * Store the ListAdapter information.
     */
    private Vector vec;

    /**
     * Create a new ListAdapter, initialized at zero.
     */
    public ListAdapter(){

        this(new Vector());
    }

    /**
     * Create a new ListAdapter from a Vector.
     *
     * @param   v - Vector used for the initialization.
     */
    public ListAdapter(Vector v){

        vec = v;
    }

     
    /**
     * Inserts the specified element at the specified position in this list.
     * Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to their indices).
     * 
     * @param   index - index at which the specified element is to be inserted.
     * @param   element - element to be inserted.
     * @throws  ClassCastException - if the class of the specified element prevents it from being added to this list.
     * @throws  NullPointerException - if the specified element is null.
     * @throws  IndexOutOfBoundsException - if the index is out of range {@code (index < 0 || index > size())}.
     */
    public void add(int index, Object element) { 

        if (element == null)
            throw new NullPointerException("myAdapter.ListAdapter.add: element cannot be null");
        if (index < 0 || index > vec.size())
            throw new IndexOutOfBoundsException("myAdapter.ListAdapter.add: index is not valid");

        vec.insertElementAt(element, index);
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * Lists that support this operation may place limitations on what elements may be added to this list. 
     * In particular, the lists will refuse to add null elements
     *
     * @param   o - element to be appended to this list.
     * @return  {@code true} (as per the general contract of the {@link HCollection#add} method).
     * @throws  NullPointerException - if the specified element is null.
     */
    public boolean add(Object o) {

        if (o == null)
            throw new NullPointerException("myAdapter.ListAdapter.add: o cannot be null");

        vec.addElement(o);
        return true;
    }

    /**
     * Appends all of the elements in the specified collection to the end of this list, 
     * in the order that they are returned by the specified collection's iterator. 
     * The behavior of this operation is unspecified if the specified collection is modified while the operation is in progress.
     * (Note that this will occur if the specified collection is this list, and it's nonempty.)
     *
     * @param   c - collection whose elements are to be added to this list. 
     * @return  {@code true} if this list changed as a result of the call.
     * @throws  NullPointerException - if the specified collection is {@code null} or contains one or more null objects.
     */
    public boolean addAll(HCollection c) {
        
        if (c == null)
            throw new NullPointerException("myAdapter.ListAdapter.addAll: c cannot be null");
        HIterator check = c.iterator();
        while (check.hasNext())
            if (check.next() == null)
                throw new NullPointerException("myAdapter.ListAdapter.addAll: c cannot contain null elements");

        HIterator it = c.iterator();
        while (it.hasNext())
            add(it.next());
        
        return !c.isEmpty();
    }

    /**
     * Inserts all of the elements in the specified collection into this list at the specified position
     * Shifts the element currently at that position (if any) and any subsequent elements to the right (increases their indices). 
     * The new elements will appear in this list in the order that they are returned by the specified collection's iterator.
     * The behavior of this operation is unspecified if the specified collection is modified while the operation is in progress. 
     * (Note that this will occur if the specified collection is this list, and it's nonempty.) 
     *
     * @param   index - index at which to insert first element from the specified collection.
     * @param   c - elements to be inserted into this list. 
     * @return  {@code true} if this list changed as a result of the call.
     * @throws  NullPointerException - if the specified collection is {@code null} or contains one or more null objects.
     * @throws  IndexOutOfBoundsException - if the index is out of range {@code (index < 0 || index > size())}.
     */
    public boolean addAll(int index, HCollection c) {

        if (c == null)
            throw new NullPointerException("myAdapter.ListAdapter.addAll: c cannot be null");
        HIterator check = c.iterator();
        while (check.hasNext())
            if (check.next() == null)
                throw new NullPointerException("myAdapter.ListAdapter.addAll: c cannot contain null elements");
        if (index < 0 || index > size())
            throw new IndexOutOfBoundsException("myAdapter.ListAdapter.addAll: index is not valid");

        int i = index;
        HIterator it = c.iterator();
        while (it.hasNext()) {
            add(i, it.next());
            i++;
        }     

        return !c.isEmpty();
    }

    /**
     * Removes all of the elements from this list. 
     * This list will be empty after this call returns (unless it throws an exception). 
     */
    public void clear() {

        vec.removeAllElements();
    }

    /**
     * Returns {@code true} if this list contains the specified element. 
     * More formally, returns {@code true} if and only if this list contains at least one element {@code e} such
     * {@code (o==null ? e==null : o.equals(e))}.
     *
     * @param   o - element whose presence in this list is to be tested.
     * @return  {@code true} if this list contains the specified element.
     * @throws  NullPointerException - if the specified element is {@code null}.
     */
    public boolean contains(Object o) {

        if (o == null)
            throw new NullPointerException();

        return vec.contains(o);
    }

    /**
     * Returns true if this list contains all of the elements of the specified collection.
     *
     * @param   c - collection to be checked for containment in this list.
     * @return  {@code true} if this list contains all of the elements of the specified collection. 
     * @throws  NullPointerException - if the specified collection contains one or more null elements.
     */    
    public boolean containsAll(HCollection c) {

        if (c == null)
            throw new NullPointerException("MyAdapter.ListAdapter.containsAll: c cannot be null");
        HIterator check = c.iterator();
            while (check.hasNext())
                if (check.next() == null)
                    throw new NullPointerException("MyAdapter.ListAdapter.containsAll: c cannot contain null elements");

        HIterator it = c.iterator();
        while (it.hasNext())
            if (!vec.contains(it.next()))
                return false;

        return true;
    }

    /**
     * Compares the specified object with this list for equality. Returns {@code true} if and only if the specified object is also
     * a ListAdapter, both ListAdapter have the same size, and all corresponding pairs of elements in the two lists are <i>equal</i>.
     * (Two elements {@code e1} and {@code e2} are <i>equal</i> if {@code (e1==null ? e2==null : e1.equals(e2)).)} 
     * In other words, two lists are defined to be <i>equal</i> if they contain the same elements in the same order.
     *
     * @param   o - the object to be compared for equality with this list.
     * @return  {@code true} if the specified object is equal to this list.
     */
    public boolean equals(Object o) {

        if (vec == null && o == null)
            return true;
        if (vec == null || o == null || !(ListAdapter.class.isInstance(o)))
            return false;
        if (vec.size() != ((ListAdapter)o).size())
            return false;
/*
        Enumeration e = vec.elements();
        HIterator it = ((ListAdapter)o).iterator();
        
        while (e.hasMoreElements()) {
            if (!e.nextElement().equals(it.next()))
                return false;
        }
        return true;
*/
        HIterator it = ((ListAdapter)o).iterator();

        for (int i = 0; i < vec.size(); i++)
            if (!(vec.elementAt(i)).equals(it.next()))
                return false;
        return true;
    }

    /**
     * Returns the element at the specified position in this list. 
     *
     * @param   index - index of element to return.
     * @return  the element at the specified position in this list.
     * @throws  IndexOutOfBoundsException - if the index is out of range {@code (index < 0 || index >= size())}.
     */
    public Object get(int index) {
        
        if (index < 0 || index >= vec.size())
            throw new IndexOutOfBoundsException("MyAdapter.ListAdapter.get: index is not valid");
        
        return vec.elementAt(index);
    }

    /**
     * Returns the hash code value for this list. The hash code of a list is defined to be the result of the following calculation:
     *  {@code hashCode = 1;
     *   HIterator i = list.iterator();
     *   while (i.hasNext()) {
     *       Object obj = i.next();
     *       hashCode = 31*hashCode + (obj==null ? 0 : obj.hashCode());
     *   }
     *  }
     * This ensures that {@code list1.equals(list2)} implies that {@code list1.hashCode()==list2.hashCode()} for any two lists, 
     * {@code list1} and {@code list2}, as required by the general contract {@code of Object.hashCode}.
     *
     * @return  the hash code value for this list.
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
     * Returns the index in this list of the first occurrence of the specified element, or -1 if this list does not contain this element.
     * More formally, returns the lowest index i such that {@code (o==null ? get(i)==null : o.equals(get(i)))}, or -1 if there is no such index.
     *
     * @param   o - element to search for.
     * @return  the index in this list of the first occurrence of the specified element, or -1 if this list does not contain this element.
     * @throws  NullPointerException - if the specified element is null.
     */
    public int indexOf(Object o) {

        if (o == null)
            throw new NullPointerException("MyAdapter.ListAdapter.indexOf: o cannot be null");

        return vec.indexOf(o);
    }

    /**
     * Returns {@code true} if this list contains no elements.
     *
     * @return  {@code true} if this list contains no elements.
     */
    public boolean isEmpty() {

        return vec.isEmpty();
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * @return  an iterator over the elements in this list in proper sequence.
     */
    public HIterator iterator() {

        return new Iterator(vec);
    }

    /**
     * Returns the index in this list of the last occurrence of the specified element, or -1 if this list does not contain this element. 
     * More formally, returns the highest index i such that {@code (o==null ? get(i)==null : o.equals(get(i)))}, or -1 if there is no such index.
     *
     * @param   o - element to search for.
     * @return  the index in this list of the last occurrence of the specified element, or -1 if this list does not contain this element.
     * @throws  NullPointerException - if the specified element is null.
     */
    public int lastIndexOf(Object o) {

        if (o == null)
            throw new NullPointerException("MyAdapter.ListAdapter.lastIndexOf: o cannot be null");

        return vec.lastIndexOf(o);
    }
    
    /**
     * Returns a list iterator of the elements in this list (in proper sequence).
     *
     * @return a list iterator of the elements in this list (in proper sequence).
     */
    public HListIterator listIterator() {

        return new ListIterator(vec);
    }

    /**
     * Returns a list iterator of the elements in this list (in proper sequence), starting at the specified position in this list. 
     * The specified index indicates the first element that would be returned by an initial call to the {@code next} method. 
     * An initial call to the {@code previous} method would return the element with the specified index minus one.
     *
     * @param   index - index of first element to be returned from the list iterator (by a call to the {@code next} method).
     * @return  a list iterator of the elements in this list (in proper sequence), starting at the specified position in this list.
     * @throws  IndexOutOfBoundsException - if the index is out of range {@code (index < 0 || index > size())}.
     */
    public HListIterator listIterator(int index) {

        if (index < 0 || index > vec.size())
            throw new IndexOutOfBoundsException("MyAdapter.ListAdapter.listIterator: index is not valid");

        return new ListIterator(vec, index);
    }

    /**
     * Removes the element at the specified position in this list. Shifts any subsequent elements to the left (subtracts one from their indices). 
     * Returns the element that was removed from the list.
     *
     * @param   index - the index of the element to removed.
     * @return  the element previously at the specified position.
     * @throws  IndexOutOfBoundsException - if the index is out of range {@code (index < 0 || index >= size())}.
     */
    public Object remove(int index) {

        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException("MyAdapter.ListAdapter.remove: index is not valid");

        Object obj = vec.elementAt(index);
        vec.removeElementAt(index);
        return obj;
    }

    /**
     * Removes the first occurrence in this list of the specified element. If this  list does not contain the element, it is unchanged. 
     * More formally, removes the element with the lowest index i such that {@code (o==null ? get(i)==null : o.equals(get(i)))} (if such an element exists).
     *
     * @param   o - element to be removed from this list, if present.
     * @return  {@code true} if this list contained the specified element.
     * @throws  NullPointerException - if the specified element is null.
     */
    public boolean remove(Object o) {

        if (o == null)
            throw new NullPointerException("MyAdapter.ListAdapter.remove: o cannot be null");

        return vec.removeElement(o);
    }


    /**
     * Removes from this list all the elements that are contained in the specified collection.
     *
     * @param   c - collection that defines which elements will be removed from this list.
     * @return  {@code true} if this list changed as a result of the call.
     * @throws  NullPointerException - if the specified collection is null or contains null elements.
     */
    public boolean removeAll(HCollection c) {

        if (c == null)
            throw new NullPointerException("MyAdapter.ListAdapter.removeAll: c cannot be null");
        HIterator check = c.iterator();
            while (check.hasNext())
                if (check.next() == null)
                    throw new NullPointerException("MyAdapter.ListAdapter.removeAll: c cannot contain null elements");

        HIterator it = c.iterator();
        int removed = 0;
        while (it.hasNext()) {
            if (remove(it.next()))
                removed++;
        }
            
        return (removed != 0);
    }

    /**
     * Retains only the elements in this list that are contained in the specified collection. 
     * In other words, removes from this list all the elements that are not contained in the specified collection.
     *
     * @param   c - collection that defines which elements this set will retain.
     * @return  {@code true} if this list changed as a result of the call.
     * @throws  NullPointerException - if the specified collection is null or contains null elements.
     */
    public boolean retainAll(HCollection c) {

        if (c == null)
            throw new NullPointerException("MyAdapter.ListAdapter.retainAll: c cannot be null");
        HIterator check = c.iterator();
            while (check.hasNext())
                if (check.next() == null)
                    throw new NullPointerException("MyAdapter.ListAdapter.retainAll: c cannot contain null elements");
        
        int removed = 0;
        HIterator it = iterator();
        while(it.hasNext())
            if(!c.contains(it.next()))
                it.remove();

        return (removed != 0);
    }

    /**
     * Replaces the element at the specified position in this list with the specified element.
     *
     * @param   index - index of element to replace.
     * @param   element - element to be stored at the specified position.
     * @return  the element previously at the specified position.
     * @throws  NullPointerException - if the specified element is null.
     * @throws  IndexOutOfBoundsException - if the index is out of range {@code (index < 0 || index >= size())}.
     */
    public Object set(int index, Object element) {

        if (element == null)
            throw new NullPointerException("MyAdapter.ListAdapter.set: element cannot be null");
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException("MyAdapter.ListAdapter.sey: index is not valid");

        Object obj = vec.elementAt(index);
        vec.setElementAt(element, index);

        return obj;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list.
     */
    public int size() {

        return vec.size();
    }

    /**
     * Returns a view of the portion of this list between the specified {@code fromIndex}, inclusive, and {@code toIndex}, exclusive. 
     * (If {@code fromIndex} and {@code toIndex} are equal, the returned list is empty.) 
     * The returned list is backed by this list, so non-structural changes in the returned list are reflected in this list, and vice-versa.
     * The returned list supports all of the optional list operations supported by this list.
     * This method eliminates the need for explicit range operations (of the sort that commonly exist for arrays). 
     * Any operation that expects a list can be used as a range operation by passing a subList view instead of a whole list. 
     * For example, the following idiom removes a range of elements from a list:
     * {@code list.subList(from, to).clear();}
     *
     * Similar idioms may be constructed for {@code indexOf} and {@code lastIndexOf}, and all of the algorithms in the 
     * {@code Collections} class can be applied to a subList.
     * The semantics of the list returned by this method become undefined if the backing list (i.e., this list) is 
     * <i>structurally modified</i> in any way other than via the returned list. 
     * (Structural modifications are those that change the size of this list, or otherwise perturb it in such a fashion that iterations in progress may yield incorrect results.)
     *
     * @param   fromIndex - low endpoint (inclusive) of the subList.
     * @param   toIndex - high endpoint (exclusive) of the subList.
     * @return  a view of the specified range within this list.
     * @throws  IndexOutOfBoundsException - for an illegal endpoint index value {@code (fromIndex < 0 || toIndex > size || fromIndex > toIndex)}.
     */
    public HList subList(int fromIndex, int toIndex) {

        if (fromIndex < 0 || toIndex > vec.size() || fromIndex > toIndex)
            throw new IndexOutOfBoundsException("MyAdapter.ListAdapter.subList: one index is not valid");

        return new SubList(fromIndex, toIndex);
    }

    /**
     * Returns an array containing all of the elements in this list in proper sequence.
     *
     * @return an array containing all of the elements in this list in proper sequence.
     */
    public Object[] toArray() {

        Object[] arry = new Object[size()];

        for (int i = 0; i < size(); i++)
            arry[i] = get(i);

        return arry;
    }

    /**
     * Returns an array containing all of the elements in this list in proper sequence; the runtime type of the returned array is that of the specified array.
     * Obeys the general contract of the {@code Collection.toArray(Object[])} method. 
     * 
     * @param   a - the array into which the elements of this list are to be stored, if it is big enough; otherwise, a new {@code Object[]} array is created for this purpose.
     * @return  an array containing the elements of this list.
     * @throws  ArrayStoreException - if the runtime type of the specified array is not a supertype of the runtime type of every element in this list.
     * @throws  NullPointerException - if the specified array is {@code null}.
     */
    public Object[] toArray(Object[] a) {

        if (a == null)
            throw new NullPointerException("MyAdapter.ListAdapter.toArray: a cannot be null");

        if (a.length < size())
            return toArray();

        for (int i = 0; i < size(); i++) {
            a[i] = get(i);
        }
        
        return a;
    }



    /*   INNER CLASS ITERATOR   */

    /**
     * Simple iterator over a ListAdapter object. It can traverse the vector associated
     * with the ListAdapter forwards, perform remove operations.
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
                throw new NoSuchElementException("MyAdapter.ListAdapter.Iterator.next: there are no other elements");

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
                throw new IllegalStateException("MyAdapter.ListAdapter.Iterator.remove: can call remove only after a next() call");

            index--;
            next_called = false;
            it_vec.removeElementAt(index);
        }
    }


    /*   INNER CLASS LISTITERATOR   */

    private class ListIterator extends Iterator implements HListIterator{


        private boolean previous_called;


        /**
         * Create a new ListIterator on a specified vector {@code v}.
         *
         * @param   v - Vector used from the iterator.
         */
        public ListIterator(Vector v) {
            
            super(v);
            previous_called = false;
        }

        /**
         * Create a new iterator on a specified vector {@code v} starting from a specified position {@code i}
         *
         * @param   v - Vector used from the iterator.
         * @param   index - Starting index.
         */
        public ListIterator(Vector v, int index) {
            
            super(v, index);
            previous_called = false;
        }


        /**
         * Inserts the specified element into the list. 
         * The element is inserted immediately before the {@code next} element that would be returned by next, if any, and after the next element that would be returned by {@code previous}, if any. 
         * (If the list contains no elements, the new element becomes the sole element on the list.) 
         * The new element is inserted before the implicit cursor: a subsequent call to {@code next} would be unaffected, and a subsequent call to {@code previous} would return the new element. 
         * (This call increases by one the value that would be returned by a call to {@code nextIndex} or {@code previousIndex}.) 
         * 
         * @param   o - the element to insert. 
         */
        public void add(Object o) {

            it_vec.insertElementAt(o, index);
            index++;
            next_called = previous_called = false;
        }

        /**
         * Returns {@code true} if this list iterator has more elements when traversing the list in the forward direction. 
         * (In other words, returns {@code true} if {@code next} would return an element rather than throwing an exception.) 
         * 
         * @return  the next element in the list. 
         * @throws  NoSuchElementException - if the iteration has no next element.
         */
        public boolean hasNext() {
            
            return super.hasNext();
        }
    
        /**
         * Returns {@code true} if this list iterator has more elements when traversing the list in the reverse direction. 
         * (In other words, returns {@code true} if {@code previous} would return an element rather than throwing an exception.) 
         * 
         * @return  {@code true} if the list iterator has more elements when traversing the list in the reverse direction.
         */
        public boolean hasPrevious() {

            if (index > 0)
                return true;
            return false;
        }
    
        /**
         * Returns the next element in the list. This method may be called repeatedly to iterate through the list, or intermixed with calls to previous to go back and forth. 
         * (Note that alternating calls to next and previous will return the same element repeatedly.) 
         * 
         * @return  the next element in the list. 
         * @throws  NoSuchElementException - if the iteration has no next element.
         */
        public Object next() {

            previous_called = false;
            return super.next();
        
        }
    
        /**
         * Returns the index of the element that would be returned by a subsequent call to {@code next}. 
         * (Returns list size if the list iterator is at the end of the list.) 
         * 
         * @return  the index of the element that would be returned by a subsequent call to {@code next}, or list size if list iterator is at end of list.
         */
        public int nextIndex() {

            return index;
        }
    
        /**
         * Returns the previous element in the list. This method may be called repeatedly to iterate through the list backwards, or intermixed with calls to {@code next} to go back and forth. 
         * (Note that alternating calls to {@code next} and {@code previous} will return the same element repeatedly.) 
         * 
         * @return  the previous element in the list. 
         * @throws  NoSuchElementException - if the iteration has no previous element.
         */
        public Object previous() {

            if (!hasPrevious())
                throw new NoSuchElementException("MyAdapter.ListAdapter.ListIterator.previous: there are no previous elements");

            next_called = false;
            previous_called = true;
            index--;
            return vec.elementAt(index);
                
        }
    
        /**
         * Returns the index of the element that would be returned by a subsequent call to {@code previous}. 
         * (Returns -1 if the list iterator is at the beginning of the list.) 
         * 
         * @return  the index of the element that would be returned by a subsequent call to {@code previous}, or -1 if list iterator is at beginning of list.
         */
        public int previousIndex() {

            return nextIndex() - 1;
        }
    
        /**
         * Removes from the list the last element that was returned by {@code next} or {@code previous}. 
         * This call can only be made once per call to next or previous. 
         * It can be made only if ListIterator.add has not been called after the last call to next or previous. 
         * 
         * @throws  IllegalStateException neither {@code next} nor {@code previous} have been called, or {@code remove} or {@code add} have been called after the last call to {@code previous} or {@code previous}.
         */
        public void remove() {

            if (!next_called && !previous_called)
                throw new IllegalStateException("MyAdapter.ListAdapter.ListIterator.remove: can remove only after a next() or previous() call");
            
            else if (previous_called) {
                vec.removeElementAt(index);
                previous_called = next_called = false;
                return;
            }
            else super.remove();
            previous_called = next_called = false;
        }
    
        /**
         * Replaces the last element returned by {@code nect} or {@code previous} with the specified element. 
         * This call can be made only if neither {@code ListIterator.remove} nor {@code ListIterator.add} have been called after the last call to {@code next} or {@code previous}. 
         * 
         * @param   o - the element with which to replace the last element returned by next or previous. 
         * @throws  IllegalStateException if neither next nor previous have been called, or {@code remove} or {@code add} have been called after the last call to {@code next} or {@code previous}.
         * @throws  IllegalArgumentException if some aspect of the specified element prevents it from being added to this list.
         */
        public void set(Object o) {

            if (o == null)
                throw new NullPointerException("MyAdapter.ListAdapter.ListIterator.set: o cannot be null");

            if (vec.isEmpty()) {
                add(o);
                return;
            }
            if (!next_called && !previous_called)
                throw new IllegalStateException("MyAdapter.ListAdapter.ListIterator.set: can set only after a next() or a previous() call");

            else if (next_called)
                vec.setElementAt(o, index - 1);

            else if (previous_called)
                vec.setElementAt(o, index);
        }
        
    }



    /*   INNER CLASS SUBLIST   */

    private class SubList extends ListAdapter {

        /**
         * Start value for the SubList (a SubList is a part of a ListAdapter).
         */
        private int from;

        /**
         * End value for the SubList (a SubList is a part of a ListAdapter).
         */
        private int to;

        /**
         * SubList constructor
         * 
         * @param   fromIndex - Start value for the SubList.
         * @param   toIndex - End value for the SubList.
         */
        SubList(int fromIndex, int toIndex) {
            
            from = fromIndex;
            to = toIndex;

        }

        /**
         * Appends the specified element to the end of this list.
         *
         * Lists that support this operation may place limitations on what elements may be added to this list. 
         * In particular, the lists will refuse to add null elements
         *
         * @param   o - element to be appended to this list.
         * @return  {@code true} (as per the general contract of the {@link HCollection#add} method).
         * @throws  NullPointerException - if the specified element is null.
         */
        @Override
        public boolean add(Object o) {
            
            ListAdapter.this.add(to, o);
            to++;
            return true;
        }


        /**
         * Appends all of the elements in the specified collection to the end of this list, 
         * in the order that they are returned by the specified collection's iterator. 
         * The behavior of this operation is unspecified if the specified collection is modified while the operation is in progress.
         * (Note that this will occur if the specified collection is this list, and it's nonempty.)
         *
         * @param   c - collection whose elements are to be added to this list. 
         * @return  {@code true} if this list changed as a result of the call.
         * @throws  NullPointerException - if the specified collection is {@code null} or contains one or more null objects.
         */
        @Override
        public boolean addAll(HCollection c) {

            if (c == null)
                throw new NullPointerException("MyAdapter.ListAdapter.SubList.addAll: c cannot be null");
            HIterator check = c.iterator();
                while (check.hasNext())
                    if (check.next() == null)
                        throw new NullPointerException("MyAdapter.ListAdapter.SubList.addAll: c cannot contain null elements");

            boolean change = ListAdapter.this.addAll(to, c);
            to += c.size();
            return change;
        }
    

        /**
         * Returns the element at the specified position in this list. 
         *
         * @param   index - index of element to return.
         * @return  the element at the specified position in this list.
         * @throws  IndexOutOfBoundsException - if the index is out of range {@code (index < 0 || index >= size())}.
         */
        @Override
        public Object get(int index) {

            if (index < 0 || index >= size())
                throw new IndexOutOfBoundsException("MyAdapter.ListAdapter.SubList.get: index not valid");

            return ListAdapter.this.get(from + index);
        }


        /**
         * Removes all of the elements from this list. 
         * This list will be empty after this call returns (unless it throws an exception). 
         */
        @Override
        public void clear() {

            while (to > from) {
                ListAdapter.this.remove(from);
                to--;
            }
        }
    

        /**
         * Returns {@code true} if this list contains the specified element. 
         * More formally, returns {@code true} if and only if this list contains at least one element {@code e} such
         * {@code (o==null ? e==null : o.equals(e))}.
         *
         * @param   o - element whose presence in this list is to be tested.
         * @return  {@code true} if this list contains the specified element.
         * @throws  NullPointerException - if the specified element is {@code null}.
         */
        @Override
        public boolean contains(Object o) {

            if (o == null)
                throw new NullPointerException("MyAdapter.ListAdapter.SubList.contains: o cannot be null");

            for (int i = from; i < to; i++)
                if (ListAdapter.this.get(i).equals(o))
                    return true;
            return false;

        }
    

        /**
         * Returns true if this list contains all of the elements of the specified collection.
         *
         * @param   c - collection to be checked for containment in this list.
         * @return  {@code true} if this list contains all of the elements of the specified collection. 
         * @throws  NullPointerException - if the specified collection contains one or more null elements.
         */    
        @Override
        public boolean containsAll(HCollection c) {

            if (c == null)
                throw new NullPointerException("MyAdapter.ListAdapter.SubList.containsAll: c cannot be null");
            HIterator check = c.iterator();
                while (check.hasNext())
                    if (check.next() == null)
                        throw new NullPointerException("MyAdapter.ListAdapter.SubList.containsAll: c cannot comntain null elements");

            if (!(ListAdapter.class.isInstance(c)))
                throw new ClassCastException("MyAdapter.ListAdapter.SubList.containsAll: c is nota a instance of ListAdapter.class");

            ListAdapter c_list = (ListAdapter) c;
            HIterator it = c_list.iterator();

            while (it.hasNext()) {
                if(!contains(it.next()))
                    return false;
            }
            return true;
        }
    

        
        /**
         * Compares the specified object with this list for equality. Returns {@code true} if and only if the specified object is also
         * a ListAdapter, both ListAdapter have the same size, and all corresponding pairs of elements in the two lists are <i>equal</i>.
         * (Two elements {@code e1} and {@code e2} are <i>equal</i> if {@code (e1==null ? e2==null : e1.equals(e2)).)} 
         * In other words, two lists are defined to be <i>equal</i> if they contain the same elements in the same order.
         *
         * @param   o - the object to be compared for equality with this list.
         * @return  {@code true} if the specified object is equal to this list.
         */
        @Override
        public boolean equals(Object o) {
            
            if (o == null || !(HCollection.class.isInstance(o)))
                return false;
            
            HCollection o_collection = (HCollection) o;

            if (o_collection.size() != size())
                return false;

            HIterator it = o_collection.iterator();

            for (int i = 0; i < size(); i++)
                if (!ListAdapter.this.get(from + i).equals(it.next()))
                    return false;
            return true;
        }
    
        /**
         * Returns the hash code value for this list. The hash code of a list is defined to be the result of the following calculation:
         *  {@code hashCode = 1;
         *   HIterator i = list.iterator();
         *   while (i.hasNext()) {
         *       Object obj = i.next();
         *       hashCode = 31*hashCode + (obj==null ? 0 : obj.hashCode());
         *   }
         *  }
         * This ensures that {@code list1.equals(list2)} implies that {@code list1.hashCode()==list2.hashCode()} for any two lists, 
         * {@code list1} and {@code list2}, as required by the general contract {@code of Object.hashCode}.
         *
         * @return  the hash code value for this list.
         */
        @Override
        public int hashCode() {

            int hashCode = 1;
            HIterator it = ListAdapter.this.iterator();

            for (int i = 0; i < from; i++) {
                if (it.hasNext())
                    it.next();
                else return hashCode;
            }

            for (int i = from; i < to; i++) {
                if (it.hasNext()) {
                    Object obj = it.next();
                    hashCode = 31 * hashCode + (obj == null ? 0 : obj.hashCode());
                }
                else return hashCode;
            }
            return hashCode;
        }
    
        
        /**
         * Returns {@code true} if this list contains no elements.
         *
         * @return  {@code true} if this list contains no elements.
         */
        @Override
        public boolean isEmpty() {

            if (from >= to) 
                return true;
            return false;
        }
    


        /**
         * Returns an iterator over the elements in this list in proper sequence.
         *
         * @return  an iterator over the elements in this list in proper sequence.
         */
        @Override
        public SubListIterator iterator() {

            return new SubListIterator(ListAdapter.this.vec, from, to);
        }

        /**
         * Removes from this list all the elements that are contained in the specified collection.
         *
         * @param   o - collection that defines which elements will be removed from this list.
         * @return  {@code true} if this list changed as a result of the call.
         * @throws  NullPointerException - if the specified collection is null or contains null elements.
         */
        @Override
        public boolean remove(Object o) {

            if (o == null)
                throw new NullPointerException("MyAdapter.ListAdapter.SubList.remove: o cannot be null");

            int i = indexOf(o);
            int i_original_list = i + from;
            if (i >= 0) {
                ListAdapter.this.remove(i_original_list);
                to--;
            }
            
            return i >= 0;
        }
    

        /**
         * Removes from this list all the elements that are contained in the specified collection.
         *
         * @param   c - collection that defines which elements will be removed from this list.
         * @return  {@code true} if this list changed as a result of the call.
         * @throws  NullPointerException - if the specified collection is null or contains null elements.
         */
        @Override
        public boolean removeAll(HCollection c) {
                       
            if (c == null)
                throw new NullPointerException("MyAdapter.ListAdapter.SubList.removeAll: c cannot be null");
            HIterator check = c.iterator();
                while (check.hasNext())
                    if (check.next() == null)
                        throw new NullPointerException("MyAdapter.ListAdapter.SubList.removeAll: c cannot contain null elements");

            HIterator it = c.iterator();
            int removed = 0;

            while (it.hasNext()) {
                if (remove(it.next()))
                    removed++;
            }
            
            return (removed != 0);
        }
    
        /**
         * Retains only the elements in this list that are contained in the specified collection. 
         * In other words, removes from this list all the elements that are not contained in the specified collection.
         *
         * @param   c - collection that defines which elements this set will retain.
         * @return  {@code true} if this list changed as a result of the call.
         * @throws  NullPointerException - if the specified collection is null or contains null elements.
         */
        @Override
        public boolean retainAll(HCollection c) {

            if (c == null)
                throw new NullPointerException("MyAdapter.ListAdapter.SubList.retainAll: c cannot be null");
            HIterator check = c.iterator();
                while (check.hasNext())
                    if (check.next() == null)
                        throw new NullPointerException("MyAdapter.ListAdapter.SubList.retainAll: c cannot contain null elements");
            if (!(ListAdapter.class.isInstance(c)))
                throw new ClassCastException("MyAdapter.ListAdapter.SubList.retainAll: c is not an instance of ListAdapter.class");

            HIterator it = SubList.this.iterator();
            int removed = 0;
            while(it.hasNext())
                if(!c.contains(it.next())) {
                    it.remove();
                    removed++;
                }
            return (removed != 0);
        }
    

        /**
         * Returns the number of elements in this list.
         *
         * @return the number of elements in this list.
         */
        @Override
        public int size() {

            return SubList.this.to - SubList.this.from;
        }
    

        /**
         * Returns an array containing all of the elements in this list in proper sequence.
         *
         * @return an array containing all of the elements in this list in proper sequence.
         */
        @Override
        public Object[] toArray() {

            Object[] arry = new Object[size()];
            for (int i = 0; i < (to - from); i++)
                arry[i] = ListAdapter.this.get(i + from);
            return arry;
        }
    

        /**
         * Returns an array containing all of the elements in this list in proper sequence; the runtime type of the returned array is that of the specified array.
         * Obeys the general contract of the {@link HCollection#toArray(Object[])} method. 
         * 
         * @param   a - the array into which the elements of this list are to be stored, if it is big enough; otherwise, a new {@code Object[]} array is created for this purpose.
         * @return  an array containing the elements of this list.
         * @throws  ArrayStoreException - if the runtime type of the specified array is not a supertype of the runtime type of every element in this list.
         * @throws  NullPointerException - if the specified array is {@code null}.
         */
        @Override
        public Object[] toArray(Object[] a) {

            if (a == null)
                throw new NullPointerException("MyAdapter.ListAdapter.SubList.toArray: a cannot be null");

            Object[] arry = toArray();

            if (a.length < size())
                return arry;

            for (int i = 0; i < size(); i++) {
                a[i] = arry[i];
            }
            
            return a;
        }


        /**
         * Returns the index in this list of the first occurrence of the specified element, or -1 if this list does not contain this element.
         * More formally, returns the lowest index i such that {@code (o==null ? get(i)==null : o.equals(get(i)))}, or -1 if there is no such index.
         *
         * @param   o - element to search for.
         * @return  the index in this list of the first occurrence of the specified element, or -1 if this list does not contain this element.
         * @throws  NullPointerException - if the specified element is null.
         */
        @Override
        public int indexOf(Object o) {

            if (o == null)
                throw new NullPointerException("MyAdapter.ListAdapter.SubList.indexOf: o cannot be null");

            for (int i = 0; i < size(); i++)
                if (get(i).equals(o))
                    return i;
            return -1;
        }

        /**
         * Returns the index in this list of the last occurrence of the specified element, or -1 if this list does not contain this element. 
         * More formally, returns the highest index i such that {@code (o==null ? get(i)==null : o.equals(get(i)))}, or -1 if there is no such index.
         *
         * @param   o - element to search for.
         * @return  the index in this list of the last occurrence of the specified element, or -1 if this list does not contain this element.
         * @throws  NullPointerException - if the specified element is null.
         */
        @Override
        public int lastIndexOf(Object o) {

            if (o == null)
                throw new NullPointerException("MyAdapter.ListAdapter.SubList.lastIndexOf: o cannot be null");


            for (int i = to - 1; i >= from; i--)
                if (super.get(i).equals(o))
                    return i - from;
            return -1;
        }





        private class SubListIterator extends Iterator {

            

            /**
             * Create a new SubListIterator on a specified vector {@code v}.
             *
             * @param   v - Vector used from the iterator.
             * @param   from_ - low endpoint (inclusive) of the subList.
             * @param   to_ - high endpoint (exclusive) of the subList.
             */
            public SubListIterator(Vector v, int from_, int to_) {
            
                super(v, from_);   
            }
            

            /**
             * Returns {@code true} if the iteration has more elements. 
             * (In other words, returns {@code true} if {@code next} would return an element rather than throwing an exception.) 
             */
            @Override
            public boolean hasNext() {
            
                if (index < it_vec.size() && index < SubList.this.to)
                    return true;
                return false;
            }
            

            /**
             * Returns the next element in the iteration. 
             * 
             * @return  the next element in the iteration. 
             * @throws  NoSuchElementException - iteration has no more elements.
             */
            @Override
            public Object next() {
            
                if (!hasNext())
                    throw new NoSuchElementException("MyAdapter.ListAdapter.SubList.SubIterator.next: there are no next elements");
            
                return super.next();
            }
                

            /**
             * Removes from the underlying collection the last element returned by the iterator. 
             * This method can be called only once per call to {@code next}. 
             * The behavior of an iterator is unspecified if the underlying collection is modified while the 
             * iteration is in progress in any way other than by calling this method. 
             */
            @Override
            public void remove() {
            
                super.remove();
                SubList.this.to--;
                
            }
        }
            
    }

}