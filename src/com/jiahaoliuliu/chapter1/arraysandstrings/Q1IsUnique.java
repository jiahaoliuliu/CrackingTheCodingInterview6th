package com.jiahaoliuliu.chapter1.arraysandstrings;

import org.junit.Test;

import java.util.Hashtable;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Is Unique. Implement an algorithm to determine if a string has all unique characters.
 * What if you cannot use additional data structures?
 * Hints:
 * - 44: Try hash table
 * - 117: Could a bit vector be useful
 * - 132: Can you solve it in O(N log N) time? What might a solution like that look like?
 */
public class Q1IsUnique {

    public boolean containUniqueCharactersHasTable(String word) {
        Hashtable<Integer, Character> characterHashtable = new Hashtable<>();
        for (char character : word.toCharArray()) {
            int position = character - 'a';
            if (characterHashtable.containsKey(position)) {
                return false;
            } else {
                characterHashtable.put(position, character);
            }
        }

        return true;
    }

    /**
     * Time complexity = 128
     * Space complexity = 1
     * @param word
     * @return
     */
    public boolean containUniqueCharactersBooleanArray(String word) {
        boolean[] memory = new boolean[26];
        for (char character: word.toCharArray()) {
            int pos = character - 'a';
            if (memory[pos]) {
                return false;
            } else {
                memory[pos] = true;
            }
        }

        return true;
    }

    /**
     * Solution according to the book
     * Assume there are 256 characters (extended ASCII)
     * @param str
     * @return
     */
    public boolean isUniqueChars(String str) {
        if (str.length() > 256) return false;

        boolean[] charSet = new boolean[128];
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i);
            if (charSet[val]) { // Already found this char in String
                return false;
            }
            charSet[val] = true;
        }
        return true;
    }

    /**
     * Note: Because an integer on Java has 32 bits, this only works with lower cases.
     * @param word The word to be checked
     * @return True if all the characters on the word are unique
     *         False if any of the characters on the word repeat itself
     */
    public boolean containUniqueCharactersBitVector(String word) {
        int result = 0;
        for (char character: word.toCharArray()) {
            int pos = character - 'a';
            if ((result & (1 << pos)) > 0) return false;
            result |= (1 << pos);
        }
        return true;
    }

    @Test
    public void test1() {
        // Given
        String word = "abcde";

        // When
        boolean result = containUniqueCharactersBooleanArray(word);

        // Then
        assertTrue(result);
    }

    @Test
    public void test2() {
        // Given
        String word = "abcdea";

        // When
        boolean result = containUniqueCharactersBooleanArray(word);

        // Then
        assertFalse(result);
    }

}
