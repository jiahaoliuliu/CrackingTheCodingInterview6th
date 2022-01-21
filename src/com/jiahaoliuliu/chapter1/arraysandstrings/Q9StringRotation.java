package com.jiahaoliuliu.chapter1.arraysandstrings;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Assume you have a method isSubstring which checks if one word is a substring of another.
 * Given two strings, s1 and s2, write code to check if s2 is a rotation of s1 using only one
 * call to isSubstring(e.g. "waterbottle" is a rotation of "erbottlewat").
 * Hints:
 * - 34: If a string is a rotation of another, then it's a rotation at a particular point. For
 *       example, a rotation of waterbottle at character 3 means cutting waterbottle at character 3
 *       and putting the right half (erbottle) before the left half(wat)
 * - 88: We are essentially asking if there's a way of splitting the first string into two parts, x
 *       and y, such that the first string is xy and the second string is yx. For example, x = wat
 *       and y = erbottle. The first string is xy = waterbottle. The second string is yx = erbottlewat
 * - 104: Think about earlier hint. The think about what happens when you concatenate erbottlewat to
 *        itself. You get erbottlewaterbottlewat
 */
public class Q9StringRotation {

    private boolean isStringRotation(String s1, String s2) {
        // Special cases
        if (s1.length() != s2.length()) return false;
        if (s1.length() <= 1) return true;

        String doubleS2 = s2 + s2;
        return isSubstring(doubleS2, s1);
    }

    private boolean isSubstring(String s1, String s2) {
        return s1.contains(s2);
    }

    /**
     * Book solution 1
     * s1 = xy
     * s2 = yx
     * isSubstring(xyxy, yx)?
     * if complexity(isSubstring) = O(A+B)
     * then complexity(isRotation) = O(N)
     */
    private boolean bookSol1IsRotation(String s1, String s2) {
        int len = s1.length();
        /* Check that s1 and s2 are equal length and not empty */
        if (len == s2.length() && len > 0) {
            /* Concatenate s1 and s1 within new buffer */
            String s1s1 = s1 + s1;
            return isSubstring(s1s1, s2);
        }

        return false;
    }

    @Test
    public void test1() {
        // Given
        String s1 = "waterbottle";
        String s2 = "erbottlewat";

        // When
        boolean result = bookSol1IsRotation(s1, s2);

        // Then
        assertTrue(result);
    }

    @Test
    public void test2() {
        // Given
        String s1 = "waterbottle";
        String s2 = "erboltlewat";

        // When
        boolean result = bookSol1IsRotation(s1, s2);

        // Then
        assertFalse(result);
    }

}
