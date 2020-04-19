package datastructure.test;

import java.util.Random;

import datastructure.ArrayStack;
import datastructure.LinkedListStack;
import datastructure.Stack;

public class StackTest {

    public static void main(String[] args) {
        stackTest();
        System.out.println("--------------------------------------");

        linkedListStackTest();
        System.out.println("--------------------------------------");

        Stack<Integer> arrayStack = new ArrayStack<>();
        Stack<Integer> linkedListStack = new LinkedListStack<>();
        int opCount = 100000;
        double arrayStackTime = testStack(arrayStack, opCount);
        double linkedListStackTime = testStack(linkedListStack, opCount);
        System.out.println("arrayStackTime: " + arrayStackTime + " s");
        System.out.println("linkedListStackTime: " + linkedListStackTime + " s");

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
}