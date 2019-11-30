package Interfaces;

public interface MyListOfObjectsInterface {
    public boolean add(Object newEntry);
    public void add(int index, Object newEntry);
    public Object remove(int index);
    public void clear();
    public Object set(int index, Object anEntry); // like replace
    public Object get(int index); // like getEntry
    public boolean contains(Object anEntry);
    public int size(); // like getLength
    public boolean isEmpty();
}
