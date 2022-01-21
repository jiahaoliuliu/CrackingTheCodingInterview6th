package com.jiahaoliuliu.chapter1.arraysandstrings;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * There are three types of edits that can be performed on string:
 * - insert a character
 * - remove a character
 * - or replace a character.
 * Given two arrays, write a function to check if they are one edit (or zero edits) away
 * Example:
 * - pale, ple   -> true
 * - pales, pale -> true
 * - pale, bale  -> true
 * - pale, bake  -> false
 *
 * Hints:
 * - 23: Start with the easy thing. Can you check each of the conditions separately
 * - 97: What is the relationship between the "insert character" option and the "remove character" option? Do these
 *       need to tweak the return values a bit.
 * - 130: Can you do all three checks in a single pass?
 *
 */
public class Q5OneAway {

    private boolean oneWay(String left, String right) {
        if (left.length() == right.length()) {
            return checkForReplacement(left, right);
        } if (left.length() - right.length() == 1) {
            return checkForInsertion(right, left);
        } else if (right.length() - left.length() == 1) {
            return checkForInsertion(left, right);
        }

        return false;
    }

    private boolean checkForReplacement(String left, String right) {
        if (left.isEmpty()) return true;
        if (left.length() != right.length()) return false;

        boolean foundDifference = false;
        for (int i = 0; i < left.length(); i++) {
            if (left.charAt(i) != right.charAt(i)) {
                // If the difference is already found
                if (foundDifference) {
                    return false;
                } else {
                    foundDifference = true;
                }
            }
        }

        // If there is no difference, return true
        return true;
    }

    private boolean checkForInsertion(String shortString, String longString) {
        // If left is empty, then any char from right is ok
        if (shortString.isEmpty()) return longString.length() == 1;

        boolean foundInsertion = false;
        int shortIndex = 0;
        int longIndex = 0;
        while (shortIndex < shortString.length()) {
            if (shortString.charAt(shortIndex) != longString.charAt(longIndex)) {
                if (foundInsertion) {
                    return false;
                } else {
                    foundInsertion = true;
                    longIndex++;
                }
            } else {
                shortIndex++;
                longIndex++;
            }
        }

        return true;
    }

    /**
     * Book solution 1
     * Merging insertion with removal
     */
    private boolean bookSol1OneEditAway(String first, String second) {
        if (first.length() == second.length()) {
            return oneEditReplace(first, second);
        } else if (first.length() + 1 == second.length()) {
            return oneEditInsert(first, second);
        } else if (first.length() - 1 == second.length()) {
            return oneEditInsert(second, first);
        }

        return false;
    }

    private boolean oneEditReplace(String s1, String s2) {
        boolean foundDifference = false;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (foundDifference) {
                    return false;
                }
                foundDifference = true;
            }
        }
        return true;
    }

    /* Check if you can insert a character into s1 to make s2. */
    boolean oneEditInsert(String s1, String s2) {
        int index1 = 0;
        int index2 = 0;
        while (index2 < s2.length() && index1 < s1.length()) {
            if (s1.charAt(index1) != s2.charAt(index2)) {
                if (index1 != index2) {
                    return false;
                }
                index2++;
            } else {
                index1++;
                index2++;
            }
        }
        return true;
    }

    /**
     * Book solution 2 - Merging the edits
     */
    private boolean bookSol2OneEditAway(String first, String second) {
        /* length checks. */
        if (Math.abs(first.length() - second.length()) > 1) {
            return false;
        }

        /* Get shorter and longer string */
        String s1 = first.length() < second.length() ? first : second;
        String s2 = first.length() < second.length() ? second : first;

        int index1 = 0;
        int index2 = 0;
        boolean foundDifference = false;
        while (index2 < s2.length() && index1 < s1.length()) {
            if (s1.charAt(index1) != s2.charAt(index2)) {
                /* Ensure that this is the first difference found. */
                if (foundDifference) return false;
                foundDifference = true;

                if (s1.length() == s2.length()) {
                    index1++;
                }
            } else {
                index1++; // If matching, move shorter pointer
            }
            index2++; // Always move poiner for longer string
        }
        return true;
    }

    @Test
    public void test1() {
        // Given
        String left = "pale";
        String right = "ple";

        // When
        boolean result = bookSol2OneEditAway(left, right);

        // Then
        assertTrue(result);
    }

    @Test
    public void test2() {
        // Given
        String left = "pales";
        String right = "pale";

        // When
        boolean result = bookSol2OneEditAway(left, right);

        // Then
        assertTrue(result);
    }

    @Test
    public void test3() {
        // Given
        String left = "pale";
        String right = "bale";

        // When
        boolean result = bookSol2OneEditAway(left, right);

        // Then
        assertTrue(result);
    }

    @Test
    public void test4() {
        // Given
        String left = "pale";
        String right = "bake";

        // When
        boolean result = bookSol2OneEditAway(left, right);

        // Then
        assertFalse(result);
    }
}
