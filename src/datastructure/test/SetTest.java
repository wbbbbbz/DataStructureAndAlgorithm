package datastructure.test;

import java.util.ArrayList;

import datastructure.*;

public class SetTest {

    public static void main(String[] args) {
        setTest();

        Set<String> bstSet = new BSTSet<>();
        Set<String> linkedListSet = new LinkedListSet<>();
        Set<String> avlSet = new AVLSet<>();

        String fileName = "a-tale-of-two-cities.txt";
        double bstSetTime = testSet(bstSet, fileName);
        double linkedListSetTime = testSet(linkedListSet, fileName);
        double avlSetTime = testSet(avlSet, fileName);

        System.out.println("bstSetTime: " + bstSetTime + " s\n");
        System.out.println("linkedListSetTime: " + linkedListSetTime + " s\n");
        System.out.println("avlSetTime: " + avlSetTime + " s\n");

    }

    public static void setTest() {
        System.out.println("Pride and Prejudice");
        ArrayList<String> words1 = new ArrayList<>();
        FileOperation.readFile("pride-and-prejudice.txt", words1);
        System.out.println("Total words: " + words1.size());

        LinkedListSet<String> set1 = new LinkedListSet<>();
        for (String word : words1)
            set1.add(word);
        System.out.println("Total different words: " + set1.getSize());

        System.out.println();

        System.out.println("A Tale of Two Cities");
        ArrayList<String> words2 = new ArrayList<>();
        FileOperation.readFile("a-tale-of-two-cities.txt", words2);
        System.out.println("Total words: " + words2.size());

        LinkedListSet<String> set2 = new LinkedListSet<>();
        for (String word : words2)
            set2.add(word);
        System.out.println("Total different words: " + set2.getSize());
    }

    // 测试使用set读取fileName并计入集合的操作。
    public static double testSet(Set<String> set, String fileName) {

        ArrayList<String> words = new ArrayList<>();
        FileOperation.readFile(fileName, words);

        long startTime = System.nanoTime();

        for (String word : words)
            set.add(word);
        long endTime = System.nanoTime();

        System.out.println(fileName);
        System.out.println("Total words: " + words.size());
        System.out.println("Total different words: " + set.getSize() + "\n");
        return (endTime - startTime) / 1000000000.0;
    }

}