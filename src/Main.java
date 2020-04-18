import java.util.Random;

import datastructure.Array;
import datastructure.ArrayQueue;
import datastructure.ArrayStack;
import datastructure.LinkedList;
import datastructure.LinkedListQueue;
import datastructure.LinkedListStack;
import datastructure.LoopQueue;
import datastructure.Queue;
import datastructure.Stack;

public class Main {

    public static void main(String[] args) {
        // arrayTest();
        // System.out.println("--------------------------------------");

        // stackTest();
        // System.out.println("--------------------------------------");

        // queueTest();
        // System.out.println("--------------------------------------");

        // loopQueueTest();
        // System.out.println("--------------------------------------");

        // LinkedListTest();

        // linkedListStackTest();

        // Stack<Integer> arrayStack = new ArrayStack<>();
        // Stack<Integer> linkedListStack = new LinkedListStack<>();
        // int opCount = 100000;
        // double arrayStackTime = testStack(arrayStack, opCount);
        // double linkedListStackTime = testStack(linkedListStack, opCount);
        // System.out.println("arrayStackTime: " + arrayStackTime + " s");
        // System.out.println("linkedListStackTime: " + linkedListStackTime + " s");

        // LinkedListQueueTest();

        // // Queue<Integer> arrayQueue = new ArrayQueue<>();
        // Queue<Integer> loopQueue = new LoopQueue<>();
        // Queue<Integer> linkedListQueue = new LinkedListQueue<>();
        // int opCount = 1000000;
        // // double arrayQueueTime = testQueue(arrayQueue, opCount);
        // double loopQueueTime = testQueue(loopQueue, opCount);
        // double linkedListQueueTime = testQueue(linkedListQueue, opCount);
        // // System.out.println("arrayQueueTime: " + arrayQueueTime + " s");
        // System.out.println("loopQueueTime: " + loopQueueTime + " s");
        // System.out.println("linkedListQueueTime: " + linkedListQueueTime + " s");
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

    // 测试使用q运行opCount个入队与出队所需的时间。单位为秒
    public static double testQueue(Queue<Integer> q, int opCount) {

        Random random = new Random();
        long startTime = System.nanoTime();
        for (int i = 0; i < opCount; i++)
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        for (int i = 0; i < opCount; i++)
            q.dequeue();

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    // 测试链表
    public static void LinkedListTest() {

        LinkedList<Integer> linkedList = new LinkedList<>();

        for (int i = 0; i < 10; i++) {
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }

        linkedList.add(2, 666);
        System.out.println(linkedList);

        linkedList.remove(2);
        System.out.println(linkedList);
        linkedList.removeFirst();
        System.out.println(linkedList);
        linkedList.removeLast();
        System.out.println(linkedList);
    }

    public static void linkedListStackTest() {
        LinkedListStack<Integer> intStack = new LinkedListStack<>();
        for (int i = 0; i < 10; i++) {
            intStack.push(i);
            System.out.println(intStack);
            if (i % 3 == 2) {
                intStack.pop();
                System.out.println(intStack);
            }
        }
    }

    // 测试使用s运行opCount个入栈与出栈所需的时间。单位为秒
    public static double testStack(Stack<Integer> s, int opCount) {

        Random random = new Random();
        long startTime = System.nanoTime();
        for (int i = 0; i < opCount; i++)
            s.push(random.nextInt(Integer.MAX_VALUE));
        for (int i = 0; i < opCount; i++)
            s.pop();

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void LinkedListQueueTest() {
        LinkedListQueue<Integer> intQueue = new LinkedListQueue<>();
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