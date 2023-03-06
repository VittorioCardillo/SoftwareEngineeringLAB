package myTest;

import myAdapter.*;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;
import java.util.Vector;



public class MapAdapterTester {
    

    /**
     * Oggetto utilizzato per eseguire ogni test.
     */
    private MapAdapter map;


    /**
     * Elementi che verranno inseriti nella mappa.
     */
    private final int start_test_size = 10;


    /**
     * Creazione della mappa, verrà eseguita ad ogni nuovo test.
     */
    @Before
    public void inizialization() {

        map = new MapAdapter();
    }

    /**
     * controllo che l’oggetto MapAdapter sia stato creato 
     * con una dimensione pari a 0, 
     * riempimento di esso tramite il metodo fillMap(), 
     * controllo del corretto riempimento dell’oggetto. 
     */
    @Test
    public void initializationTest() {

        System.out.println("-- initializationTest --\n");

        assertEquals(0, map.size());
        fillMap();
        assertNotEquals(0, map.size());

        printMap();
    }



    // - - - TEST MAPADAPTER - - - //
    

    /**
     * riempimento mappa, controllo dell’esistenza o meno di sei 
     * particolari valori all’interno della mappa, tre dei quali 
     * sono effettivamente presente, i restanti no. 
     */
    @Test
    public void containsKeyTest(){

        System.out.println("-- containsKeyTest(Object o) --\n");

        fillMap();
        assertTrue(map.containsKey(5));
        assertTrue(map.containsKey(0));
        assertTrue(map.containsKey(9));
        assertFalse(map.containsKey(10));
        assertFalse(map.containsKey(-1));
        assertFalse(map.containsKey(100));

        printMap();
    }


    /**
     * viene cercato all’interno della mappa il valore nullo { null }, 
     * tale inserimento deve causare il lancio dell’eccezione NullPointerException. 
     */
    @Test
    public void containsKeyTestExc(){

        System.out.println("-- containsKeyTestExc(Object o) --\n");

        assertThrows(NullPointerException.class,
                    () -> {map.containsKey(null);});

        printMap();
    }


    /**
     * riempimento mappa, controllo dell’esistenza o meno di sei 
     * particolari valori all’interno della mappa, tre dei quali 
     * sono effettivamente presente, i restanti no. 
     */
    @Test
    public void containsValueTest(){

        System.out.println("-- containsValueTest(Object o) --\n");

        fillMap();
        assertTrue(map.containsValue(15));
        assertTrue(map.containsValue(10));
        assertTrue(map.containsValue(19));
        assertFalse(map.containsValue(20));
        assertFalse(map.containsValue(-1));
        assertFalse(map.containsValue(100));

        printMap();
    }


    /**
     * viene cercato all’interno della mappa il valore nullo { null }, 
     * tale inserimento deve causare il lancio dell’eccezione NullPointerException. 
     */
    @Test
    public void containsValueTestExc(){

        System.out.println("-- containsValueTestExc(Object o) --\n");

        assertThrows(NullPointerException.class,
                    () -> {map.containsValue(null);});

        printMap();
    }


    /**
     * riempimento mappa, utilizzo entrySet() e successiva creazione di 
     * un array dal set tramite toArray, viene ora verificato che gli 
     * elementi dell’array siano Entry e che ci siano tutti. 
     * Il backing viene verificato eliminando un elemento dalla mappa e 
     * verificando che la dimensione del set e della mappa sia diminuita. 
     */
    @Test
    public void entrySetTest() {

        System.out.println("-- entrySet() --\n");

        fillMap();
        HSet s = map.entrySet();
        Object[] o = s.toArray();
        assertTrue(Entry.class.isInstance(o[0]));
        assertEquals(10, o.length);
        Entry[] e = oToEntry(o);
        assertEquals(10, e.length);
        assertEquals(9, e[0].getKey());
        assertEquals(0, e[9].getKey());
        assertEquals(19, e[0].getValue());
        assertEquals(10, e[9].getValue());


        map.remove(2);
        assertEquals(9, map.size());
        assertEquals(9, s.size());

        o = s.toArray();
        assertTrue(Entry.class.isInstance(o[0]));
        assertEquals(9, o.length);
        e = oToEntry(o);
        assertEquals(9, e.length);
        assertEquals(9, e[0].getKey());
        assertEquals(0, e[8].getKey());
        assertEquals(19, e[0].getValue());
        assertEquals(10, e[8].getValue());

        printMap();

    }


    /**
     * riempimento mappa, creazione nuova mappa identica alla 
     * precedente, verifica che le due mappe siano uguali, 
     * aggiunta ad una delle due mappe di un elemento, verifica che 
     * le due mappe non siano più uguali, verifica che una mappa non 
     * sia uguale a null, e che non sia uguale ad una stringa. 
     */
    @Test
    public void equalsTest() {

        System.out.println("-- equalsTest(Object o) --\n");

        fillMap();
        MapAdapter map2 = new MapAdapter();
        for (int i = 0; i < start_test_size; i++)
            map2.put(i, i + 10);

        assertTrue(map.equals(map2));

        map.put(3, "Hello");

        assertFalse(map.equals(map2));
        assertFalse(map.equals(null));
        assertFalse(map.equals("String"));

        map2.put(3, "Hola");
        assertFalse(map.equals(map2));

        map.remove(3);
        map2.remove(3);

        map.put("key", "value");
        map2.put("key2", "value");

        assertFalse(map.equals(map2));

        printMap();
    }


    /**
     * riempimento mappa, utilizzo del metodo get() e controllo 
     * dalla corretta restituzione dell’elemento corrispondente, 
     * controllo che il metodo get() su un indice non presente nella 
     * mappa restituisca il valore nullo {@code null }. 
     */
    @Test
    public void getTest() {

        System.out.println("-- getTest(Object key) --\n");

        fillMap();
        assertEquals(12, map.get(2));
        assertEquals(null, map.get(-1));
        assertEquals(null, map.get(100));

        printMap();
    }


    /**
     * riempimento mappa, creazione nuova mappa identica alla precedente, 
     * verifica che gli hashCode delle due mappe siano uguali. 
     */
    @Test
    public void hashCodeTest() {

        System.out.println("-- hashCodeTest() --\n");
        
        fillMap();
        MapAdapter map2 = new MapAdapter();

        for (int i = 0; i < start_test_size; i++)
                map2.put(i, i + 10);
        
        assertEquals(map.hashCode(), map2.hashCode());

        printMap();
    }


    /**
     * riempimento mappa, controllo tramite isEmpty() che la mappa non 
     * sia vuota, rimozione degli elementi tramite il metodo clear(), 
     * controllo tramite isEmpty() che la mappa sia vuota. 
     */
    @Test
    public void isEmptyTest() {

        System.out.println("-- isEmptyTest() --\n");

        fillMap();
        assertFalse(map.isEmpty());
        map.clear();
        assertTrue(map.isEmpty());

        printMap();
    }


    /**
     * riempimento mappa, utilizzo keySet() e successiva creazione 
     * di un array dal set tramite toArray, viene ora verificato che 
     * gli elementi dell’array siano Object e che ci siano tutti. 
     * Il backing viene verificato eliminando un elemento dalla mappa 
     * e verificando che la dimensione del set e della mappa sia diminuita. 
     */
    @Test
    public void keySet() {
        
        System.out.println("-- keySet() --\n");

        fillMap();
        HSet s = map.keySet();
        Object[] o = s.toArray();
        assertTrue(Object.class.isInstance(o[0]));
        assertEquals(10, o.length);
        assertEquals(9, o[0]);
        assertEquals(0, o[9]);
        assertEquals(4, o[5]);
        assertEquals(6, o[3]);


        map.remove(2);
        assertEquals(9, map.size());
        assertEquals(9, s.size());

        o = s.toArray();
        assertTrue(Object.class.isInstance(o[0]));
        assertEquals(9, o.length);
        assertEquals(9, o[0]);
        assertEquals(0, o[8]);
        assertEquals(5, o[4]);
        assertEquals(7, o[2]);

        printMap();

    }




    /**
     * riempimento mappa, aggiunta di un elemento a mappa con una 
     * chiave già presente, controllo che la dimensione della mappa 
     * rimanga invariata e dell’inserimento nella posizione corretta 
     * dell’oggetto precedentemente aggiunto. Aggiunta di due altri 
     * elemento, questa volta con una chiave non precedentemente 
     * contenuta nella mappa, controllo corretto aumento della dimensione 
     * della mappa e dell’inserimento degli oggetti precedentemente aggiunti. 
     */
    @Test
    public void putTest() {

        System.out.println("-- putTest(Object key, Object value) --\n");

        fillMap();
        map.put(0, "one");
        assertEquals(start_test_size, map.size());
        assertEquals("one", map.get(0));
        map.put(50, "two");
        assertEquals(start_test_size + 1, map.size());
        assertEquals("two", map.get(50));
        map.put(map.size(), "three");
        assertEquals(start_test_size + 2, map.size());

        printMap();
    }


    /**
     * Riempimento mappa, aggiunta di tre elementi, il primo con chiave 
     * nulla, il secondo con valore nullo e il terzo con sia chiave che 
     * valore nullo. 
     */
    @Test 
    public void putTestExc() {

        System.out.println("-- putTestExc(Object key, Object value) --\n");

        fillMap();
        assertThrows(NullPointerException.class,
                    () -> {map.put(null, "Hello");});

        assertThrows(NullPointerException.class,
                    () -> {map.put(map.size() + 1, null);});

        assertThrows(NullPointerException.class,
                    () -> {map.put(null, null);});

        printMap();
    }


    /**
     * creazione di una HMap di due elementi, aggiunta della HMap 
     * alla mappa vuota, controllo corretto aumento della dimensione 
     * della mappa e della presenza degli elementi inseriti. 
     * Svuotamento della mappa. Riempimento mappa, aggiunta di una 
     * HCollection, controllo corretto aumento della dimensione 
     * della mappa. 
     */
    @Test
    public void putAllTest() {

        System.out.println("-- putAllTest(HMap t) --\n");

        MapAdapter m = new MapAdapter();
        m.put(17, "one");
        m.put(2, "two");
        HMap hm = (HMap)m;

        map.putAll(hm);
        assertEquals(2, map.size());
        assertEquals("two", map.get(2));
        map.clear();
        fillMap();
        map.putAll(hm);
        assertEquals(start_test_size + 1, map.size());

        printMap();
    } 


    /**
     * creazione HMap nulla, controllo che l’aggiunta di tale HMap 
     * alla mappa restituisca NullPointerException. 
     */
    @Test
    public void putAllTestExc() {

        System.out.println("-- putAllTestExc(HMap t) --\n");

        HMap m1 = null;

        assertThrows(NullPointerException.class,
                    () -> {map.putAll(m1);});

        printMap();
    }


    /**
     * riempimento mappa, rimozione di un elemento tramite remove() 
     * e controllo corretta diminuzione della dimensione della mappa. 
     */
    @Test
    public void removeTest() {

        System.out.println("-- removeTest(Object key) --\n");

        fillMap();
        assertEquals(14, map.remove(4));
        assertEquals(9, map.size());

        printMap();
    }


    /**
     * rimozione di un elemento con una chiave nulla avendo la mappa 
     * vuota e controllo corretto lancio NullPointerException, 
     * riempimento mappa, rimozione di un elemento a chiave nulla e 
     * controllo corretto lancio NullPointerException. 
     */
    @Test
    public void removeTestExc() {

        System.out.println("-- removeTestExc(Object key) --\n");

        assertThrows(NullPointerException.class,
                    () -> {map.remove(null);});
        fillMap();
        assertThrows(NullPointerException.class,
                    () -> {map.remove(null);});
                    
    }


    /**
     * controllo se con la mappa vuota il metodo size() restituisce 0, 
     * riempimento mappa, controllo tramite size() che la dimensione 
     * della mappa corrisponda a 10, rimozione di un elemento tramite 
     * il metodo remove(), controllo tramite size() che la mappa abbia 
     * dimensione 9. 
     */
    @Test
    public void sizeTest() {

        System.out.println("-- sizeTest() --\n");

        assertEquals(0, map.size());
        fillMap();
        assertEquals(10, map.size());
        map.remove(0);
        assertEquals(9, map.size());

        printMap();
    }


    /**
     * riempimento mappa, utilizzo values() e successiva creazione di un 
     * array dal set tramite toArray, viene ora verificato che gli 
     * elementi dell’array siano Object e che ci siano tutti. 
     * Il backing viene verificato eliminando un elemento dalla mappa e 
     * verificando che la dimensione del set e della mappa sia diminuita 
     * e che gli elementi continuino ad esserci. 
     */
    @Test
    public void values() {
        
        System.out.println("-- values() --\n");

        fillMap();
        HCollection c = map.values();
        HIterator it = c.iterator();
        Object[] o = new Object[c.size()];

        for (int i = 0; i < c.size(); i++)
            o[i] = it.next();
            
        assertTrue(Object.class.isInstance(o[0]));
        assertEquals(10, o.length);
        assertEquals(19, o[0]);
        assertEquals(10, o[9]);
        assertEquals(14, o[5]);
        assertEquals(16, o[3]);

        it = c.iterator();
        it.next();
        it.next();
        it.remove();

        assertEquals(9, map.size());
        assertEquals(9, c.size());
        it = c.iterator();
        for (int i = 0; i < c.size(); i++)
            o[i] = it.next();

        assertTrue(Object.class.isInstance(o[0]));
        assertEquals(19, o[0]);
        assertEquals(10, o[8]);
        assertEquals(14, o[4]);
        assertEquals(16, o[2]);

        printMap();

    }



    // - - - TEST HSET KEY - - - //



    /**
     * riempimento mappa, creazione keySet, controllo che l’utilizzo 
     * di add restituisca UnsupportedOperationException.
     */
    @Test
    public void keyAddTestExt() {

    
        System.out.println("-- keyAddTestExc(Object element) --\n");
    
        fillMap();
        HSet hs = map.keySet();

        assertThrows(UnsupportedOperationException.class,
                    () -> {hs.add(0);});
    
        printMap();
        
    }


    /**
     * riempimento mappa, creazione keySet, creazione collection 
     * controllo che l’utilizzo di addAll restituisca UnsupportedOperationException.
     */
    @Test
    public void keyAddAllTestExt() {

    
        System.out.println("-- keyAddAllTestExc(HCollection c) --\n");
    
        fillMap();
        HSet hs = map.keySet();
        HCollection c = map.values();

        assertThrows(UnsupportedOperationException.class,
                    () -> {hs.addAll(c);});
    
        printMap();
        
    }


    /**
     * riempimento mappa, creazione keySet, rimozione degli elementi 
     * tramite il metodo clear(), controllo che la dimensione 
     * della mappa sia pari a zero.
     */
    @Test
    public void keyClearTest() {

    
        System.out.println("-- keyClearTest() --\n");
    
        fillMap();
        HSet hs = map.keySet();

        assertEquals(10, map.size());
        assertEquals(10, hs.size());
        hs.clear();
        assertEquals(0, hs.size());
        assertEquals(0, map.size());
    
        printMap();
    }


    /**
     *  riempimento mappa, creazione keySet, controllo dell’esistenza 
     * o meno di tre particolari valori all’interno del set 
     * (e quindi della mappa), due dei quali sono effettivamente presente, 
     * il restante no. 
     */
    @Test
    public void keyContainsTest(){

        System.out.println("-- keyContainsTest(Object o) --\n");

        fillMap();
        HSet hs = map.keySet();
        Object o1 = 1;
        Object o2 = -1;
        Object o3 = 7;

        assertTrue(hs.contains(o1));
        assertFalse(hs.contains(o2));
        assertTrue(hs.contains(o3));

        printMap();
    }


    /**
     * riempimento mappa, creazione keySet, viene cercato all’interno 
     * del set il valore nullo {@code null }, tale inserimento deve causare 
     * il lancio dell’eccezione NullPointerException. 
     */
    @Test
    public void keyContainsTestExc(){

        System.out.println("-- keyContainsTestExc(Object o) --\n");

        fillMap();
        HSet hs = map.keySet();
        Object o1 = null;

        assertThrows(NullPointerException.class,
                    () -> {hs.contains(o1);});

        printMap();
    }


    /**
     * riempimento mappa, creazione keySet, creazione di una 
     * collection di due elementi esistenti all’interno della mappa, 
     * ricerca della collection nel set, aggiunta di un elemento non 
     * presente nel set all’interno della collection, ricerca della 
     * collection nel set. 
     */
    @Test
    public void keyContainsAllTest(){

        System.out.println("-- keyContainsAllTest(HCollection c) --\n");

        fillMap();
        HSet hs = map.keySet();
        Vector<Object> vec = new Vector<Object>();
        vec.add(1);
        vec.add(4);
        ListAdapter lis = new ListAdapter(vec);
        HCollection col = (HCollection) lis;

        assertTrue(hs.containsAll(col));

        lis.add(20);
        col = (HCollection) lis;

        assertFalse(hs.containsAll(col));

        printMap();
    }


    /**
     * riempimento mappa, creazione nuova mappa identica alla precedente, 
     * verifica che i due keySet siano uguali, sostituzione ad uno dei 
     * due set di un elemento, verifica che le due mappe non siano più 
     * uguali, verifica che una mappa non sia uguale a null, e che non 
     * sia uguale ad una stringa. Sostituzione al secondo set lo stesso 
     * elemento sostituito precedentemente al primo e quindi la 
     * corrispondenza dei due set. 
     */
    @Test
    public void keyEqualsTest() {

        System.out.println("-- keyEqualsTest(Object o) --\n");

        fillMap();
        HSet hs = map.keySet();
        MapAdapter map2 = new MapAdapter();
        for (int i = 0; i < start_test_size; i++)
            map2.put(i, i + 10);
        HSet hs2 = map2.keySet();

        assertTrue(hs.equals(hs2));

        map.put(3, "Hello");
        assertEquals(10, hs.size());

        assertTrue(hs.equals(hs2));
        assertFalse(hs.equals(null));
        assertFalse(hs.equals("String"));

        map2.put(3, "Hola");

        assertTrue(hs.equals(hs2));

        map.remove(3);
        map2.remove(3);

        assertEquals(9, hs.size());

        map.put("key", "value");
        map2.put("key2", "value");

        assertEquals(10, hs.size());

        assertFalse(hs.equals(hs2));

        printMap();
    }


    /**
     * riempimento mappa, creazione nuova mappa identica alla precedente, 
     * creazione dei due keySet e verifica che gli hashCode dei set 
     * siano uguali. 
     */
    @Test
    public void keyHashCodeTest() {

        System.out.println("-- keyHashCodeTest() --\n");
        
        fillMap();
        MapAdapter map2 = new MapAdapter();
        HSet hs = map.keySet();
        HSet hs2 = map2.keySet();
        for (int i = 0; i < start_test_size; i++)
                map2.put(i, i + 10);

        assertEquals(hs.hashCode(), hs2.hashCode());

        printMap();
    }


    /**
     * riempimento mappa, creazione keySet controllo tramite isEmpty() 
     * che il keySet non sia vuoto, rimozione degli elementi tramite 
     * il metodo clear(), controllo tramite isEmpty() che il keySet 
     * sia vuoto. 
     */
    @Test
    public void keyIsEmptyTest() {

        System.out.println("-- keyIsEmptyTest() --\n");

        fillMap();
        HSet hs = map.keySet();
        assertFalse(hs.isEmpty());
        hs.clear();
        assertTrue(hs.isEmpty());

        printMap();
    }


    /**
     * riempimento mappa, creazione keySet, creazione di un iteratore, 
     * controllo del corretto funzionamento del metodo next(), sia 
     * che restituisca il valore corretto, sia che non restituisca un 
     * valore errato, controllo del corretto funzionamento del metodo 
     * hasNext(), utilizzato per spostarsi all’ultimo elemento e 
     * controllando che quello sia effettivamente l’ultimo elemento 
     * chiamando next() e controllando che lanci NullPointerException. 
     */
    @Test
    public void keyIteratorTest() {
        
        System.out.println("-- keyIteratorTest() + method + exception --\n");

        fillMap();
        HSet hs = map.keySet();

        HIterator it = hs.iterator();

        Object o = 9;
        assertTrue(o.equals(it.next()));
        assertNotEquals(5, it.next());
        while (it.hasNext()) 
            it.next();

        assertThrows(NoSuchElementException.class,
                    () -> {it.next();});

        printMap();
    }


    /**
     * riempimento mappa, creazione keySet, rimozione di un elemento 
     * tramite remove() e controllo corretta diminuzione della 
     * dimensione del set. 
     */
    @Test
    public void keyRemoveTest() {

        System.out.println("-- keyRemoveTest(Object key) --\n");

        fillMap();
        HSet hs = map.entrySet();
        assertTrue(hs.remove(new Entry(1, 11)));
        assertEquals(9, hs.size());

        printMap();
    }


    /**
     * creazione keySet, rimozione di un elemento con una chiave 
     * nulla avendo la mappa vuota e controllo corretto lancio 
     * NullPointerException, riempimento mappa, rimozione di un 
     * elemento a chiave nulla e controllo corretto lancio 
     * NullPointerException. 
     */
    @Test
    public void keyRemoveTestExc() {

        System.out.println("-- keyRemoveTestExc(Object key) --\n");

        HSet hs = map.keySet();
        assertThrows(NullPointerException.class,
                    () -> {hs.remove(null);});
        fillMap();
        assertThrows(NullPointerException.class,
                    () -> {hs.remove(null);});
                    
    }


    /**
     * creazione di una collection di due elementi entrambi presenti 
     * nella mappa, riempimento mappa, creazione keySet, rimozione 
     * della Collection, controllo corretta diminuzione della 
     * dimensione della mappa. Aggiunta di un elemento non presente 
     * nella mappa alla Collection, pulizia mappa e nuovo riempimento 
     * di essa, rimozione della Collection dalla mappa e controllo 
     * corretta diminuzione della dimensione della mappa. 
     */
    @Test
    public void keyRemoveAllTest() {

        System.out.println("-- keyRemoveAllTest(HCollection c) --\n");

        ListAdapter l = new ListAdapter();
        l.add(2);
        l.add(5);
        HCollection col = (HCollection) l;
        fillMap();
        HSet hs = map.keySet();

        hs.removeAll(col);
        assertEquals(8, hs.size());
        l.add(15);
        hs.clear();
        fillMap();
        col = (HCollection) l;
        hs.removeAll(col);
        assertEquals(8, hs.size());

        printMap();
    }


    /**
     * creazione Collection nulla, controllo che la rimozione di 
     * tale Collection dal set restituisca NullPointerException. 
     */
    @Test
    public void keyRemoveAllTestExc() {
        
        System.out.println("-- keyRemoveAllTestExc(HCollection c) --\n");

        fillMap();
        HSet hs = map.keySet();
        HCollection col1 = null;
        assertThrows(NullPointerException.class,
                    () -> {hs.removeAll(col1);});

        printMap();

    }


    /**
     * creazione di una Collection di due elementi entrambi presenti 
     * nella mappa, riempimento mappa, creazione keySet, utilizzo del 
     * metodo retainAll(), controllo corretta diminuzione della 
     * dimensione del set. Aggiunta di un elemento non presente nel 
     * set alla Collection, pulizia mappa e nuovo riempimento di essa, 
     * utilizzo del metodo retainAll() e controllo corretta diminuzione 
     * della dimensione del set. 
     */
    @Test
    public void keyRetainAllTest() {

        System.out.println("-- keyRetainAllTest(HCollection c) --\n");

        ListAdapter l = new ListAdapter();
        l.add(2);
        l.add(5);
        HCollection col = (HCollection) l;
        fillMap();
        HSet hs = map.keySet();

        assertTrue(hs.retainAll(col)); 
        assertEquals(2, hs.size());
        hs.clear();
        fillMap();
        l.add(15);
        col = (HCollection) l;
        hs.retainAll(col);
        assertEquals(2, hs.size());

        printMap();
    }


    /**
     * riempimento mappa, creazione keySet, creazione Collection nulla, 
     * controllo che l’utilizzo di retainAll() con tale Collection 
     * nel set restituisca NullPointerException. 
     */
    
    @Test
    public void keyRetainAllTestExc() {
        
        System.out.println("-- keyRetainAllTestExc(HCollection c) --\n");

        fillMap();
        HSet hs = map.keySet();
        HCollection col1 = null;
        assertThrows(NullPointerException.class,
                    () -> {hs.retainAll(col1);});

        printMap();

    }


    /**
     * creazione keySet, controllo se con il set vuoto il metodo size() 
     * restituisce 0, riempimento mappa, controllo tramite size() che 
     * la dimensione del set corrisponda a 10, rimozione di un elemento 
     * tramite il metodo remove(), controllo tramite size() che il set 
     * abbia dimensione 9. 
     */
    @Test
    public void keySizeTest() {

        System.out.println("-- keySizeTest() --\n");

        HSet hs = map.keySet();

        assertEquals(0, hs.size());
        fillMap();
        assertEquals(10, hs.size());
        hs.remove(0);
        assertEquals(9, hs.size());

        printMap();
    }


    /**
     * riempimento mappa, creazione keySet, creazione di un array 
     * tramite il metodo toArray(), controllo che tutti gli elementi 
     * dell’array coincidano in valore e posizione a tutti gli 
     * elementi del set originario. 
     */
    @Test 
    public void keyToArrayTest() {

        System.out.println("-- keyToArrayTest() --\n");

        fillMap();
        HSet hs = map.keySet();
        Object[] arry = hs.toArray();
        HIterator it = hs.iterator();

        for (int i = 0; i < arry.length; i++) {
            assertTrue(it.next().equals(arry[i]));
        }

        printMap();
    }


    /**
     * riempimento mappa, creazione keySet, creazione di un array a 
     * due elementi e utilizzo del metodo toArray() passando come 
     * parametro l’array creato, controllo che tutti gli elementi 
     * dell’array coincidano in valore e posizione a tutti gli 
     * elementi del set originario. Creazione di un array a venti 
     * elementi e utilizzo del metodo toArray() passando come parametro 
     * l’array creato, controllo che tutti gli elementi dell’array 
     * coincidano in valore e posizione a tutti gli elementi del set 
     * originario. 
     */
    @Test
    public void keyToArrayObjTest() {

        System.out.println("-- keyToArrayObjTest(Object[] a) --\n");

        fillMap();
        HSet hs = map.keySet();
        Object[] obj = {2, 3};
        Object[] res = hs.toArray(obj);
        HIterator it = hs.iterator();


        for (int i = 0; i < res.length; i++) {
            assertTrue(it.next().equals(res[i]));
        }

        Object[] obj2 = new Object[20];
        Object[] res2 = hs.toArray(obj2);
        it = hs.iterator();

        for (int i = 0; i < hs.size(); i++) {
            assertTrue(it.next().equals(res2[i]));
        }

        printMap();
    }


    /**
     * riempimento mappa, creazione di un keySet, creazione di un 
     * array nullo e passaggio di esso tramite il metodo toArray(), 
     * controllo corretto lancio NullPointerException. 
     */
    @Test
    public void keyToArrayObjTestExc() {

        System.out.println("-- keyToArrayObjTestExc(Object[] a) --\n");
    
        fillMap();
        HSet hs = map.keySet();
        Object[] obj = null;

        assertThrows(NullPointerException.class,
                    () -> {hs.toArray(obj);});

        printMap();
    }




    // - - - TEST COLLECTIONVALUE - - - //




    /**
     * riempimento mappa, creazione values, controllo dell’esistenza o 
     * meno di tre particolari valori all’interno della 
     * Collection(e quindi della mappa), due dei quali sono 
     * effettivamente presente, il restante no. 
     */
    @Test
    public void collectionContainsTest(){

        System.out.println("-- entryContainsTest(Object o) --\n");

        fillMap();
        HCollection hs = map.values();
        Object o1 = 11;
        Object o2 = 150;
        Object o3 = 19;

        assertTrue(hs.contains(o1));
        assertFalse(hs.contains(o2));
        assertTrue(hs.contains(o3));

        printMap();
    }


    /**
     * riempimento mappa, creazione values, viene cercato all’interno 
     * del set il valore nullo {@code null }, tale inserimento deve causare 
     * il lancio dell’eccezione NullPointerException. 
     */
    @Test
    public void collectionContainsTestExc(){

        System.out.println("-- collectionContainsTestExc(Object o) --\n");

        fillMap();
        HCollection hs = map.values();
        Object o1 = null;

        assertThrows(NullPointerException.class,
                    () -> {hs.contains(o1);});

        printMap();
    }


    /**
     * riempimento mappa, creazione nuova mappa identica alla precedente, 
     * verifica che i due values siano uguali, sostituzione ad una delle 
     * due Collection di un elemento, verifica che le due mappe non siano 
     * più uguali, verifica che una mappa non sia uguale a null, e che 
     * non sia uguale ad una stringa. Sostituzione alla seconda Collection 
     * lo stesso elemento sostituito precedentemente alla prima e quindi 
     * la corrispondenza delle due Collection. 
     */
    @Test
    public void collectionEqualsTest() {

        System.out.println("-- collectionEqualsTest(Object o) --\n");

        fillMap();
        HCollection hs = map.values();
        MapAdapter map2 = new MapAdapter();
        for (int i = 0; i < start_test_size; i++)
            map2.put(i, i + 10);
        HCollection hs2 = map2.values();

        assertTrue(hs.equals(hs2));

        map.put(3, "Hello");
        assertEquals(10, hs.size());

        assertFalse(hs.equals(hs2));
        assertFalse(hs.equals(null));
        assertFalse(hs.equals("String"));

        map2.put(3, "Hola");

        assertFalse(hs.equals(hs2));

        map.remove(3);
        map2.remove(3);

        assertEquals(9, hs.size());

        map.put("key", "value");
        map2.put("key2", "value");

        assertEquals(10, hs.size());

        assertFalse(hs.equals(hs2));

        printMap();
    }


    /**
     * riempimento mappa, creazione values, rimozione di un elemento 
     * tramite remove() e controllo corretta diminuzione della 
     * dimensione della Collection. 
     */
    @Test
    public void collectionRemoveTest() {

        System.out.println("-- collectionRemoveTest(Object key) --\n");

        fillMap();
        HCollection hs = map.values();
        assertTrue(hs.remove(11));
        assertEquals(9, hs.size());

        printMap();
    }


    /**
     * creazione values, rimozione di un elemento con un valore nullo 
     * avendo la mappa vuota e controllo corretto lancio 
     * NullPointerException, riempimento mappa, rimozione di un 
     * elemento a valore nullo e controllo corretto lancio 
     * NullPointerException. 
     */
    @Test
    public void collectionRemoveTestExc() {

        System.out.println("-- collectionRemoveTestExc(Object key) --\n");

        HCollection hs = map.values();
        assertThrows(NullPointerException.class,
                    () -> {hs.remove(null);});
        fillMap();
        assertThrows(NullPointerException.class,
                    () -> {hs.remove(null);});
                    
    }


    /**
     * creazione di una Collection di due elementi entrambi presenti 
     * nella mappa, riempimento mappa, creazione values, utilizzo del 
     * metodo retainAll(), controllo corretta diminuzione della 
     * dimensione della Collection. Aggiunta di un elemento non presente 
     * nella Colelction originale alla Collection, pulizia mappa e 
     * nuovo riempimento di essa, utilizzo del metodo retainAll() e 
     * controllo corretta diminuzione della dimensione della Collection. 
     */
    @Test
    public void collectionRetainAllTest() {

        System.out.println("-- collectionRetainAllTest(HCollection c) --\n");

        ListAdapter l = new ListAdapter();
        l.add(12);
        l.add(15);
        HCollection col = (HCollection) l;
        fillMap();
        HCollection hs = map.values();

        assertTrue(hs.retainAll(col)); 
        assertEquals(2, hs.size());
        hs.clear();
        fillMap();
        l.add(25);
        col = (HCollection) l;
        hs.retainAll(col);
        assertEquals(2, hs.size());

        printMap();
    }


    /**
     * riempimento mappa, creazione values, creazione Collection nulla, 
     * controllo che l’utilizzo di retainAll() con tale Collection nella 
     * Collelction originale restituisca NullPointerException. 
     */
    @Test
    public void collectionRetainAllTestExc() {
        
        System.out.println("-- collectionRetainAllTestExc(HCollection c) --\n");

        fillMap();
        HCollection hs = map.values();
        HCollection col1 = null;
        assertThrows(NullPointerException.class,
                    () -> {hs.retainAll(col1);});

        printMap();

    }


    /**
     * riempimento mappa, creazione values, creazione di un iteratore, 
     * controllo del corretto funzionamento del metodo next(), 
     * sia che restituisca il valore corretto, sia che non restituisca 
     * un valore errato, controllo del corretto funzionamento del 
     * metodo hasNext(), utilizzato per spostarsi all’ultimo elemento 
     * e controllando che quello sia effettivamente l’ultimo elemento 
     * chiamando next() e controllando che lanci NullPointerException. 
     */
    @Test
    public void collectionIteratorTest() {
        
        System.out.println("-- collectionIteratorTest() + method + exception --\n");

        fillMap();
        HCollection hs = map.values();

        HIterator it = hs.iterator();

        Object o = 19;
        assertTrue(o.equals(it.next()));
        assertTrue((it.next()).equals(18));
        while (it.hasNext()) 
            it.next();

        assertThrows(NoSuchElementException.class,
                    () -> {it.next();});

        printMap();
    }





    // - - - TEST HSET ENTRY - - - //




    /**
     * riempimento mappa, creazione entrySet, controllo che 
     * l’utilizzo di add() restituisca UnsupportedOperationException.
     */
    @Test
    public void entryAddTestExt() {

    
        System.out.println("-- entryAddTestExc(Object element) --\n");
    
        fillMap();
        HSet hs = map.entrySet();

        assertThrows(UnsupportedOperationException.class,
                    () -> {hs.add(0);});
    
        printMap();
        
    }


    /**
     * riempimento mappa, creazione entrySet, creazione Collection 
     * controllo che l’utilizzo di addAll restituisca 
     * UnsupportedOperationException.
     */
    @Test
    public void entryAddAllTestExt() {

    
        System.out.println("-- entryAddAllTestExc(HCollection c) --\n");
    
        fillMap();
        HSet hs = map.entrySet();
        HCollection c = map.values();

        assertThrows(UnsupportedOperationException.class,
                    () -> {hs.addAll(c);});
    
        printMap();
        
    }


    /**
     * riempimento mappa, creazione entrySet, rimozione degli elementi 
     * tramite il metodo clear(), controllo che la dimensione della 
     * mappa sia pari a zero.
     */
    @Test
    public void entryClearTest() {

    
        System.out.println("-- entryClearTest() --\n");
    
        fillMap();
        HSet hs = map.entrySet();

        assertEquals(10, map.size());
        assertEquals(10, hs.size());
        hs.clear();
        assertEquals(0, hs.size());
        assertEquals(0, map.size());
    
        printMap();
    }


    /**
     * riempimento mappa, creazione entrySet, controllo dell’esistenza 
     * o meno di tre particolari valori all’interno del set 
     * (e quindi della mappa), due dei quali sono effettivamente presente, 
     * il restante no. 
     */
    @Test
    public void entryContainsTest(){

        System.out.println("-- entryContainsTest(Object o) --\n");

        fillMap();
        HSet hs = map.entrySet();
        Entry e1 = new Entry(1, 11);
        Entry e2 = new Entry(-1, 11);
        Entry e3 = new Entry(1, 12);

        assertTrue(hs.contains(e1));
        assertFalse(hs.contains(e2));
        assertFalse(hs.contains(e3));

        printMap();
    }


    /**
     * riempimento mappa, creazione entrySet, viene cercato all’interno 
     * del set il valore nullo {@code null }, tale inserimento deve causare 
     * il lancio dell’eccezione NullPointerException. 
     */
    @Test
    public void entryContainsTestExc(){

        System.out.println("-- entryContainsTestExc(Object o) --\n");

        fillMap();
        HSet hs = map.entrySet();
        Entry e1 = new Entry(null, 11);
        Entry e2 = new Entry(1, null);

        assertThrows(NullPointerException.class,
                    () -> {hs.contains(e1);});
        assertThrows(NullPointerException.class,
                    () -> {hs.contains(e2);});

        printMap();
    }


    /**
     * riempimento mappa, creazione entrySet, creazione di una 
     * Collection di due elementi esistenti all’interno della mappa, 
     * ricerca della Collection nel set, aggiunta di un elemento non 
     * presente nel set all’interno della Collection, ricerca della 
     * Collection nel set. 
     */
    @Test
    public void entryContainsAllTest(){

        System.out.println("-- entryContainsAllTest(HCollection c) --\n");

        fillMap();
        HSet hs = map.entrySet();
        Vector<Object> vec = new Vector<Object>();
        vec.add(new Entry(1, 11));
        vec.add(new Entry(4, 14));
        ListAdapter lis = new ListAdapter(vec);
        HCollection col = (HCollection) lis;

        assertTrue(hs.containsAll(col));

        lis.add(new Entry(20, 14));
        col = (HCollection) lis;

        assertFalse(hs.containsAll(col));

        printMap();
    }


    /**
     * riempimento mappa, creazione nuova mappa identica alla precedente, 
     * verifica che i due entrySet siano uguali, sostituzione ad uno dei 
     * due set di un elemento, verifica che le due mappe non siano più 
     * uguali, verifica che una mappa non sia uguale a null, e che non 
     * sia uguale ad una stringa. Sostituzione al secondo set lo stesso 
     * elemento sostituito precedentemente al primo e quindi la 
     * corrispondenza dei due set. Nuovo inserimento di elementi, questa
     * volta con lo stesso valore e controllo della non uguaglianza.
     */
    @Test
    public void entryEqualsTest() {

        System.out.println("-- entryEqualsTest(Object o) --\n");

        fillMap();
        HSet hs = map.entrySet();
        MapAdapter map2 = new MapAdapter();
        for (int i = 0; i < start_test_size; i++)
            map2.put(i, i + 10);
        HSet hs2 = map2.entrySet();

        assertTrue(hs.equals(hs2));

        map.put(3, "Hello");
        assertEquals(10, hs.size());

        assertFalse(hs.equals(hs2));
        assertFalse(hs.equals(null));
        assertFalse(hs.equals("String"));

        map2.put(3, "Hola");

        assertFalse(hs.equals(hs2));

        map.remove(3);
        map2.remove(3);

        assertEquals(9, hs.size());

        map.put("key", "value");
        map2.put("key2", "value");

        assertEquals(10, hs.size());

        assertFalse(hs.equals(hs2));

        printMap();
    }


    /**
     * riempimento mappa, creazione nuova mappa identica alla 
     * precedente, creazione dei due entrySet e verifica che gli 
     * hashCode dei set siano uguali. 
     */
    @Test
    public void entryHashCodeTest() {

        System.out.println("-- entryHashCodeTest() --\n");
        
        fillMap();
        MapAdapter map2 = new MapAdapter();
        HSet hs = map.entrySet();
        HSet hs2 = map2.entrySet();
        for (int i = 0; i < start_test_size; i++)
                map2.put(i, i + 10);

        assertEquals(hs.hashCode(), hs2.hashCode());

        printMap();
    }


    /**
     * riempimento mappa, creazione entrySet controllo tramite 
     * isEmpty() che il entrySet non sia vuoto, rimozione degli 
     * elementi tramite il metodo clear(), controllo tramite 
     * isEmpty() che il entrySet sia vuoto. 
     */
    @Test
    public void entryIsEmptyTest() {

        System.out.println("-- entryIsEmptyTest() --\n");

        fillMap();
        HSet hs = map.entrySet();
        assertFalse(hs.isEmpty());
        hs.clear();
        assertTrue(hs.isEmpty());

        printMap();
    }


    /**
     * riempimento mappa, creazione entrySet, creazione di un iteratore, 
     * controllo del corretto funzionamento del metodo next(), sia che 
     * restituisca il valore corretto, sia che non restituisca un valore 
     * errato, controllo del corretto funzionamento del metodo hasNext(), 
     * utilizzato per spostarsi all’ultimo elemento e controllando che 
     * quello sia effettivamente l’ultimo elemento chiamando next() e 
     * controllando che lanci NullPointerException. 
     */
    @Test
    public void entryIteratorTest() {
        
        System.out.println("-- entryIteratorTest() + method + exception --\n");

        fillMap();
        HSet hs = map.entrySet();

        HIterator it = hs.iterator();

        Entry e = new Entry(9, 19);
        assertTrue(e.equals((Entry)it.next()));
        assertTrue((new Entry(8, 18)).equals((Entry) it.next()));
        while (it.hasNext()) 
            it.next();

        assertThrows(NoSuchElementException.class,
                    () -> {it.next();});

        printMap();
    }


    /**
     * riempimento mappa, creazione entrySet, rimozione di un elemento 
     * tramite remove() e controllo corretta diminuzione della 
     * dimensione del set. 
     */
    @Test
    public void entryRemoveTest() {

        System.out.println("-- entryRemoveTest(Object key) --\n");

        fillMap();
        HSet hs = map.entrySet();
        assertTrue(hs.remove(new Entry(1, 11)));
        assertEquals(9, hs.size());

        printMap();
    }


    /**
     * creazione entrySet, rimozione di un elemento con una chiave 
     * nulla avendo la mappa vuota e controllo corretto lancio 
     * NullPointerException, riempimento mappa, rimozione di un 
     * elemento a chiave nulla e controllo corretto lancio 
     * NullPointerException. 
     */
    @Test
    public void entryRemoveTestExc() {

        System.out.println("-- entryRemoveTestExc(Object key) --\n");

        HSet hs = map.entrySet();
        assertThrows(NullPointerException.class,
                    () -> {hs.remove(null);});
        fillMap();
        assertThrows(NullPointerException.class,
                    () -> {hs.remove(null);});
                    
    }


    /**
     * creazione di una Collection di due elementi entrambi presenti 
     * nella mappa, riempimento mappa, creazione entrySet, rimozione 
     * della Collection, controllo corretta diminuzione della 
     * dimensione della mappa. Aggiunta di un elemento non presente 
     * nella mappa alla Collection, pulizia mappa e nuovo riempimento 
     * di essa, rimozione della Collection dalla mappa e controllo 
     * corretta diminuzione della dimensione della mappa. 
     */
    @Test
    public void entryRemoveAllTest() {

        System.out.println("-- entryRemoveAllTest(HCollection c) --\n");

        ListAdapter l = new ListAdapter();
        l.add(new Entry(2, 12));
        l.add(new Entry(5, 15));
        HCollection col = (HCollection) l;
        fillMap();
        HSet hs = map.entrySet();

        hs.removeAll(col);
        assertEquals(8, hs.size());
        l.add(new Entry(15, 25));
        hs.clear();
        fillMap();
        col = (HCollection) l;
        hs.removeAll(col);
        assertEquals(8, hs.size());

        printMap();
    }


    /**
     * creazione Collection nulla, controllo che la rimozione di tale 
     * Collection dal set restituisca NullPointerException. 
     */
    @Test
    public void entryRemoveAllTestExc() {
        
        System.out.println("-- entryRemoveAllTestExc(HCollection c) --\n");

        fillMap();
        HSet hs = map.entrySet();
        HCollection col1 = null;
        assertThrows(NullPointerException.class,
                    () -> {hs.removeAll(col1);});

        printMap();

    }


    /**
     * creazione di una Collection di due elementi entrambi presenti 
     * nella mappa, riempimento mappa, creazione entrySet, utilizzo 
     * del metodo retainAll(), controllo corretta diminuzione della 
     * dimensione del set. Aggiunta di un elemento non presente nel 
     * set alla Collection, pulizia mappa e nuovo riempimento di essa, 
     * utilizzo del metodo retainAll() e controllo corretta diminuzione 
     * della dimensione del set. 
     */
    @Test
    public void entryRetainAllTest() {

        System.out.println("-- entryRetainAllTest(HCollection c) --\n");

        ListAdapter l = new ListAdapter();
        l.add(new Entry(2, 12));
        l.add(new Entry(5, 15));
        HCollection col = (HCollection) l;
        fillMap();
        HSet hs = map.entrySet();

        assertTrue(hs.retainAll(col)); 
        assertEquals(2, hs.size());
        hs.clear();
        fillMap();
        l.add(new Entry(15, 25));
        col = (HCollection) l;
        hs.retainAll(col);
        assertEquals(2, hs.size());

        printMap();
    }


    /**
     * riempimento mappa, creazione entrySet, creazione Collection nulla, 
     * controllo che l’utilizzo di retainAll() con tale Collection nel 
     * set restituisca NullPointerException. 
     */
    @Test
    public void entryRetainAllTestExc() {
        
        System.out.println("-- entryRetainAllTestExc(HCollection c) --\n");

        fillMap();
        HSet hs = map.entrySet();
        HCollection col1 = null;
        assertThrows(NullPointerException.class,
                    () -> {hs.retainAll(col1);});

        printMap();

    }


    /**
     * creazione entrySet, controllo se con il set vuoto il metodo size() 
     * restituisce 0, riempimento mappa, controllo tramite size() che la 
     * dimensione del set corrisponda a 10, rimozione di un elemento 
     * tramite il metodo remove(), controllo tramite size() che il set 
     * abbia dimensione 9. 
     */
    @Test
    public void entrySizeTest() {

        System.out.println("-- entrySizeTest() --\n");

        HSet hs = map.entrySet();

        assertEquals(0, hs.size());
        fillMap();
        assertEquals(10, hs.size());
        hs.remove(new Entry(0, 10));
        assertEquals(9, hs.size());

        printMap();
    }


    /**
     * riempimento mappa, creazione entrySet, creazione di un array 
     * tramite il metodo toArray(), controllo che tutti gli elementi 
     * dell’array coincidano in valore e posizione a tutti gli elementi 
     * del set originario. 
     */
    @Test 
    public void entryToArrayTest() {

        System.out.println("-- entryToArrayTest() --\n");

        fillMap();
        HSet hs = map.entrySet();
        Object[] arry = hs.toArray();
        HIterator it = hs.iterator();
        Entry e_it = (Entry)it.next();
        Entry e_ob = (Entry)arry[0];

        assertTrue(e_it.equals(e_ob));

        for (int i = 1; i < arry.length; i++) {
            assertTrue(((Entry)it.next()).equals((Entry)arry[i]));
        }

        printMap();
    }


    /**
     * riempimento mappa, creazione entrySet, creazione di un array a 
     * due elementi e utilizzo del metodo toArray() passando come 
     * parametro l’array creato, controllo che tutti gli elementi 
     * dell’array coincidano in valore e posizione a tutti gli 
     * elementi del set originario. Creazione di un array a venti
     * elementi e utilizzo del metodo toArray() passando come 
     * parametro l’array creato, controllo che tutti gli elementi 
     * dell’array coincidano in valore e posizione a tutti gli 
     * elementi del set originario. 
     */
    @Test
    public void entryToArrayObjTest() {

        System.out.println("-- entryToArrayObjTest(Object[] a) --\n");

        fillMap();
        HSet hs = map.entrySet();
        Object[] obj = {2, 3};
        Object[] res = hs.toArray(obj);
        HIterator it = hs.iterator();
        Entry e_it = (Entry)it.next();
        Entry e_ob = (Entry)res[0];

        assertTrue(e_it.equals(e_ob));


        for (int i = 1; i < res.length; i++) {
            assertTrue(((Entry)it.next()).equals((Entry)res[i]));
        }

        Object[] obj2 = new Object[20];
        Object[] res2 = hs.toArray(obj2);
        it = hs.iterator();

        for (int i = 0; i < hs.size(); i++) {
            assertTrue(((Entry)it.next()).equals((Entry)res2[i]));
        }

        printMap();
    }


    /**
     * riempimento mappa, creazione di un entrySet, creazione di un 
     * array nullo e passaggio di esso tramite il metodo toArray(), 
     * controllo corretto lancio NullPointerException. 
     */
    @Test
    public void entryToArrayObjTestExc() {

        System.out.println("-- entryToArrayObjTestExc(Object[] a) --\n");
    
        fillMap();
        HSet hs = map.entrySet();
        Object[] obj = null;

        assertThrows(NullPointerException.class,
                    () -> {hs.toArray(obj);});

        printMap();
    }







        // - - - HELPER FUNCTION - - - //




        /**
         * riempie la mappa.
         */
        private void fillMap() {
        
            for (int i = 0; i < start_test_size; i++) {
                map.put(i, i+10);
//                System.out.print(i);      // for debugging
            }
            System.out.println();
        }


        /**
         * stampa gli elementi della mappa.
         */
        private void printMap() {

            System.out.println("The map is in the following condition");
            HSet s = map.entrySet();
            Object[] o  = s.toArray();

            Entry[] e = oToEntry(o);

            for (int i = 0; i < map.size(); i++) {
                System.out.println("Key: " + e[i].getKey() + " Value: " + e[i].getValue());  
            }
            System.out.println("Size: " + map.size());
            System.out.println();
        }
        

        /**
         * trasforma un array di Object nell'array corrispondente di Entry.
         * 
         * @param   o - array che si desidera trasformare.
         * @return  array di Entry corrispondente a quello di Object inserito.
         */
        private Entry[] oToEntry(Object[] o) {

            Entry[] e = new Entry[o.length];

            for (int i = 0; i < o.length; i++) {
                e[i] = (Entry)o[i];
            }

            return e;
        }


        
}
