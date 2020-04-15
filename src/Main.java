import datastructure.Array;

public class Main {

    public static void main(String[] args) {
        Array<Integer> intArray = new Array<>(20);
        for (int i = 0; i < 21; i++) {
            intArray.addLast(i);
        }
        System.out.println(intArray);
        intArray.insert(1, 100);
        System.out.println(intArray);

        intArray.remove(2);
        System.out.println(intArray);

        intArray.removeElement(2);
        System.out.println(intArray);

        intArray = new Array<>(1);
        for (int i = 0; i < 1; i++) {
            intArray.addLast(i);
        }
        System.out.println(intArray);
        intArray.remove(0);
        System.out.println(intArray);
    }
}