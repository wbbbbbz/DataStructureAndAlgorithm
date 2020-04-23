package datastructure.test;

import java.util.ArrayList;

import datastructure.AVLMap;
import datastructure.BSTMap;
import datastructure.FileOperation;
import datastructure.LinkedListMap;
import datastructure.Map;

public class MapTest {

    public static void main(String[] args) {
        // linkedListMapTest();
        // bstMapTest();

        Map<String, Integer> bstMap = new BSTMap<>();
        Map<String, Integer> linkedListMap = new LinkedListMap<>();
        Map<String, Integer> avlMap = new AVLMap<>();
        String fileName = "pride-and-prejudice.txt";
        double bstMapTime = testMapTime(bstMap, fileName);
        double linkedListMapTime = testMapTime(linkedListMap, fileName);
        double avlMapTime = testMapTime(avlMap, fileName);
        System.out.println("bstMapTime: " + bstMapTime + " s\n");
        System.out.println("linkedListMapTime: " + linkedListMapTime + " s\n");
        System.out.println("AVLMapTime: " + avlMapTime + " s\n");
    }

    public static void linkedListMapTest() {
        System.out.println("Pride and Prejudice");
        ArrayList<String> words1 = new ArrayList<>();
        FileOperation.readFile("pride-and-prejudice.txt", words1);
        System.out.println("Total words: " + words1.size());

        LinkedListMap<String, Integer> map1 = new LinkedListMap<>();
        for (String word : words1)
            if (map1.contains(word))
                map1.set(word, map1.get(word) + 1);
            else
                map1.add(word, 1);
        System.out.println("Total different words: " + map1.getSize());
        System.out.println("Frequency of pride: " + map1.get("pride"));
        System.out.println("Frequency of prejudice: " + map1.get("prejudice"));
    }

    public static void bstMapTest() {
        System.out.println("Pride and Prejudice");
        ArrayList<String> words1 = new ArrayList<>();
        FileOperation.readFile("pride-and-prejudice.txt", words1);
        System.out.println("Total words: " + words1.size());

        BSTMap<String, Integer> map1 = new BSTMap<>();
        for (String word : words1)
            if (map1.contains(word))
                map1.set(word, map1.get(word) + 1);
            else
                map1.add(word, 1);
        System.out.println("Total different words: " + map1.getSize() + "\n");
        System.out.println("Frequency of pride: " + map1.get("pride") + "\n");
        System.out.println("Frequency of prejudice: " + map1.get("prejudice") + "\n");
    }

    // 测试使用map读取fileName并计入映射的操作。
    public static double testMapTime(Map<String, Integer> map, String fileName) {

        ArrayList<String> words = new ArrayList<>();
        FileOperation.readFile(fileName, words);

        long startTime = System.nanoTime();

        for (String word : words)
            if (map.contains(word))
                map.set(word, map.get(word) + 1);
            else
                map.add(word, 1);
        long endTime = System.nanoTime();

        System.out.println(fileName);
        System.out.println("Total words: " + words.size());
        System.out.println("Total different words: " + map.getSize());
        System.out.println("Frequency of pride: " + map.get("pride"));
        System.out.println("Frequency of prejudice: " + map.get("prejudice") + "\n");
        return (endTime - startTime) / 1000000000.0;
    }

}