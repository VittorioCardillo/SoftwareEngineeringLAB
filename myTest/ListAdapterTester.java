package myTest;

import myAdapter.*;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;
import java.util.Vector;

public class ListAdapterTester {

    /**
     * Oggetto utilizzato per eseguire ogni test.
     */
    private ListAdapter list;
    
    /**
     * Elementi che verranno inseriti nella lista.
     */
    private final int start_test_size = 10;

    /**
     * Creazione della lista, verrà eseguita ad ogni nuovo test.
     */
    @Before
    public void inizialization() {

        list = new ListAdapter();
    }


    // - - - TEST LIST - - - //


    /**
     * Controllo che l’oggetto ListAdapter sia stato creato, 
     * riempimento di esso tramite il metodo fillList(), 
     * controllo del corretto riempimento dell’oggetto. 
     */
    @Test
    public void initializationTest() {

        System.out.println("-- initializationTest --\n");

        assertEquals(0, list.size());
        fillList();
        assertNotEquals(1, list.size());

        printList();
    }

    /**
     * aggiunta di un elemento a lista vuota, controllo corretto aumento 
     * della dimensione della lista e dell’inserimento nella posizione 
     * corretta dell’oggetto precedentemente aggiunto. 
     * Riempimento lista, aggiunta di due elementi, controllo corretto 
     * aumento della dimensione della lista e dell’inserimento 
     * nella posizione corretta degli oggetti precedentemente aggiunti. 
     */
    @Test
    public void addTest() {

        System.out.println("-- addTest(int index, Object element) --\n");

        list.add(0, "one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        fillList();
        assertEquals(list.get(0), "one");
        list.add(5, "two");
        assertEquals(start_test_size + 2, list.size());
        assertEquals(list.get(5), "two");
        list.add(list.size(), "three");
        assertEquals(start_test_size + 3, list.size());

        printList();
    }

    /**
     * Riempimento lista, aggiunta di due elementi in posizioni non valide, 
     * rispettivamente -1 e size()+1, controllo corretto lancio eccezioni. 
     * Aggiunta di un elemento nullo, controllo corretto lancio eccezioni. 
     */
    @Test 
    public void addTestExc() {

        System.out.println("-- addTestExc(int index, Object element) --\n");

        fillList();
        assertThrows(IndexOutOfBoundsException.class,
                    () -> {list.add(-1, "Hello");});

        assertThrows(IndexOutOfBoundsException.class,
                    () -> {list.add(list.size() + 1, "Hello");});

        assertThrows(NullPointerException.class,
                    () -> {list.add(0, null);});

        printList();
    }

    /**
     * aggiunta di un elemento a lista vuota, controllo corretto aumento 
     * della dimensione della lista e dell’inserimento nella posizione corretta 
     * dell’oggetto precedentemente aggiunto. Riempimento lista, aggiunta di un elemento, 
     * controllo corretto aumento della dimensione della lista e dell’inserimento 
     * nella posizione corretta degli oggetti precedentemente aggiunti. 
     */
    @Test
    public void basicAddText() {

        System.out.println("-- addTest(Object element) --\n");


        list.add("one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        fillList();
        list.add("two");
        assertEquals(start_test_size + 2, list.size());
        assertEquals(list.get(list.size() - 1), "two");

        printList();
    }

    /**
     * Riempimento lista, aggiunta di un elemento nullo {@code null} 
     * e controllo del corretto lancio di NullPointerException. 
     */
    @Test
    public void basicAddTextExc() {

        System.out.println("-- addTestExc(Object element) --\n");

        fillList();
        assertThrows(NullPointerException.class,
                    () -> {list.add(null);});

        printList();
    }

    /**
     * creazione di una Collection di due elementi, 
     * aggiunta della Collection alla lista vuota, 
     * controllo corretto aumento della dimensione della lista e
     *  successivo svuotamento di essa. Riempimento lista, 
     * aggiunta di una Collection, 
     * controllo corretto aumento della dimensione della lista. 
     */
    @Test
    public void addAllTest() {

        System.out.println("-- addAllTest(HCollection c) --\n");

        Vector<Object> vec = new Vector<Object>();
        vec.add("one");
        vec.add("two");
        ListAdapter lis = new ListAdapter(vec);
        HCollection col = (HCollection) lis;

        list.addAll(col);
        assertEquals(2, list.size());
        list.clear();
        fillList();
        list.addAll(col);
        assertEquals(start_test_size + 2, list.size());

        printList();
    } 

    /**
     * creazione Collection nulla, controllo che l’aggiunta di tale Collection 
     * alla lista restituisca NullPointerException, creazione di una 
     * Collection con un solo elemento nullo, 
     * controllo che l’aggiunta di tale collezione alla lista restituisca NullPointerException. 
     */
    @Test
    public void addAllTestExc() {

        System.out.println("-- addAllTestExc(HCollection c) --\n");

        HCollection col1 = null;

        assertThrows(NullPointerException.class,
                    () -> {list.addAll(col1);});

        Vector<Object> vec = new Vector<Object>();
        vec.add("one");
        vec.add(null);
        ListAdapter lis = new ListAdapter(vec);
        HCollection col2 = (HCollection) lis;

        assertThrows(NullPointerException.class,
                    () -> {list.addAll(col2);});

        printList();
    }

    /**
     * creazione di una Collection di due elementi, 
     * aggiunta della Collection alla lista vuota, 
     * controllo corretto aumento della dimensione della lista e successivo 
     * svuotamento di essa. Riempimento lista, aggiunta di una Collection, 
     * controllo corretto aumento della dimensione della lista. 
     */
    @Test
    public void addAllIndexTest() {

        System.out.println("-- addAllIndexTest(int index, HCollection c) --\n");

        Vector<Object> vec = new Vector<Object>();
        vec.add("one");
        vec.add("two");
        ListAdapter lis = new ListAdapter(vec);
        HCollection col = (HCollection) lis;

        list.addAll(0, col);
        assertEquals(2, list.size());
        list.clear();
        fillList();
        list.addAll(3, col);
        assertEquals(start_test_size + 2, list.size());

        printList();
    }

    /**
     * creazione Collection nulla, riempimento lista, 
     * controllo che l’aggiunta di tale 
     * Collection alla lista restituisca NullPointerException, 
     * creazione di una Collection con un solo elemento nullo, 
     * controllo che l’aggiunta di tale collezione alla lista restituisca 
     * NullPointerException. Creazione Collection non nulla, 
     * controllo che l’inserimento di tale Collection in un indice non 
     * valido {@code ]-inf, 0[ & ]size, +inf[ } lanci IndexOutOfBoundException.
     */
    @Test
    public void addAllIndexTestExc() {

        System.out.println("-- addAllIndexTestExc(int index, HCollection c) --\n");

        HCollection col1 = null;
        fillList();
        assertThrows(NullPointerException.class,
                    () -> {list.addAll(2, col1);});

                    
        Vector<Object> vec = new Vector<Object>();
        vec.add("one");
        vec.add(null);
        ListAdapter lis = new ListAdapter(vec);
        HCollection col2 = (HCollection) lis;

        assertThrows(NullPointerException.class,
                    () -> {list.addAll(2, col2);});

        vec.setElementAt("two", 1);
        lis = new ListAdapter(vec);
        HCollection col3 = (HCollection) lis;

        assertThrows(IndexOutOfBoundsException.class,
                    () -> {list.addAll(-1, col3);});

        printList();
    }

    /**
     * riempimento lista, rimozione degli elementi tramite il metodo clear(), 
     * controllo che la dimensione della lista sia pari a zero.
     */
    @Test
    public void clearTest() {

        System.out.println("-- clearTest() --\n");

        fillList();
        list.clear();
        assertEquals(0, list.size());

        printList();
    }

    /**
     * riempimento lista, controllo dell’esistenza o meno di due 
     * particolari valori all’interno della lista, 
     * uno dei quali è effettivamente presente, l’altro no. 
     */
    @Test
    public void containsTest(){

        System.out.println("-- containsTest(Object o) --\n");

        fillList();
        assertTrue(list.contains(12));
        assertFalse(list.contains(100));

        printList();
    }

    /**
     * viene cercato all’interno della lasta il valore nullo {@code null }, tale inserimento 
     * deve causare il lancio dell’eccezione NullPointerException. 
     */
    @Test
    public void containsTestExc(){

        System.out.println("-- containsTestExc(Object o) --\n");

        assertThrows(NullPointerException.class,
                    () -> {list.contains(null);});

        printList();
    }

    /**
     * riempimento lista, creazione di una Collection di due elementi 
     * esistenti all’interno della lista, ricerca della Collection nella 
     * lista, aggiunta di un elemento non presente nella lista all’interno 
     * della Collection, ricerca della Collection nella lista. 
     */
    @Test
    public void containsAllTest(){

        System.out.println("-- containsAllTest(HCollection c) --\n");

        fillList();
        Vector<Object> vec = new Vector<Object>();
        vec.add(12);
        vec.add(13);
        ListAdapter lis = new ListAdapter(vec);
        HCollection col = (HCollection) lis;

        assertTrue(list.containsAll(col));

        lis.add(100);
        col = (HCollection) lis;

        assertFalse(list.containsAll(col));

        printList();
    }

    /**
     * creazione Collection nulla, riempimento lista, controllo che la ricerca di tale 
     * Collection nella lista restituisca NullPointerException, 
     * creazione di una Collection con un solo elemento nullo, 
     * controllo che la ricerca di tale collezione nella lista restituisca NullPointerException. 
     */
    @Test
    public void containsAllTestExc(){

        System.out.println("-- containsAllTestExc(HCollection c) --\n");

        HCollection col1 = null;
        fillList();
        assertThrows(NullPointerException.class,
                    () -> {list.addAll(2, col1);});

                    
        Vector<Object> vec = new Vector<Object>();
        vec.add("one");
        vec.add(null);
        ListAdapter lis = new ListAdapter(vec);
        HCollection col2 = (HCollection) lis;

        assertThrows(NullPointerException.class,
                    () -> {list.addAll(2, col2);});

        printList();
    }

    /**
     * riempimento lista, creazione nuova lista identica alla precedente, 
     * verifica che le due liste siano uguali, 
     * aggiunta ad una delle due liste di un elemento, 
     * verifica che le due liste non siano più uguali. 
     * Controllo uguaglianza con null, controllo uguaglianza con una stringa.
     */
    @Test
    public void equalsTest() {

        System.out.println("-- equalsTest(Object o) --\n");

        fillList();
        ListAdapter list2 = new ListAdapter();
        for (int i = 0; i < start_test_size; i++)
            list2.add(i + 10);

        assertTrue(list.equals(list2));

        list.add("Hello");

        assertFalse(list.equals(list2));
        assertFalse(list.equals(null));
        assertFalse(list.equals("String"));

        printList();
    }


    /**
     * riempimento lista, utilizzo del metodo get() e 
     * controllo dalla corretta restituzione dell’elemento corrispondente. 
     */
    @Test
    public void getTest() {

        System.out.println("-- getTest(int index) --\n");

        fillList();
        assertEquals(12, list.get(2));

        printList();
    }

    /**
     * tentativo di utilizzo del metodo get() in due posizioni non valide,
     * rispettivamente -1 e 100, avendo come insieme di indici validi [0, 9], 
     * e successivo controllo corretto lancio eccezioni. 
     */
    @Test
    public void getTestExc() {

        System.out.println("-- getTestExc(int index) --\n");

        assertThrows(IndexOutOfBoundsException.class,
                    () -> {list.get(-1);});

        assertThrows(IndexOutOfBoundsException.class,
                    () -> {list.get(100);});

        printList();
    }

    /**
     * riempimento lista, creazione nuova lista identica alla precedente, 
     * verifica che gli hashCode delle due liste siano uguali. 
     */
    @Test
    public void hashCodeTest() {

        System.out.println("-- hashCodeTest() --\n");
        
        fillList();
        ListAdapter list2 = new ListAdapter();

        for (int i = 0; i < start_test_size; i++)
                list2.add(i + 10);
        
        assertEquals(list.hashCode(), list2.hashCode());

        printList();
    }


    /**
     * riempimento lista, utilizzo del metodo indexOf() e controllo 
     * dalla corretta restituzione dell’indice corrispondente.
     * Vengono cercati due oggetti, uno presente effettivamente 
     * all’interno della lista e uno non presente, in modo 
     * tale da verificare anche la corretta restituzione del valore -1. 
     */
    @Test
    public void indexOfTest() {

        System.out.println("-- indexOfTest(Object o) --\n");

        fillList();
        assertEquals(2, list.indexOf(12));
        assertEquals(-1, list.indexOf(100));

        printList();

    }

    /**
     * riempimento lista, tentativo di utilizzo del metodo indexOf() passando un oggetto nullo, viene 
     * successivamente atteso il lancio dell’eccezione NullPointerException. 
     */
    @Test
    public void indexOfTestExc() {

        System.out.println("-- indexOfTestExc(Object o) --\n");

        fillList();
        assertThrows(NullPointerException.class,
                    () -> {list.indexOf(null);});

        printList();
    }

    /**
     * riempimento lista, controllo tramite isEmpty() che la lista non 
     * sia vuota, rimozione degli elementi tramite il metodo clear(), 
     * controllo tramite isEmpty() che la lista sia vuota. 
     */
    @Test
    public void isEmptyTest() {

        System.out.println("-- isEmptyTest() --\n");

        fillList();
        assertFalse(list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());

        printList();
    }

    /**
     * riempimento lista, creazione di un iteratore, 
     * controllo del corretto funzionamento del metodo next(), 
     * sia che restituisca il valore corretto, sia che non restituisca 
     * un valore errato, controllo del corretto funzionamento del 
     * metodo hasNext(), utilizzato per spostarsi all’ultimo elemento 
     * e controllando che quello sia effettivamente l’ultimo elemento 
     * chiamando next() e controllando che lanci NullPointerException. 
     */
    @Test
    public void iteratorTest() {
        
        System.out.println("-- iteratorTest() + method + exception --\n");

        fillList();
        HIterator it = list.iterator();

        assertEquals(10, it.next());
        assertNotEquals(12, it.next());
        while (it.hasNext()) 
            it.next();

        assertThrows(NoSuchElementException.class,
                    () -> {it.next();});

        printList();
    }

    /**
     * riempimento lista, inserimento di altri due elementi alla sua coda, 
     * utilizzo del metodo lastIndexOf() e controllo dalla corretta 
     * restituzione dell’indice corrispondente. Vengono cercati due oggetti, 
     * uno presente effettivamente all’interno della lista e uno non presente, in modo 
     * tale da verificare anche la corretta restituzione del valore -1. 
     */
    @Test
    public void lastIndexOfTest() {

        System.out.println("-- lastIndexOfTest(Object o) --\n");

        fillList();
        list.add(25);
        list.add(30);
        assertEquals(10, list.lastIndexOf(25));
        assertEquals(-1, list.lastIndexOf(99));

        printList();
    }


    /**
     * riempimento lista, tentativo di utilizzo del metodo lastIndexOf() passando un oggetto nullo, 
     * viene successivamente atteso il lancio dell’eccezione NullPointerException. 
     */
    @Test
    public void lastIndexOfTestExc() {

        System.out.println("-- lastIndexOfTestExc(Object o) --\n");

        fillList();

        assertThrows(NullPointerException.class,
                    () -> {list.lastIndexOf(null);});

        printList();
    }


    /**
     * riempimento lista, creazione di un iteratore, 
     * controllo del corretto funzionamento del metodo next(), 
     * nextIndex(), previous(), previousIndex(), spostandosi avanti e 
     * indietro all’interno della lista e controllando le corrispondenze 
     * tra posizione attuale, indice restituito e valori attesi. 
     * Si test poi il metodo remove() e il metodo add(), controllano 
     * la successiva esistenza dell’oggetto aggiunto e che sia stato 
     * inserito nella posizione corretta. Successivamente si verifica 
     * il corretto funzionamento di hasPrevious(), utilizzato per 
     * spostarsi al primo elemento e controllando che quello sia 
     * effettivamente il primo elemento 
     * chiamando previous() e controllando che lanci NullPointerException. 
     */
    @Test
    public void listIteratorTest() {
        
        System.out.println("-- listIteratorTest() + method + exception --\n");

        fillList();
        HListIterator it = list.listIterator();
        assertEquals(10, it.next());
        assertEquals(1, it.nextIndex());
        assertEquals(11, it.next());
        assertEquals(1, it.previousIndex());
        assertEquals(11, it.previous());
        it.remove();
        assertEquals(9, list.size());
        it.add("Hello");
        assertEquals(12, it.next());
        while(it.hasPrevious())
            it.previous();
        assertEquals(10, it.next());
        assertEquals("Hello", it.next());

        while(it.hasPrevious())
        it.previous();
        assertThrows(NoSuchElementException.class,
                    () -> {it.previous();});

        printList();
    }

    /**
     * riempimento lista, creazione di un iteratore passando un indice 
     * al costruttore, controllo che il successivo valore restituito da 
     * next() sia quello aspettato, controllo che il passaggio al 
     * costruttore di un indice non valido restituisca IndexOutOfBoundException. 
     */
    @Test
    public void listIteratorIndexTest() {

        System.out.println("-- listIteratorIndexTest(int index) + method + exception --\n");

        fillList();
        HListIterator it = list.listIterator(4);
        assertEquals(14, it.next());

        assertThrows(IndexOutOfBoundsException.class,
                    () -> {list.listIterator(100);});

        printList();
    }

    /**
     * riempimento lista, rimozione di un elemento tramite remove() e 
     * controllo corretta restituzione dell’elemento corrispondente all’indice rimosso, 
     * controllo corretta diminuzione della dimensione della lista. 
     */
    @Test
    public void removeTest() {

        System.out.println("-- removeTest(int index) --\n");

        fillList();
        assertEquals(14, list.remove(4));
        assertEquals(9, list.size());

        printList();
    }

    /**
     * rimozione di un elemento in posizione size() avendo la lista 
     * vuota e controllo corretto lancio IndexOutOfBoundException, 
     * riempimento lista, rimozione di un elemento in posizione size(),
     * controllo corretto lancio IndexOutOfBoundException. 
     */
    @Test
    public void removeTestExc() {

        System.out.println("-- removeTestExc(int index) --\n");

        assertThrows(IndexOutOfBoundsException.class,
                    () -> {list.remove(list.size());});
        fillList();
        assertThrows(IndexOutOfBoundsException.class,
                    () -> {list.remove(list.size());});
                    
    }

    /**
     * riempimento lista, rimozione di un elemento presente nella lista 
     * tramite remove(Object o) e controllo corretta restituzione di 
     * {@code true } e della corretta diminuzione della dimensione della lista. 
     * Tentativo di rimozione di un oggetto non presente nella lista, 
     * controllo che il metodo restituisca {@code false } e che la dimensione 
     * della lista non sia cambiata. 
     */
    @Test
    public void removeObjTest() {
        
        System.out.println("-- removeObjTest(Object o) --\n");

        fillList();
        Integer i = 14;
        assertTrue(list.remove(i));
        assertEquals(9, list.size());
        i = 25;
        assertFalse(list.remove(i));
        assertEquals(9, list.size());
        
        printList();
    }

    /**
     * rimozione di un elemento nullo {@code null } avendo la lista vuota 
     * e controllo corretto lancio NullPointerException, riempimento 
     * lista, rimozione di un elemento nullo {@code null }, controllo 
     * corretto lancio NullPointerException. 
     */
    @Test
    public void removeObjTestExc() {

        System.out.println("-- removeObjTestExc(Object o) --\n");

        Integer i = null;
        assertThrows(NullPointerException.class,
                    () -> {list.remove(i);});
        fillList();
        assertThrows(NullPointerException.class,
                    () -> {list.remove(i);});
                    
    }

    /**
     * creazione di una Collection di due elementi entrambi presenti nella lista, riempimento lista, 
     * rimozione della Collection, controllo corretta diminuzione della 
     * dimensione della lista. Aggiunta di un elemento non presente nella 
     * lista alla Collection, pulizia lista e nuovo riempimento di essa, 
     * rimozione della Collection dalla lista e controllo corretta 
     * diminuzione della dimensione della lista. 
     */
    @Test
    public void removeAllTest() {

        System.out.println("-- removeAllTest(HCollection c) --\n");

        ListAdapter l = new ListAdapter();
        l.add(12);
        l.add(15);
        HCollection col = (HCollection) l;
        fillList();

        list.removeAll(col);
        assertEquals(8, list.size());
        l.add(25);
        list.clear();
        fillList();
        col = (HCollection) l;
        list.removeAll(col);
        assertEquals(8, list.size());

        printList();
    }

    /**
     * creazione Collection nulla, controllo che la rimozione 
     * di tale Collection dalla lista restituisca NullPointerException. 
     */
    @Test
    public void removeAllTestExc() {
        
        System.out.println("-- removeAllTestExc(HCollection c) --\n");

        fillList();
        HCollection col1 = null;
        assertThrows(NullPointerException.class,
                    () -> {list.removeAll(col1);});

        printList();

    }

    /**
     * creazione di una Collection di due elementi entrambi presenti 
     * nella lista, riempimento lista, utilizzo del metodo retainAll(), 
     * controllo corretta diminuzione della dimensione della lista. 
     * Aggiunta di un elemento non presente nella lista alla Collection, 
     * pulizia lista e nuovo riempimento di essa, utilizzo del metodo 
     * retainAll() e controllo corretta diminuzione della dimensione della lista. 
     */
    @Test
    public void retainAllTest() {

        System.out.println("-- retainAllTest(HCollection c) --\n");

        ListAdapter l = new ListAdapter();
        l.add(12);
        l.add(15);
        HCollection col = (HCollection) l;
        fillList();

        list.retainAll(col);
        assertEquals(2, list.size());
        list.clear();
        fillList();
        l.add(25);
        col = (HCollection) l;
        list.retainAll(col);
        assertEquals(2, list.size());

        printList();
    }

    /**
     * creazione Collection nulla, controllo che l’utilizzo di retainAll() 
     * con tale Collection dalla lista restituisca NullPointerException. 
     */
    @Test
    public void retainAllTestExc() {
        
        System.out.println("-- retainAllTestExc(HCollection c) --\n");

        fillList();
        HCollection col1 = null;
        assertThrows(NullPointerException.class,
                    () -> {list.retainAll(col1);});

        printList();

    }

    /**
     * riempimento lista, sostituzione di un elemento, controllo 
     * corretta presenza nella lista dell’elemento appena inserito. 
     */
    @Test
    public void setTest() {

        System.out.println("-- setTest(int index, Object element) --\n");

        fillList();
        list.set(4, "Hello");
        assertEquals("Hello", list.get(4));
    }

    /**
     * Riempimento lista, aggiunta di due elementi in posizioni non valide, 
     * rispettivamente -1 e size(), controllo corretto lancio eccezioni. 
     * Aggiunta di un elemento nullo, controllo corretto lancio eccezioni. 
     */
    @Test
    public void setTestExc() {
        
        System.out.println("-- setTestExc(int index, Object element) --\n");
        
        fillList();
        assertThrows(IndexOutOfBoundsException.class,
                    () -> {list.set(-1, "Hello");});

        assertThrows(IndexOutOfBoundsException.class,
                    () -> {list.set(list.size(), "Hello");});

        
        assertThrows(NullPointerException.class,
                    () -> {list.set(2, null);});

        printList();

    }

    /**
     * controllo se con la lista vuota il metodo size() restituisce 0, riempimento lista, 
     * controllo tramite size() che la dimensione della lista corrisponda 
     * a 10, rimozione di un elemento tramite il metodo remove(), 
     * controllo tramite size() che la lista abbia dimensione 9. 
     */
    @Test
    public void sizeTest() {

        System.out.println("-- sizeTest() --\n");

        assertEquals(0, list.size());
        fillList();
        assertEquals(10, list.size());
        list.remove(0);
        assertEquals(9, list.size());

        printList();
    }


    /**
     * riempimento lista, creazione di una SubList, controllo che la 
     * dimensione della lista creata sia corretta. Creazione di un’altra 
     * SubList, questa volta con gli indici from e to coincidenti e 
     * controllo della dimensione della SubList creata. 
     * Creazione di un’altra SubList, questa volta con gli indici 
     * coincidenti agli estremi della lista originale, controllo che 
     * le dimensioni siano coincidenti. 
     */
    @Test
    public void subListTest() {

        System.out.println("-- subListTest(int from, int to) --\n");

        fillList();
        HList hl = list.subList(1, 4);
        assertEquals(3, hl.size());
        hl = list.subList(0, 0);
        assertEquals(0, hl.size());
        hl = list.subList(0, list.size());
        assertEquals(list.size(), hl.size());

        printList();
    }

    /**
     * creazione di due SubList con indici in posizioni non valide, 
     * la prima (to = 100) e la seconda (from = -2), controllo corretto 
     * lancio eccezioni. 
     */
    @Test
    public void subListTestExc() {

        System.out.println("-- subListTestExc() --\n");

        assertThrows(IndexOutOfBoundsException.class,
                    () -> {list.subList(2, 100);});

        assertThrows(IndexOutOfBoundsException.class,
                    () -> {list.subList(-2, 8);});

        printList();

    }

    /**
     * riempimento lista, creazione di un array tramite il metodo toArray(), 
     * controllo che tutti gli elementi dell’array coincidano in valore e 
     * posizione a tutti gli elementi della lista originaria. 
     */
    @Test 
    public void toArrayTest() {

        System.out.println("-- toArrayTest() --\n");

        fillList();
        Object[] arry = list.toArray();

        for (int i = 0; i < arry.length; i++) {
            assertEquals(list.get(i), arry[i]);
        }

        printList();
    }


    /**
     * riempimento lista, creazione di un array a due elementi e 
     * utilizzo del metodo toArray() passando come parametro l’array 
     * creato, controllo che tutti gli elementi dell’array coincidano 
     * in valore e posizione a tutti gli elementi della lista originaria. 
     * Creazione di un array a venti elementi e utilizzo del metodo 
     * toArray() passando come parametro l’array creato, controllo che 
     * tutti gli elementi dell’array coincidano in valore e posizione a 
     * tutti gli elementi della lista originaria. 
     */
    @Test
    public void toArrayObjTest() {

        System.out.println("-- toArrayObjTest(Object[] a) --\n");

        fillList();
        Object[] obj = {2, 3};
        Object[] res = list.toArray(obj);

        for (int i = 0; i < res.length; i++) {
            assertEquals(list.get(i), res[i]);
        }

        Object[] obj2 = new Object[20];
        Object[] res2 = list.toArray(obj2);

        for (int i = 0; i < list.size(); i++) {
            assertEquals(list.get(i), res2[i]);
        }

        printList();
    }


    /**
     * riempimento lista, creazione di un array nullo e passaggio di 
     * esso tramite il metodo toArray(), controllo corretto 
     * lancio NullPointerException. 
     */
    @Test
    public void toArrayObjTestExc() {

        System.out.println("-- toArrayObjTestExc(Object[] a) --\n");
    
        fillList();
        Object[] obj = null;

        assertThrows(NullPointerException.class,
                    () -> {list.toArray(obj);});

        printList();
    }



    // - - - TEST SUBLIST - - - //


    /**
     * riempimento lista, creazione SubList, aggiunta di due elementi, 
     * controllo corretto aumento della dimensione della lista e 
     * dell’inserimento nella posizione corretta degli oggetti 
     * precedentemente aggiunti. 
     */
    @Test
    public void subListAddText() {

        System.out.println("-- subListAddText(Object a) --\n");

        fillList();
        HList l = list.subList(2, 5);
        l.add("one");
        assertEquals(start_test_size + 1, list.size());
        l.add("two");
        assertEquals(start_test_size + 2, list.size());
        assertEquals(l.get(l.size() - 1), "two");

        printSubList(l);
        
    }

    /**
     * riempimento lista, aggiunta di un elemento nullo 
     * {@code null } e controllo del corretto lancio di NullPointerException . 
     */
    @Test
    public void subListAddTextExc() {

        System.out.println("-- subListAddTextExc(Object a) --\n");

        fillList();
        HList l = list.subList(2, 5);
        assertThrows(NullPointerException.class,
                    () -> {l.add(null);});

        printSubList(l);
    }
    

    /**
     * creazione di una Collection di due elementi, riempimento lista, 
     * creazione SubList, aggiunta della Collection alla SubList, 
     * controllo corretto aumento della dimensione della lista, 
     * controllo corretta posizione degli elementi appena giunti alla lista. 
     */
    @Test
    public void subListAddAllTest() {

        System.out.println("-- subListAddAllTest(HCollection c) --\n");

        Vector<Object> vec = new Vector<Object>();
        vec.add("one");
        vec.add("two");
        ListAdapter lis = new ListAdapter(vec);
        HCollection col = (HCollection) lis;

        fillList();
        HList l = list.subList(2, 5);
        l.addAll(col);
        assertEquals(5, l.size());
        assertEquals("one", l.get(3));

        printSubList(l);
    } 

    /**
     * creazione Collection nulla, riempimento lista, creazione SubList, 
     * controllo che l’aggiunta di tale Collection alla SubList 
     * restituisca NullPointerException, creazione di una Collection 
     * con un solo elemento nullo, controllo che l’aggiunta di tale 
     * collezione alla SubList restituisca NullPointerException. 
     */
    @Test
    public void subListAddAllTestExc() {

        System.out.println("-- subListAddAllTestExc(HCollection c) --\n");

        HCollection col1 = null;
        fillList();
        HList l = list.subList(2, 5);
        assertThrows(NullPointerException.class,
                    () -> {l.addAll(col1);});

        Vector<Object> vec = new Vector<Object>();
        vec.add("one");
        vec.add(null);
        ListAdapter lis = new ListAdapter(vec);
        HCollection col2 = (HCollection) lis;

        assertThrows(NullPointerException.class,
                    () -> {l.addAll(col2);});

        printSubList(l);
    }


    /**
     * riempimento lista, creazione SubList, utilizzo del metodo get() e 
     * controllo dalla corretta restituzione dell’elemento corrispondente. 
     */
    @Test
    public void subListGetTest() {

        System.out.println("-- subListGetTest(int index) --\n");

        fillList();
        HList l = list.subList(2, 5);
        assertEquals(14, l.get(2));

        printSubList(l);
    }

    /**
     *  tentativo di utilizzo del metodo get() in due posizioni non valide, 
     * rispettivamente -1 e 3, avendo come insieme di indici validi [2, 4], 
     * e successivo controllo corretto lancio eccezioni. 
     */
    @Test
    public void subListGetTestExc() {

        System.out.println("-- subListGetTestExc(int index) --\n");

        fillList();
        HList l = list.subList(2, 5);
        assertThrows(IndexOutOfBoundsException.class,
                    () -> {l.get(-1);});

        assertThrows(IndexOutOfBoundsException.class,
                    () -> {l.get(3);});

        printSubList(l);
    }
    

    /**
     * riempimento lista, creazione SubList, rimozione degli elementi 
     * tramite il metodo clear(), controllo che la dimensione della 
     * SubList sia pari a zero, controllo che la dimensione della lista 
     * originale sia 7, ovvero {@code list.size() – SubList.size() } 
     * quando sono state appena create.
     */
    @Test
    public void subListClearTest() {

        System.out.println("-- subListClearTest() --\n");

        fillList();
        HList l = list.subList(2, 5);
        l.clear();
        assertEquals(0, l.size());
        assertEquals(7, list.size());

        printSubList(l);
    }

    /**
     * riempimento lista, creazione SubList, controllo dell’esistenza 
     * o meno di due particolari valori all’interno della lista, 
     * uno dei quali è effettivamente presente, l’altro no. 
     */
    @Test
    public void subListContainsTest(){

        System.out.println("-- subListContainsTest(Object o) --\n");

        fillList();
        HList l = list.subList(2, 5);
        assertTrue(l.contains(12));
        assertFalse(l.contains(100));

        printSubList(l);
    }

    /**
     * riempimento lista, creazione SubList, viene cercato all’interno 
     * della SubList il valore nullo { null }, tale inserimento deve 
     * causare il lancio dell’eccezione NullPointerException. 
     */
    @Test
    public void subListContainsTestExc(){

        System.out.println("-- subListContainsTestExc(Object o) --\n");

        fillList();
        HList l = list.subList(2, 5);

        assertThrows(NullPointerException.class,
                    () -> {l.contains(null);});

        printSubList(l);
    }

    /**
     * riempimento lista, creazione SubList, creazione di una Collection 
     * di due elementi esistenti all’interno della SubList, 
     * ricerca della Collection nella SubList, aggiunta di un elemento 
     * non presente nella SubList all’interno della Collection, 
     * ricerca della Collection nella SubList. 
     */
    @Test
    public void subListContainsAllTest(){

        System.out.println("-- subListContainsAllTest(HCollection c) --\n");

        fillList();
        HList l = list.subList(2, 5);
        Vector<Object> vec = new Vector<Object>();
        vec.add(12);
        vec.add(13);
        ListAdapter lis = new ListAdapter(vec);
        HCollection col = (HCollection) lis;

        assertTrue(l.containsAll(col));

        lis.add(100);
        col = (HCollection) lis;

        assertFalse(l.containsAll(col));

        printSubList(l);
    }

    /**
     * creazione Collection nulla, riempimento lista, creazione SubList, 
     * controllo che la ricerca di tale Collection nella SubList 
     * restituisca NullPointerException, creazione di una Collection 
     * con un solo elemento nullo, controllo che la ricerca di tale 
     * collezione nella SubList restituisca NullPointerException. 
     */
    @Test
    public void subListContainsAllTestExc(){

        System.out.println("-- subListContainsAllTestExc(HCollection c) --\n");

        HCollection col1 = null;
        fillList();
        HList l = list.subList(2, 5);
        assertThrows(NullPointerException.class,
                    () -> {l.addAll(2, col1);});

                    
        Vector<Object> vec = new Vector<Object>();
        vec.add("one");
        vec.add(null);
        ListAdapter lis = new ListAdapter(vec);
        HCollection col2 = (HCollection) lis;

        assertThrows(NullPointerException.class,
                    () -> {l.addAll(2, col2);});

        printSubList(l);
    }

    /**
     * riempimento lista, creazione nuova lista identica alla precedente, 
     * verifica che le due SubList siano uguali, aggiunta ad una delle 
     * due SubList di un elemento, verifica che le due SubList non siano 
     * più uguali. 
     */
    @Test
    public void subListEqualsTest() {

        System.out.println("-- subListEqualsTest(Object o) --\n");

        fillList();
        HList l = list.subList(2, 5);
        ListAdapter list2 = new ListAdapter();
        for (int i = 0; i < 3; i++)
            list2.add(i + 12);

        assertTrue(l.equals(list2));

        l.add("Hello");

        assertFalse(l.equals(list2));
        assertFalse(l.equals(null));
        assertFalse(l.equals("String"));

        printSubList(l);
    }

    /**
     * riempimento lista, creazione SubList, 
     * creazione nuova SubList identica alla precedente, 
     * verifica che le due SubList siano uguali, aggiunta ad una 
     * delle due SubList di un elemento, verifica che gli hashCode 
     * delle due SubList siano uguali. 
     */
    @Test
    public void subListHashCodeTest() {

        System.out.println("-- subListHashCodeTest() --\n");
        
        fillList();
        HList l = list.subList(2, 5);
        ListAdapter list2 = new ListAdapter();

        for (int i = 0; i < 3; i++)
                list2.add(i + 12);
        
        assertEquals(l.hashCode(), list2.hashCode());

        printSubList(l);
    }


    /**
     * riempimento lista, creazione SubList, controllo tramite isEmpty() 
     * che la SubList non sia vuota, rimozione degli elementi tramite il 
     * metodo clear(), controllo tramite isEmpty() che la SubList sia vuota. 
     */
    @Test
    public void subListIsEmptyTest() {

        System.out.println("-- subListIsEmptyTest() --\n");

        fillList();
        HList l = list.subList(2, 5);
        assertFalse(l.isEmpty());
        l.clear();
        assertTrue(l.isEmpty());

        printSubList(l);
    }

    /**
     * riempimento lista, creazione SubList, creazione di un iteratore, 
     * controllo del corretto funzionamento del metodo next(), sia che 
     * restituisca il valore corretto, sia che non restituisca un valore 
     * errato, controllo del corretto funzionamento del metodo hasNext(), 
     * utilizzato per spostarsi all’ultimo elemento e controllando che 
     * quello sia effettivamente l’ultimo elemento chiamando next() e 
     * controllando che lanci NullPointerException. 
     */
    @Test
    public void subListIteratorTest() {
        
        System.out.println("-- subListIteratorTest() + method + exception --\n");

        fillList();
        HList l = list.subList(2, 5);
        HIterator it = l.iterator();

        assertEquals(12, it.next());
        assertNotEquals(10, it.next());
        while (it.hasNext()) 
            it.next();

        assertThrows(NoSuchElementException.class,
                    () -> {it.next();});

        printSubList(l);
    }


    /**
     * riempimento lista, creazione SubList, 
     * rimozione di un elemento presente nella SubList tramite 
     * remove(Object o) e controllo corretta restituzione di {@code true } 
     * e della corretta diminuzione della dimensione della SubList. 
     * Tentativo di rimozione di un oggetto non presente nella SubList, 
     * controllo che il metodo restituisca {@code false } e che la dimensione 
     * della SubList non sia cambiata. 
     */
    @Test
    public void subListRemoveTest() {
        
        System.out.println("-- subListRemoveTest(Object o) --\n");

        fillList();
        HList l = list.subList(2, 5);
        Integer i = 14;
        assertTrue(l.remove(i));
        assertEquals(2, l.size());
        i = 25;
        assertFalse(l.remove(i));
        assertEquals(2, l.size());
        
        printSubList(l);
    }

    /**
     * creazione SubList vuota, rimozione di un elemento nullo { null } 
     * avendo la SubList vuota e controllo corretto lancio 
     * NullPointerException, riempimento SubList, rimozione di un 
     * elemento nullo { null }, controllo corretto lancio 
     * NullPointerException. 
     */
    @Test
    public void subListRemoveTestExc() {

        System.out.println("-- subListRemoveTestExc(Object o) --\n");

        HList l = list.subList(0, 0);
        Integer i = null;
        assertThrows(NullPointerException.class,
                    () -> {l.remove(i);});
        fillList();
        HList l2 = list.subList(2, 5);
        assertThrows(NullPointerException.class,
                    () -> {l2.remove(i);});
                 
        printSubList(l);
    }

    /**
     * creazione di una Collection di due elementi entrambi presenti 
     * nella lista, riempimento lista, creazione SubList, rimozione 
     * della Collection dalla SubList, controllo corretta diminuzione 
     * della dimensione della SubList. Aggiunta di un elemento non 
     * presente nella SubList alla Collection, pulizia lista e nuovo 
     * riempimento di essa, rimozione della Collection dalla SubList 
     * e controllo corretta diminuzione della dimensione della SubList. 
     */
    @Test
    public void subListRemoveAllTest() {

        System.out.println("-- subListRemoveAllTest(HCollection c) --\n");

        ListAdapter l = new ListAdapter();
        l.add(12);
        l.add(15);
        HCollection col = (HCollection) l;
        fillList();
        HList li = list.subList(2, 5);

        li.removeAll(col);
        assertEquals(2, li.size());
        l.add(25);
        list.clear();
        fillList();
        col = (HCollection) l;
        li.removeAll(col);
        assertEquals(1, li.size());

        printSubList(li);
    }

    /**
     * riempimento lista, creazione SubList, creazione Collection nulla, 
     * controllo che la rimozione di tale Collection dalla SubList 
     * restituisca NullPointerException. 
     */
    @Test
    public void subListRemoveAllTestExc() {
        
        System.out.println("-- subListRemoveAllTestExc(HCollection c) --\n");

        fillList();
        HList l = list.subList(2, 5);
        HCollection col1 = null;
        assertThrows(NullPointerException.class,
                    () -> {l.removeAll(col1);});

        printSubList(l);

    }

    /**
     * creazione di una Collection di due elementi entrambi presenti nella SubList, 
     * riempimento lista, creazione SubList, utilizzo del metodo 
     * retainAll(), controllo corretta diminuzione della dimensione della 
     * SubList. Aggiunta di un elemento non presente nella SubList alla 
     * Collection, pulizia lista e nuovo riempimento di essa, utilizzo del 
     * metodo retainAll() e controllo corretta diminuzione della 
     * dimensione della SubList. 
     */
    @Test
    public void subListRetainAllTest() {

        System.out.println("-- subListRetainAllTest(HCollection c) --\n");

        ListAdapter l = new ListAdapter();
        l.add(12);
        l.add(15);
        HCollection col = (HCollection) l;
        fillList();
        HList li = list.subList(2, 5);

        li.retainAll(col);
        assertEquals(1, li.size());
        list.clear();
        fillList();
        l.add(25);
        col = (HCollection) l;
        li.retainAll(col);
        assertEquals(1, li.size());

        printSubList(l);
    }


    /**
     * creazione Collection nulla, riempimento lista, creazione SubList, 
     * controllo che l’utilizzo di retainAll() con tale Collection 
     * dalla SubList restituisca NullPointerException. 
     */
    @Test
    public void subListRetainAllTestExc() {
        
        System.out.println("-- subListRetainAllTestExc(HCollection c) --\n");

        fillList();
        HList l = list.subList(2, 5);
        HCollection col1 = null;
        assertThrows(NullPointerException.class,
                    () -> {l.retainAll(col1);});

        printSubList(l);

    }

    /**
     * creazione di una SubList vuota, controllo se con la SubList vuota 
     * il metodo size() restituisce 0, riempimento lista, controllo 
     * tramite size() che la dimensione della SubList corrisponda a 3. 
     */
    @Test
    public void subListSizeTest() {

        System.out.println("-- subListSizeTest() --\n");

        HList l = list.subList(0, 0);
        assertEquals(0, l.size());
        list.clear();
        fillList();
        HList l2 = list.subList(2, 5);
        assertEquals(3, l2.size());

        printSubList(l);
    }

    /**
     * riempimento lista, creazione SubList, creazione di un array 
     * tramite il metodo toArray(), controllo che tutti gli elementi 
     * dell’array coincidano in valore e posizione a tutti gli elementi 
     * della SubList originaria. 
     */
    @Test 
    public void subListToArrayTest() {

        System.out.println("-- subListToArrayTest() --\n");

        fillList();
        HList l = list.subList(2, 5);
        Object[] arry = l.toArray();

        for (int i = 0; i < arry.length; i++) {
            assertEquals(l.get(i), arry[i]);
        }

        printSubList(l);
    }

    /**
     * riempimento lista, creazione SubList, creazione di un array 
     * a due elementi e utilizzo del metodo toArray() passando come 
     * parametro l’array creato, controllo che tutti gli elementi 
     * dell’array coincidano in valore e posizione a tutti gli elementi 
     * della SubList originaria. Creazione di un array a venti elementi 
     * e utilizzo del metodo toArray() passando come parametro l’array 
     * creato, controllo che tutti gli elementi dell’array coincidano 
     * in valore e posizione a tutti gli elementi della SubList 
     * originaria. 
     */
    @Test
    public void subListToArrayObjTest() {

        System.out.println("-- subListToArrayObjTest(Object[] a) --\n");

        fillList();
        HList l = list.subList(2, 5);
        Object[] obj = {2, 3};
        Object[] res = l.toArray(obj);

        for (int i = 0; i < res.length; i++) {
            assertEquals(l.get(i), res[i]);
        }

        Object[] obj2 = new Object[20];
        Object[] res2 = l.toArray(obj2);

        for (int i = 0; i < l.size(); i++) {
            assertEquals(l.get(i), res2[i]);
        }

        printSubList(l);
    }


    /**
     * riempimento lista, creazione SubList, 
     * creazione di un array nullo e passaggio di esso tramite il 
     * metodo toArray(), controllo corretto lancio NullPointerException. 
     */
    @Test
    public void subListToArrayObjTestExc() {

        System.out.println("-- subListToArrayObjTestExc(Object[] a) --\n");
    
        fillList();
        HList l = list.subList(2, 5);
        Object[] obj = null;

        assertThrows(NullPointerException.class,
                    () -> {l.toArray(obj);});

        printSubList(l);
    }


    /**
     * riempimento lista, creazione SubList, utilizzo del metodo 
     * indexOf() e controllo dalla corretta restituzione dell’indice 
     * corrispondente. 
     */
    @Test
    public void subListIndexOfTest() {

        System.out.println("-- subListIndexOfTest(Object o) --\n");

        fillList();
        HList l = list.subList(2, 5);
        assertEquals(0, l.indexOf(12));

        printSubList(l);

    }


    /**
     * riempimento lista, creazione SubList, tentativo di utilizzo 
     * del metodo indexOf passando un oggetto nullo, viene 
     * successivamente atteso il lancio dell’eccezione 
     * NullPointerException. 
     */
    @Test
    public void subListIndexOfTestExc() {

        System.out.println("-- subListIndexOfTestExc(Object o) --\n");

        fillList();
        HList l = list.subList(2, 5);
        assertThrows(NullPointerException.class,
                    () -> {l.indexOf(null);});

        printSubList(l);
    }


    /**
     * riempimento lista, creazione SubList, inserimento di altri due 
     * elementi alla sua coda, utilizzo del metodo lastIndexOf() e 
     * controllo dalla corretta restituzione dell’indice corrispondente. 
     * Vengono cercati due oggetti, uno presente effettivamente 
     * all’interno della SubList e uno non presente, in modo tale da 
     * verificare anche la corretta restituzione del valore -1. 
     */
    @Test
    public void subListLastIndexOfTest() {

        System.out.println("-- subListLastIndexOfTest(Object o) --\n");

        fillList();
        HList l = list.subList(2, 5);
        l.add(25);
        l.add(30);
        assertEquals(5, list.lastIndexOf(25));
        assertEquals(-1, list.lastIndexOf(99));

        printSubList(l);
    }


    /**
     * riempimento lista, creazione SubList tentativo di utilizzo del 
     * metodo lastIndexOf() passando un oggetto nullo, viene 
     * successivamente atteso il lancio dell’eccezione NullPointerException. 
     */
    @Test
    public void subListLastIndexOfTestExc() {

        System.out.println("-- subListLastIndexOfTestExc(Object o) --\n");

        fillList();
        HList l = list.subList(2, 5);

        assertThrows(NullPointerException.class,
                    () -> {l.lastIndexOf(null);});

        printSubList(l);
    }


    // - - - - - - - - - - - - - - - - - //


    /**
     * riempimento lista.
     */
    private void fillList() {
        
        for (int i = 0; i < start_test_size; i++)
            list.add(i + 10);
    }

    /**
     * stampa List.
     */
    private void printList() {

        System.out.println("The list is in the following condition");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Index: " + i + " Value: " + list.get(i));  
        }
        System.out.println("Size: " + list.size());
        System.out.println();
    }

    /**
     * stampa SubList
     */
    private void printSubList(HList l) {

        System.out.println("The list is in the following condition");
        for (int i = 0; i < l.size(); i++) {
            System.out.println("Index: " + i + " Value: " + l.get(i));  
        }
        System.out.println("Size: " + l.size());
        System.out.println();
    }




}