package datastructure;

public class LinkedListSet<E> implements Set<E> {

    private LinkedList<E> data;

    public LinkedListSet() {
        data = new LinkedList<E>();
    }

    @Override
    public void add(E e) {
        if (!contains(e)) {
            data.addFirst(e); // 时间复杂度低！
        }
    }

    @Override
    public void remove(E e) {
        data.removeElement(e);
    }

    @Override
    public boolean contains(E e) {
        return data.contains(e);
    }

    @Override
    public int getSize() {
        return data.getSize();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

}