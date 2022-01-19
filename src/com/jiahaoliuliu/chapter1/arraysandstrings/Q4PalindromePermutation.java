package com.jiahaoliuliu.chapter1.arraysandstrings;

import org.junit.Test;

import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Given a string, write a function to check if it is a permutation of a palindrome. A palindrome is a word or phrase
 * that is the same forward and backwards. A permutation is a rearrangement of letters. The palindrome does not need
 * to be limited to just dictionary words.
 * Example:
 * - input: Tact Coa
 * - Output: True (permutations: "taco cat", "atco cta", etc.)
 * Hints:
 * - 106: You do not have to -and should not - generate all permutations. This would be very inefficient
 * - 121: What characteristics would a string that is a permutation of a palindrome have?
 * - 134: Have you tried a hash table? You should be able to get this down to O(N) time.
 * - 136: Can you reduce the space usage by using a bit vector?
 */
public class Q4PalindromePermutation {

    /**
     * Assumptions: Uppercase and lower case does not matter
     * @param input A normal string
     * @return true if it is a combination of palindromes
     */
    private boolean isPalindromePermutation(String input) {
        Map<Character, Integer> dataSet = new Hashtable<>();

        // Looping through the string, skipping the empty space
        for (char character: input.toLowerCase(Locale.ROOT).toCharArray()) {
            if (character == ' ') continue;
            if (dataSet.containsKey(character)) {
                dataSet.remove(character);
            } else {
                dataSet.put(character, 1);
            }
        }
        return dataSet.size() <= 1;
    }

    private boolean isPalindromePermutationVectorArray(String input) {
        int checker = 0;
        for (char character: input.toLowerCase(Locale.ROOT).toCharArray()) {
            if (character == ' ') continue;
            // Using XOR, which results 1 if the bits are different and 0 if they are the same
            checker = checker ^ (1 << character - 'a');
        }

        return checker == 0 || isPowerOfTwo(checker);
    }

    private boolean isPowerOfTwo(int n) {
        return (int)(Math.ceil((Math.log(n) / Math.log(2))))
                == (int)(Math.floor(((Math.log(n) / Math.log(2)))));
    }

    /**
     * Solution 1 - Using hash table to count
     */
    private boolean bookSol1IsPermutationOfPalindrome(String phrase) {
        int[] table = buildCharFrequencyTable(phrase);
        return checkMaxOneOdd(table);
    }

    /* Count how many times each character appears */
    private int[] buildCharFrequencyTable(String phrase) {
        int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];
        for (char c : phrase.toCharArray()) {
            int x = getCharNumber(c);
            if (x != -1) {
                table[x]++;
            }
        }
        return table;
    }

    /* Map each character to a number. a-> 0, b -> 1, c -> 2, etc.
     * This is case insensitive. Non-letter characters map to -1. */
    private int getCharNumber(Character c) {
        int a = Character.getNumericValue('a');
        int z = Character.getNumericValue('z');
        int val = Character.getNumericValue(c);
        if (a <= val && val <= z) {
            return val - a;
        }

        return -1;
    }

    /* Check that no more than one character has an odd count */
    private boolean checkMaxOneOdd(int[] table) {
        boolean foundOdd = false;
        for (int count: table) {
            if (count % 2 == 1) {
                if (foundOdd) {
                    return false;
                } else {
                    foundOdd = true;
                }
            }
        }

        return true;
    }

    /**
     * Solution 2 - Tweak a bit
     */
    private boolean bookSol2IsPermutationOfPalindrome(String phrase) {
        int countOdd = 0;
        int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];
        for (char c : phrase.toCharArray()) {
            int x = getCharNumber(c);
            if (x != -1) {
                table[x]++;
                if (table[x] % 2 == 1) {
                    countOdd++;
                } else {
                    countOdd--;
                }
            }
        }

        return countOdd <= 1;
    }

    /**
     * Solution 3 - Using bit vector
     */
    private boolean bookSol3IsPermutationOfPalindrome(String phrase) {
        int bitVector = createBitVector(phrase);
        return bitVector == 0 || checkExactlyOneBitSet(bitVector);
    }

    /* Create a bit vector for the string. For each letter with value i, toggle the ith bit. */
    private int createBitVector(String phrase) {
        int bitVector = 0;
        for (char c: phrase.toCharArray()) {
            int x = getCharNumber(c);
            bitVector = toggle(bitVector, x);
        }
        return bitVector;
    }

    /* Toggle the ith bit in the integer */
    private int toggle(int bitVector, int index) {
        if (index < 0) return bitVector;

        int mask = 1 << index;
        if ((bitVector & mask) == 0) {
            bitVector |= mask;
        } else {
            bitVector &= ~mask;
        }
        return bitVector;
    }

    /* Check that exactly one bit is set by subtracting one from the integer and ANDing it with the origian integer */
    private boolean checkExactlyOneBitSet(int bitVector) {
        return (bitVector & (bitVector - 1)) == 0;
    }

    @Test
    public void test1() {
        // Given
        String input = "Tact Coa";

        // When
        boolean result = bookSol3IsPermutationOfPalindrome(input);

        // Then
        assertTrue(result);
    }

    @Test
    public void test2() {
        // Given
        String input = "Size string";

        // When
        boolean result = bookSol3IsPermutationOfPalindrome(input);

        // Then
        assertFalse(result);
    }

}
