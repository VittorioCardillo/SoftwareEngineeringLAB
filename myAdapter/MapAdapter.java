package myAdapter;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.NoSuchElementException;

public class MapAdapter implements HMap {

    /**
     * Store the MapAdapter information.
     */
    private Hashtable tab;

    /**
     * Create a new MapAdapter, initialized at zero.
     */
    public MapAdapter() {

        this(new Hashtable());
    }

    /**
     * Create a new MapAdapter from an Hashtable.
     *
     * @param   t - Hashtable used for the initialization.
     */
    public MapAdapter(Hashtable t) {
        
        tab = t;
    }

    /**
     * Removes all mappings from this map. 
     */
    public void clear() {

        tab.clear();
    }

     /**
     * Returns {@code true} if this map contains a mapping for the specified key. 
     * More formally, returns {@code true} if and only if this map contains at a mapping for a key {@code k} such that {@code (key==null ? k==null : key.equals(k))}. 
     * (There can be at most one such mapping.)  
     * 
     * @param   key - key whose presence in this map is to be tested. 
     * @return  {@code true} if this map contains a mapping for the specified key. 
     * @throws  NullPointerException - if the key is {@code null}.
     */   
    public boolean containsKey(Object key) {

        if (key == null)
            throw new NullPointerException("myAdapter.MapAdapter.containsKey: key cannot be null");

        return tab.containsKey(key);
    }

    /**
     * Returns {@code true} if this map maps one or more keys to the specified value.
     * More formally, returns {@code true} if and only if this map contains at least one mapping to a value {@code v} such that {@code (value==null ? v==null : value.equals(v))}. 
     * This operation will probably require time linear in the map size for most implementations of the {@code Map} interface. 
     * 
     * @param   value - value whose presence in this map is to be tested. 
     * @return  {@code true} if this map maps one or more keys to the specified value. 
     * @throws  NullPointerException - if the value is {@code null}.
     */   
    public boolean containsValue(Object value) {

        if (value == null)
            throw new NullPointerException("myAdapter.MapAdapter.containsValue: value cannot be null");

            return tab.contains(value);
    }

    /**
     * Returns a set view of the mappings contained in this map. 
     * Each element in the returned set is a Map.Entry. 
     * The set is backed by the map, so changes to the map are reflected in the set, and vice-versa. 
     * If the map is modified while an iteration over the set is in progress, the results of the iteration are undefined. 
     * The set supports element removal, which removes the corresponding mapping from the map, via the {@code Iterator.remove, Set.remove, removeAll, retainAll and clear operations}. 
     * It does not support the {@code add} or {@code addAll} operations. 
     * 
     * @return a set view of the mappings contained in this map.
     */
    public HSet entrySet() {
        
        SetEntry s = new SetEntry(tab);

        return s;
    }

    /**
     * Compares the specified object with this map for equality. 
     * Returns {@code true} if the given object is also a map and the two Maps represent the same mappings. 
     * More formally, two maps {@code t1} and {@code t2} represent the same mappings {@code if t1.entrySet().equals(t2.entrySet())}. 
     * This ensures that the equals method works properly across different implementations of the {@code Map} interface. 
     * 
     * @param   o - object to be compared for equality with this map. 
     * @return  {@code true} if the specified object is equal to this map.
     */
    public boolean equals(Object o) {

        if (tab == null && o == null)
            return true;
        if (tab == null || o == null || !(MapAdapter.class.isInstance(o)))
            return false;
        if (size() != ((MapAdapter)o).size())
            return false;

        HSet s1 = entrySet();
        HSet s2 = ((MapAdapter)o).entrySet();

        return s1.equals(s2);
    }

    /**
     * Returns the value to which this map maps the specified key. 
     * Returns {@code null} if the map contains no mapping for this key. 
     * A return value of {@code null} does not necessarily indicate that the map contains no mapping for the key; 
     * it's also possible that the map explicitly maps the key to {@code null}. 
     * The {@code containsKey} operation may be used to distinguish these two cases.
     * 
     * More formally, if this map contains a mapping from a key {@code k} to a value {@code v} such that {@code (key==null ? k==null : key.equals(k))}, 
     * then this method returns {@code v}; otherwise it returns {@code null}. (There can be at most one such mapping.) 
     * 
     * @param   key - key whose associated value is to be returned. 
     * @return  the value to which this map maps the specified key, or {@code null} if the map contains no mapping for this key. 
     * @throws  NullPointerException - key is null.
     */
    public Object get(Object key) {
        
        if (key == null)
            throw new NullPointerException("myAdapter.MapAdapter.get: key cannot be null");

        return tab.get(key);
    }

    /**
     * Returns the hash code value for this map. 
     * The hash code of a map is defined to be the sum of the hashCodes of each entry in the map's entrySet view. 
     * This ensures that {@code t1.equals(t2)} implies that {@code t1.hashCode()==t2.hashCode()} for any two maps {@code t1} and {@code t2}, 
     * as required by the general contract of Object.hashCode. 
     * 
     * @return  the hash code value for this map.
     */
    public int hashCode() {

        HSet s = entrySet();
        Object[] obj = s.toArray();
        int hash = 0;

        for (int i = 0; i < obj.length; i++) 
            hash += obj[i].hashCode();

        return hash;
    }

    /**
     * Returns {@code true} if this map contains no key-value mappings. 
     * 
     * @return {@code true} if this map contains no key-value mappings.
     */
    public boolean isEmpty() {

        return tab.isEmpty();
    }

    /**
     * Returns a set view of the keys contained in this map. 
     * The set is backed by the map, so changes to the map are reflected in the set, and vice-versa. 
     * If the map is modified while an iteration over the set is in progress, the results of the iteration are undefined. 
     * The set supports element removal, which removes the corresponding mapping from the map, via the {@code Iterator.remove, Set.remove, removeAll retainAll, and clear operations}. 
     * It does not support the add or {@code addAll} operations.
     * 
     * @return a set view of the keys contained in this map.
     */
    public HSet keySet() {

        SetKey s = new SetKey();

        return s;
    }

    /**
     * Associates the specified value with the specified key in this map. 
     * If the map previously contained a mapping for this key, the old value is replaced by the specified value. 
     * (A map {@code m} is said to contain a mapping for a key {@code k} if and only if {@code m.containsKey(k)} would return {@code true}.)
     * 
     * @param   key - key with which the specified value is to be associated.
     * @param   value - value to be associated with the specified key. 
     * @return  previous value associated with specified key, or null if there was no mapping for key. 
     *          A null return can also indicate that the map previously associated null with the specified key, if the implementation supports null values. 
     * @throws  NullPointerException - the specified key or value is null.
     */
    public Object put(Object key, Object value) {

        if (key == null || value == null)
            throw new NullPointerException("myAdapter.MapAdapter.put: key or value cannot be null");

        return tab.put(key, value);
    }

    /**
     * Copies all of the mappings from the specified map to this map. 
     * The effect of this call is equivalent to that of calling {@code MapAdapter.put(k, v)} on this map once for each mapping from key {@code k} to value {@code v} in the specified map. 
     * The behavior of this operation is unspecified if the specified map is modified while the operation is in progress. 
     * 
     * @param   t - Mappings to be stored in this map. 
     * @throws  NullPointerException - the specified map is null.
     */
    public void putAll(HMap t) {

        if (t == null)
            throw new NullPointerException("myAdapter.MapAdapter.putAll: t cannot be null");

        HSet s = t.keySet();
        Object[] obj = s.toArray();

        for (int i = 0; i < obj.length; i++) {
            put(obj[i], t.get(obj[i]));
        }
    }

    /**
     * Removes the mapping for this key from this map if it is present. 
     * More formally, if this map contains a mapping from key {@code k} to value {@code v} such that {@code (key==null ? k==null : key.equals(k))}, that mapping is removed. 
     * (The map can contain at most one such mapping.)
     * 
     * Returns the value to which the map previously associated the key, or {@code null} if the map contained no mapping for this key. 
     * (A {@code null} return can also indicate that the map previously associated {@code null} with the specified key if the implementation supports {@code null} values.) 
     * The map will not contain a mapping for the specified key once the call returns. 
     * 
     * @param   key - key whose mapping is to be removed from the map. 
     * @return  previous value associated with specified key, or {@code null} if there was no mapping for key. 
     * @throws  NullPointerException -if the key is {@code null}.
     */
    public Object remove(Object key) {

        if (key == null)
            throw new NullPointerException("myAdapter.MapAdapter.remove: key cannot be null");

        return tab.remove(key);
    }

    /**
     * Returns the number of key-value mappings in this map. 
     * If the map contains more than {@code Integer.MAX_VALUE} elements, returns {@code Integer.MAX_VALUE}. 
     * 
     * @return  the number of key-value mappings in this map.
     */
    public int size() {

        return tab.size();
    }

    /**
     * Returns a collection view of the values contained in this map. 
     * The collection is backed by the map, so changes to the map are reflected in the collection, and vice-versa. 
     * If the map is modified while an iteration over the collection is in progress, the results of the iteration are undefined. 
     * The collection supports element removal, which removes the corresponding mapping from the map, via the {@code Iterator.remove, Collection.remove, removeAll, retainAll and clear} operations. 
     * It does not support the add or {@code addAll} operations. 
     * 
     * @return  a collection view of the values contained in this map.
     */
    public HCollection values() {

        CollectionValue s = new CollectionValue();

        return s;
    }














    private class SetEntry implements HSet{


        private Hashtable t;


        public SetEntry(Hashtable tab_) {

            t = tab_;
        }


        /**
         * Adds the specified element to this set if it is not already present. More formally, adds the specified element, {@code o}, 
         * to this set if this set contains no element {@code e} such that {@code (o==null ? e==null : o.equals(e))}. 
         * If this set already contains the specified element, the call leaves this set unchanged and returns {@code false}. In combination with the restriction on constructors, this ensures that sets never contain duplicate elements.
         * 
         * The stipulation above does not imply that sets must accept all elements; sets may refuse to add any particular element, including {@code null}, 
         * and throwing an exception, as described in the specification for {@code Collection.add}. 
         * Individual set implementations should clearly document any restrictions on the the elements that they may contain. 
         * 
         * @param   o - element to be added to this set. 
         * @return  {@code true} if this set did not already contain the specified element. 
         * @throws  UnsupportedOperationException - if the add method is not supported by this set. 
         */
        public boolean add(Object o) {

            throw new UnsupportedOperationException("myAdapter.MapAdapter.SetEntry.add: add cannot be called");
        }


        /**
         * Adds all of the elements in the specified collection to this set if they're not already present. 
         * If the specified collection is also a set, the {@code addAll} operation effectively modifies this set so that its value is the union of the two sets. 
         * The behavior of this operation is unspecified if the specified collection is modified while the operation is in progress. 
         * 
         * @param   c - collection whose elements are to be added to this set. 
         * @return  {@code true} if this set changed as a result of the call. 
         * @throws  UnsupportedOperationException - if the add method is not supported by this set. 
         */
        public boolean addAll(HCollection c) {

            throw new UnsupportedOperationException("myAdapter.MapAdapter.SetEntry.addAll: addAll cannot be called");      
        }


        /**
         * Removes all of the elements from this set. 
         * This set will be empty after this call returns (unless it throws an exception). 
         */
        public void clear() {

            t.clear();
        }


        /**
         * Returns {@code true} if this set contains the specified element. 
         * More formally, returns {@code true} if and only if this set contains an element e such that {@code (o==null ? e==null : o.equals(e))}. 
         * 
         * @param   o - element whose presence in this set is to be tested. 
         * @return  {@code true} if this set contains the specified element. 
         * @throws  ClassCastException - if the type of the specified element is incompatible with this setEntry. 
         * @throws  NullPointerException - if the specified element is {@code null}.
         */
        public boolean contains(Object o) {

            if (o == null) 
                throw new NullPointerException("myAdapter.MapAdapter.SetEntry.contains: o cannot be called");
            if (!Entry.class.isInstance(o))
                throw new ClassCastException("myAdapter.MapAdapter.SetEntry.contains: o is not an instance of Entry.class");
     
            Entry e = (Entry)o;
  
            if (t.containsKey(e.getKey()) && (e.getValue().equals(t.get(e.getKey()))))
                return true;
            return false;
        }
    
        /**
         * Returns {@code true} if this set contains all of the elements of the specified collection. 
         * If the specified collection is also a set, this method returns {@code true} if it is a subset of this set. 
         * 
         * @param   c - collection to be checked for containment in this set. 
         * @return  {@code true} if this set contains all of the elements of the specified collection. 
         * @throws  ClassCastException - if the type of the specified element is incompatible with this setEntry. 
         * @throws  NullPointerException - if the specified collection contains one or more null elements. 
         * @throws  NullPointerException - if the specified collection is null.
         */
        public boolean containsAll(HCollection c) {

            if (c == null)
                throw new NullPointerException("myAdapter.MapAdapter.SetEntry.containsAll: c cannot be null");
            HIterator check = c.iterator();
                while (check.hasNext())
                    if (check.next() == null)
                        throw new NullPointerException("myAdapter.MapAdapter.SetEntry.containsAll: c cannot comntain null elements");

            HIterator it = c.iterator();
            while (it.hasNext())
                if (!contains(it.next()))
                    return false;
            return true;
            
        }


        /**
         * Compares the specified object with this set for equality. Returns {@code true} if the specified object is also a set, 
         * the two sets have the same size, and every member of the specified set is contained in this set 
         * (or equivalently, every member of this set is contained in the specified set). 
         * This definition ensures that the equals method works properly across different implementations of the set interface.
         * 
         * @param   o - Object to be compared for equality with this set. 
         * @return  {@code true} if the specified Object is equal to this set. 
         */
        public boolean equals(Object o) {

            if (o == null || !(SetEntry.class.isInstance(o)))
                return false;

            SetEntry o_set = (SetEntry) o;

            if (o_set.size() != size())
                return false;

            HIterator it = o_set.iterator();
            HIterator it2 = iterator();

            for (int i = 0; i < size(); i++) {
                Entry e1 = (Entry)it.next();
                Entry e2 = (Entry)it2.next();
                if (!e1.equals(e2))
                    return false;
            }
            return true;
        }

 
        /**
         * Returns the hash code value for this set.
         * The hash code of a set is defined to be the sum of the hash codes of the elements in the set, where the hashcode of a {@code null} 
         * element is defined to be zero. This ensures that {@code s1.equals(s2)} implies that {@code s1.hashCode()==s2.hashCode()} 
         * for any two sets {@code s1} and {@code s2}, as required by the general contract of the {@code Object.hashCode} method. 
         * 
         * @return  the hash code value for this set.
         */
        public int hashCode() {

            int hash = 0;
            HIterator it = iterator();

            for (int i = 0; i < size(); i++) 
                hash += it.next().hashCode();
            
            return hash;
        }

        
        /**
         * Returns {@code true} if this set contains no elements. 
         * 
         * @return  {@code true}; if this set contains no elements.
         */
        public boolean isEmpty() {

            return t.isEmpty();
        }
    

        /**
         * Returns an iterator over the elements in this set. 
         * The elements are returned in no particular order (unless this set is an instance of some class that provides a guarantee). 
         * 
         * @return  an iterator over the elements in this set.
         */
        public HIterator iterator() {

            return new SetIterator();
        }


        /**
         * Removes the specified element from this set if it is present. 
         * More formally, removes an element e such that (o==null ? e==null : o.equals(e)), if the set contains such an element. 
         * Returns true if the set contained the specified element (or equivalently, if the set changed as a result of the call). 
         * (The set will not contain the specified element once the call returns.) 
         * 
         * @param   o - object to be removed from this set, if present. 
         * @return  true if the set contained the specified element. 
         * @throws  NullPointerException - if the specified element is null. 
         */
        @Override
        public boolean remove(Object o) {

            if (o == null) 
                throw new NullPointerException("myAdapter.MapAdapter.SetEntry.remove: o cannot be null");
            if (!Entry.class.isInstance(o))
                throw new ClassCastException("myAdapter.MapAdapter.SetEntry.remove: o is not instance off Entry.class");

            Entry e = (Entry)o;
            o = e.getKey();
            
            if (MapAdapter.this.remove(o) != null)
                return true;
            
            return false;
        }



        /**
         * Removes from this set all of its elements that are contained in the specified collection. 
         * If the specified collection is also a set, this operation effectively modifies this set so that its value is the asymmetric 
         * set difference of the two sets. 
         * 
         * @param   c - collection that defines which elements will be removed from this set. 
         * @return  {@code true} if this set changed as a result of the call. 
         * @throws  NullPointerException -  if this set contains a {@code null} element. 
         * @throws  NullPointerException - if the specified collection is {@code null}.
         */
        public boolean removeAll(HCollection c) {

            if (c == null)
                throw new NullPointerException("myAdapter.MapAdapter.SetEntry.removeAll: c cannot be null");
            HIterator check = c.iterator();
            while (check.hasNext())
                if (check.next() == null)
                    throw new NullPointerException("myAdapter.MapAdapter.SetEntry.removeAll: c cannot contains null elements");

            HIterator it = c.iterator();
            int removed = 0;
            while (it.hasNext()) {
                if (remove(it.next()))
                    removed++;
            }

            return (removed != 0);

        }


        /**
         * Retains only the elements in this set that are contained in the specified collection. 
         * In other words, removes from this set all of its elements that are not contained in the specified collection. 
         * If the specified collection is also a set, 
         * this operation effectively modifies this set so that its value is the intersection of the two sets. 
         * 
         * @param   c - collection that defines which elements this set will retain. 
         * @return  {@code true} if this collection changed as a result of the call. 
         * @throws  ClassCastException - if the types of one or more elements in this set are incompatible with the specified collection. 
         * @throws  NullPointerException - if this set contains a null element and the specified collection does not support null elements (optional). 
         * @throws  NullPointerException - if the specified collection is null.
         */
        public boolean retainAll(HCollection c) {

            if (c == null)
                throw new NullPointerException("myAdapter.MapAdapter.SetEntry.retainAll: c cannot be null");
            HIterator check = c.iterator();
            while (check.hasNext())
                if (check.next() == null)
                    throw new NullPointerException("myAdapter.MapAdapter.SetEntry.retainAll: c cannot contain null elements");
            
            int removed = 0;
            HIterator it = iterator();

            MapAdapter ma = new MapAdapter();

            HIterator c_it = c.iterator();
            Entry e_col;
            while (c_it.hasNext()) {
                e_col = (Entry)c_it.next();
                ma.put(e_col.getKey(), e_col.getValue());
            }

            Entry tab_col;
            while(it.hasNext()) {
                tab_col = (Entry)it.next();
                if(!(ma.containsKey(tab_col.getKey()) && ma.containsValue(tab_col.getValue()))) {
                    it.remove();
                    removed++;
                }
            }
            
            return (removed != 0);

        }

        /**
         * Returns the number of elements in this set (its cardinality). 
         * If this set contains more than Integer.MAX_VALUE elements, returns Integer.MAX_VALUE. 
         * 
         * @return  the number of elements in this set (its cardinality).
         */
        public int size() {

            return t.size();
        }

        /**
         * Returns an array containing all of the elements in this set. 
         * Obeys the general contract of the Collection.toArray method. 
         * 
         * @return  an array containing all of the elements in this set.
         */
        public Object[] toArray() {

            Object[] arry = new Object[size()];
            HIterator it = iterator();

            for (int i = 0; i < size(); i++)
                arry[i] = it.next();
    
            return arry;
        }

        /**
         * Returns an array containing all of the elements in this set; the runtime type of the returned array is that of the specified array. 
         * Obeys the general contract of the {@code Collection.toArray(Object[])} method. 
         * 
         * @param   a - the array into which the elements of this set are to be stored, if it is big enough; otherwise, a new array of the same runtime type is allocated for this purpose. 
         * @return  an array containing the elements of this set. 
         * @throws  NullPointerException - if the specified array is {@code null}.
         */
        public Object[] toArray(Object[] a) {

            if (a == null)
                throw new NullPointerException("myAdapter.MapAdapter.SetEntry.toArray: a cannot be null");

            if (a.length < size())
                return toArray();

            HIterator it = iterator();
            for (int i = 0; i < size(); i++) {
                a[i] = it.next();
            }
        
            return a;
        }




/*   INNER CLASS ITERATOR   */


        /**
         * Simple iterator over a ListAdapter object. It can traverse the vector associated
         * with the ListAdapter forwards, perform remove operations.
         */
        private class SetIterator implements HIterator {


            /**
             * Current iterator position.
            */
            protected int index;

            /**
             * Used on the {@code remove} method, if {@code true} it is possible to call the {@code remove} method.
             */
            protected boolean next_called;
        

            /**
             * Create a new iterator on a set.
             */
            public SetIterator() {

                index = 0;
                next_called = false;
            }


        
            /**
             * Returns {@code true} if the iteration has more elements. 
             * (In other words, returns {@code true} if {@code next} would return an element rather than throwing an exception.) 
             */
            public boolean hasNext() {

                if (index < size())
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
                    throw new NoSuchElementException("myAdapter.MapAdapter.SetEntry.SetIterator.next: there are not next elements");

                Enumeration e = tab.keys();
                
                for (int i = 0; i < index; i++) {
                        e.nextElement();
                }

                Object k = e.nextElement();
                index++;
                next_called = true;
                Entry res = new Entry(k, tab.get(k));
                return res;

            }
    
            /**
             * Removes from the underlying collection the last element returned by the iterator. 
             * This method can be called only once per call to {@code next}. 
             * The behavior of an iterator is unspecified if the underlying collection is modified while the 
             * iteration is in progress in any way other than by calling this method. 
             */
            public void remove() {

                if (!next_called)
                    throw new IllegalStateException("myAdapter.MapAdapter.SetEntry.SetIterator.remove: can call remove only after a next() call");

                Enumeration e = tab.keys();
                
                for (int i = 0; i < index - 1; i++) {
                        e.nextElement();
                }
    
                Object k = e.nextElement();

                index--;
                next_called = false;
                tab.remove(k);
            }
        }

    }







    private class SetKey extends SetEntry {
        
        SetKey() {
            super(tab);
        }
        


        /**
         * Returns {@code true} if this set contains the specified element. 
         * More formally, returns {@code true} if and only if this set contains an element e such that {@code (o==null ? e==null : o.equals(e))}. 
         * 
         * @param   o - element whose presence in this set is to be tested. 
         * @return  {@code true} if this set contains the specified element. 
         * @throws  ClassCastException - if the type of the specified element is incompatible with this setEntry. 
         * @throws  NullPointerException - if the specified element is {@code null}.
         */
        @Override
        public boolean contains(Object o) {

            if (o == null) 
                throw new NullPointerException("myAdapter.MapAdapter.SetKey.contains: o cannot be null");
            if (!Object.class.isInstance(o))
                throw new ClassCastException("myAdapter.MapAdapter.SetKey.contains: o cannot contain null elements");
     
            return tab.containsKey(o);
        }


        /**
         * Compares the specified object with this set for equality. Returns {@code true} if the specified object is also a set, 
         * the two sets have the same size, and every member of the specified set is contained in this set 
         * (or equivalently, every member of this set is contained in the specified set). 
         * This definition ensures that the equals method works properly across different implementations of the set interface.
         * 
         * @param   o - Object to be compared for equality with this set. 
         * @return  {@code true} if the specified Object is equal to this set. 
         */
        public boolean equals(Object o) {

            if (o == null || !(SetKey.class.isInstance(o)))
                return false;

            SetKey o_set = (SetKey) o;

            if (o_set.size() != size())
                return false;

            HIterator it = o_set.iterator();
            HIterator it2 = iterator();

            for (int i = 0; i < size(); i++) 
                if (!it.next().equals(it2.next()))
                    return false;
            
            return true;
        }




        /**
         * Removes the specified element from this set if it is present. 
         * More formally, removes an element e such that (o==null ? e==null : o.equals(e)), if the set contains such an element. 
         * Returns true if the set contained the specified element (or equivalently, if the set changed as a result of the call). 
         * (The set will not contain the specified element once the call returns.) 
         * 
         * @param   o - object to be removed from this set, if present. 
         * @return  true if the set contained the specified element. 
         * @throws  NullPointerException - if the specified element is null. 
         */
        @Override
        public boolean remove(Object o) {

            if (o == null) 
            throw new NullPointerException("myAdapter.MapAdapter.SetKey.remove: o cannot be null");
            if (!Object.class.isInstance(o))
                throw new ClassCastException();
            
            if (MapAdapter.this.remove(o) != null)
                return true;
            
            return false;
        }


        /**
         * Retains only the elements in this set that are contained in the specified collection. 
         * In other words, removes from this set all of its elements that are not contained in the specified collection. 
         * If the specified collection is also a set, 
         * this operation effectively modifies this set so that its value is the intersection of the two sets. 
         * 
         * @param   c - collection that defines which elements this set will retain. 
         * @return  {@code null} if this collection changed as a result of the call. 
         * @throws  ClassCastException - if the types of one or more elements in this set are incompatible with the specified collection. 
         * @throws  NullPointerException - if this set contains a null element and the specified collection does not support null elements (optional). 
         * @throws  NullPointerException - if the specified collection is null.
         */
        @Override
        public boolean retainAll(HCollection c) {

            if (c == null)
                throw new NullPointerException("myAdapter.MapAdapter.SetKey.retainAll: c cannot be null");
            HIterator check = c.iterator();
            while (check.hasNext())
                if (check.next() == null)
                    throw new NullPointerException("myAdapter.MapAdapter.SetKey.retainAll: c cannot contain null elements");
            
            int removed = 0;
            HIterator it = iterator();

            Object tab_col;
            while(it.hasNext()) {
                tab_col = it.next();
                if(!(c.contains(tab_col))) {
                    it.remove();
                    removed++;
                }
            }
            
            return (removed != 0);

        }



        /**
         * Returns an iterator over the elements in this set. 
         * The elements are returned in no particular order (unless this set is an instance of some class that provides a guarantee). 
         * 
         * @return  an iterator over the elements in this set.
         */
        public HIterator iterator() {

            return new SetIterator();
        }



        private class SetIterator extends SetEntry.SetIterator  {


            /**
             * Returns the next element in the iteration. 
             * 
             * @return  the next element in the iteration. 
             * @throws  NoSuchElementException - iteration has no more elements.
             */
            public Object next() {

                if (!hasNext())
                    throw new NoSuchElementException("myAdapter.MapAdapter.SetKey.SetIterator.next: there are not next elements");

                Enumeration e = tab.keys();
                
                for (int i = 0; i < index; i++) {
                        e.nextElement();
                }

                Object k = e.nextElement();
                index++;
                next_called = true;

                return k;

            }

        }

    }









    private class CollectionValue extends SetEntry implements HCollection {
        
        CollectionValue() {
            super(tab);
        }
        

        /**
         * Returns {@code true} if this set contains the specified element. 
         * More formally, returns {@code true} if and only if this set contains an element e such that {@code (o==null ? e==null : o.equals(e))}. 
         * 
         * @param   o - element whose presence in this set is to be tested. 
         * @return  {@code true} if this set contains the specified element. 
         * @throws  ClassCastException - if the type of the specified element is incompatible with this setEntry. 
         * @throws  NullPointerException - if the specified element is {@code null}.
         */
        @Override
        public boolean contains(Object o) {

            if (o == null) 
                throw new NullPointerException("myAdapter.MapAdapter.CollectionValue.contains: o cannot be null");
            if (!Object.class.isInstance(o))
                throw new ClassCastException("myAdapter.MapAdapter.CollectionValue.contains: o cannot contain null elements");
     
            return tab.containsValue(o);
        }


        /**
         * Compares the specified object with this set for equality. Returns {@code true} if the specified object is also a set, 
         * the two sets have the same size, and every member of the specified set is contained in this set 
         * (or equivalently, every member of this set is contained in the specified set). 
         * This definition ensures that the equals method works properly across different implementations of the set interface.
         * 
         * @param   o - Object to be compared for equality with this set. 
         * @return  {@code true} if the specified Object is equal to this set. 
         */
        public boolean equals(Object o) {

            if (o == null || !(CollectionValue.class.isInstance(o)))
                return false;

            CollectionValue o_set = (CollectionValue) o;

            if (o_set.size() != size())
                return false;

            HIterator it = o_set.iterator();
            HIterator it2 = iterator();

            for (int i = 0; i < size(); i++) 
                if (!it.next().equals(it2.next()))
                    return false;
            
            return true;
        }


        /**
         * Removes the specified element from this set if it is present. 
         * More formally, removes an element e such that (o==null ? e==null : o.equals(e)), if the set contains such an element. 
         * Returns true if the set contained the specified element (or equivalently, if the set changed as a result of the call). 
         * (The set will not contain the specified element once the call returns.) 
         * 
         * @param   o - object to be removed from this set, if present. 
         * @return  true if the set contained the specified element. 
         * @throws  NullPointerException - if the specified element is null. 
         */
        @Override
        public boolean remove(Object o) {

            if (o == null) 
                throw new NullPointerException("myAdapter.MapAdapter.CollectionValue.remove: o cannot be null");
            if (!Object.class.isInstance(o))
                throw new ClassCastException("myAdapter.MapAdapter.CollectionValue.remove: o is not an instance of Object.class");
            
            HIterator it = iterator();
            boolean contain = false;
            while (it.hasNext())
                if (it.next() == o) {
                    it.remove();
                    contain = true;
                    break;
                }
            
            return contain;
        }


        /**
         * Retains only the elements in this set that are contained in the specified collection. 
         * In other words, removes from this set all of its elements that are not contained in the specified collection. 
         * If the specified collection is also a set, 
         * this operation effectively modifies this set so that its value is the intersection of the two sets. 
         * 
         * @param   c - collection that defines which elements this set will retain. 
         * @return  {@code null} if this collection changed as a result of the call. 
         * @throws  ClassCastException - if the types of one or more elements in this set are incompatible with the specified collection. 
         * @throws  NullPointerException - if this set contains a null element and the specified collection does not support null elements (optional). 
         * @throws  NullPointerException - if the specified collection is null.
         */
        @Override
        public boolean retainAll(HCollection c) {

            if (c == null)
                throw new NullPointerException("myAdapter.MapAdapter.CollectionValue.retainALl: c cannot be null");
            HIterator check = c.iterator();
            while (check.hasNext())
                if (check.next() == null)
                    throw new NullPointerException("myAdapter.MapAdapter.CollectionValue.retainAll: c cannot contain null elements");
            
            int removed = 0;
            HIterator it = iterator();

            Object tab_col;
            while(it.hasNext()) {
                tab_col = it.next();
                if(!(c.contains(tab_col)))
                    it.remove();
                    removed++;
            }
            
            return (removed != 0);

        }




        /**
         * Returns an iterator over the elements in this set. 
         * The elements are returned in no particular order (unless this set is an instance of some class that provides a guarantee). 
         * 
         * @return  an iterator over the elements in this set.
         */
        public HIterator iterator() {

            return new SetIterator();
        }


        private class SetIterator extends SetEntry.SetIterator  {


            /**
             * Returns the next element in the iteration. 
             * 
             * @return  the next element in the iteration. 
             * @throws  NoSuchElementException - iteration has no more elements.
             */
            public Object next() {

                if (!hasNext())
                    throw new NoSuchElementException("myAdapter.MapAdapter.CollectionValue.SetIterator: there are not next elements");

                Enumeration e = tab.keys();
                
                for (int i = 0; i < index; i++) {
                        e.nextElement();
                }

                Object k = e.nextElement();
                Object ret = tab.get(k);
                index++;
                next_called = true;

                return ret;

            }

        }

    }
    
}



