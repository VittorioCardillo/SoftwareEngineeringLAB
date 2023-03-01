package myAdapter;


public class Entry {

    private Object key;
    private Object value;

    public Entry() {

        this(0, 0);
    }

    public Entry(Object k, Object v) {

        key = k;
        value = v;
    }

    public Object getKey() { return key; }

    public Object getValue() { return value; }

    public int hashCode() {

        int hash = (getKey()==null   ? 0 : getKey().hashCode()) ^
        (getValue()==null ? 0 : getValue().hashCode());

        return hash;
    }

    public boolean equals(Entry obj) {

        if (getKey().equals(obj.getKey()) && getValue().equals(obj.getValue()))
            return true;

        return false;
    }



}