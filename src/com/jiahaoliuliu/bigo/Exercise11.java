package com.jiahaoliuliu.bigo;

import org.junit.Test;

public class Exercise11 {

    private static final int NUM_CHARS = 26;

    private void printSortedStrings(int remaining) {
        printSortedStrings(remaining, "");
    }

    private void printSortedStrings(int remaining, String prefix) {
        if (remaining == 0) {
            if (isInOrder(prefix)) {
                System.out.println(prefix);
            }
        } else {
            for (int i = 0; i < NUM_CHARS; i++) {
                char c = ithLetter(i);
                printSortedStrings(remaining - 1, prefix + c);
            }
        }
    }

    private boolean isInOrder(String s) {
        for (int i = 1; i < s.length(); i++) {
            int prev = ithLetter(s.charAt(i - 1));
            int curr = ithLetter(s.charAt(i));
            if (prev > curr) {
                return false;
            }
        }

        return true;
    }

    char ithLetter(int i) {
        return (char) (((int) 'a') + i);
    }

    @Test
    public void test1() {
        printSortedStrings(2);
    }
}
