package datastructure.test;

import java.util.Random;

import datastructure.*;

public class QueueTest {

    public static void main(String[] args) {
        queueTest();
        System.out.println("--------------------------------------");

        loopQueueTest();
        System.out.println("--------------------------------------");

        LinkedListQueueTest();
        System.out.println("--------------------------------------");

        Queue<Integer> arrayQueue = new ArrayQueue<>();
        Queue<Integer> loopQueue = new LoopQueue<>();
        Queue<Integer> linkedListQueue = new LinkedListQueue<>();
        int opCount = 1000000;
        double arrayQueueTime = testQueue(arrayQueue, opCount);
        double loopQueueTime = testQueue(loopQueue, opCount);
        double linkedListQueueTime = testQueue(linkedListQueue, opCount);
        System.out.println("arrayQueueTime: " + arrayQueueTime + " s");
        System.out.println("loopQueueTime: " + loopQueueTime + " s");
        System.out.println("linkedListQueueTime: " + linkedListQueueTime + " s");
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