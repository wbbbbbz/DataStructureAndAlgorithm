package datastructure;

public class BSTSet<E extends Comparable<E>> implements Set<E> {

    private BinarySearchTree<E, Object> data;

    public BSTSet() {
        data = new BinarySearchTree<>();
    }

    @Override
    public void add(E e) {
        data.add(e, null);
    }

    @Override
    public void remove(E e) {
        data.remove(e);
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