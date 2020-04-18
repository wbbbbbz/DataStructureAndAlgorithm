import datastructure.Array;
import datastructure.ArrayQueue;
import datastructure.ArrayStack;
import datastructure.LoopQueue;

public class Main {

    public static void main(String[] args) {
        arrayTest();
        System.out.println("--------------------------------------");
        stackTest();
        System.out.println("--------------------------------------");
        queueTest();
        System.out.println("--------------------------------------");
        loopQueueTest();
        System.out.println("--------------------------------------");
    }

    public static void arrayTest() {
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


    public static void stackTest() {
        ArrayStack<Integer> intStack = new ArrayStack<>(20);
        for (int i = 0; i < 10; i++) {
            intStack.push(i);
            System.out.println(intStack);
            if (i % 3 == 2) {
                intStack.pop();
                System.out.println(intStack);
            }
        }
    }


    public static void queueTest() {
        ArrayQueue<Integer> intQueue = new ArrayQueue<>(10);
        for (int i = 0; i < 10; i++) {
            intQueue.enqueue(i);
            System.out.println(intQueue);
            if (i % 3 == 2) {
                intQueue.dequeue();
                System.out.println(intQueue);
            }
        }
    }

    
    public static void loopQueueTest() {
        LoopQueue<Integer> intQueue = new LoopQueue<>(10);
        for (int i = 0; i < 10; i++) {
            intQueue.enqueue(i);
            System.out.println(intQueue);
            if (i % 3 == 2) {
                intQueue.dequeue();
                System.out.println(intQueue);
            }
        }
    }


}