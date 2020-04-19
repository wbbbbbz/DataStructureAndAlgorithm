package datastructure.test;

import datastructure.LinkedList;

public class LinkedListTest {

    public static void main(String[] args) {
        linkedListTest();
    }

    // 测试链表
    public static void linkedListTest() {

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

}