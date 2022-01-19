package com.jiahaoliuliu.chapter1.arraysandstrings;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Given two strings, write a method to decide if one is a permutation of the other
 *
 * Hints:
 * - 1: Describe what it means for two strings to be permutation of eac other. Now, look at that definition you provided.
 *      Can you check these Strings against that definition?
 * - 84: There is one solution that is O(N log N) time. Another solution uses some space, but O(N) time
 * - 122: Could a hashtable be useful?
 * - 131: Two strings that are permutations should have the same characters, but in different orders. can you make the
 *        orders the same?
 */
public class Q2CheckPermutation {

    private boolean arePermutations(String word1, String word2) {
        // Simple checks
        if (word1.length() != word2.length()) return false;

        Map<Character, Integer> dataSet = new HashMap <>();
        for (char character: word1.toCharArray()) {
            int occurrences = 1;
            if (dataSet.containsKey(character)) {
                occurrences = dataSet.get(character);
                occurrences++;
            }

            dataSet.put(character, occurrences);
        }

        for (char character: word2.toCharArray()) {
            if (!dataSet.containsKey(character)) {
                return false;
            }

            int occurrences = dataSet.get(character);
            occurrences--;
            if (occurrences < 0) return false;
            if (occurrences == 0) {
                dataSet.remove(character);
            } else {
                dataSet.put(character, occurrences);
            }
        }
        return dataSet.isEmpty();
    }

    private boolean arePermutationsBySorting(String word1, String word2) {
        if (word1.length() != word2.length()) return false;
        char[] word1CharArray = word1.toCharArray();
        char[] word2CharArray = word2.toCharArray();
        Arrays.sort(word1CharArray);
        Arrays.sort(word2CharArray);

        return Arrays.equals(word1CharArray, word2CharArray);
    }

    /**
     * Book solution 1
     * This is not optimal but it is clean, simple and easy to understand
     */
    private String sort(String s) {
        char[] content = s.toCharArray();
        Arrays.sort(content);
        return new String(content);
    }

    boolean permutationBookSol1(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        return sort(s).equals(sort(t));
    }

    /**
     * Book solution 2
     */
    boolean permutationBookSol2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] letters = new int[128]; // Assumption ASCII

        char[] sArray = s.toCharArray();
        for (char c: sArray) {
            letters[c]++;
        }

        for (int i = 0; i < t.length(); i++) {
            // Java automatically converts char to ASCII positions. ie. 'a' is now 97
            int c = (int)t.charAt(i);
            letters[c]--;
            if (letters[c] < 0) {
                return false;
            }
        }

        return true;
    }

    @Test
    public void test1() {
        // Given
        String word1 = "baba";
        String word2 = "aabb";

        // When
        boolean result = permutationBookSol2(word1, word2);

        // Then
        assertTrue(result);
    }

    @Test
    public void test2() {
        // Given
        String word1 = "baba";
        String word2 = "aab";

        // When
        boolean result = permutationBookSol2(word1, word2);

        // Then
        assertFalse(result);
    }

}
