package datastructure.test;

import java.util.ArrayList;

import datastructure.FileOperation;
import datastructure.RedBlackTree;

public class RedBlackTreeTest {

    public static void main(String[] args) {
        redBlackTreeTest();
    }

    public static void redBlackTreeTest() {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("testfiles\\pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            RedBlackTree<String, Integer> map = new RedBlackTree<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }

        System.out.println();
    }
}