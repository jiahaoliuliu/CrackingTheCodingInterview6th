package com.jiahaoliuliu.chapter1.arraysandstrings;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Implement a method to perform basic string compression using the counts of repeated characters.
 * For example, the string aabcccccaaa would become a2b1c5a3.
 * If the "compressed" string would not become smaller than the original string, your method should
 * return the original string. YOu can assume the string has only uppercase and lowercase letters (a - z).
 * Hint:
 * - 92: Do the easy thing first. Compress the string, then compare the lengths.
 * - 110: Be careful that you aren't repeatedly concatenating strings together. This can be very inefficient.
 */
public class Q6StringCompression {

    private String stringCompression(String word) {
        char currentChar = word.charAt(0);
        int occurrences = 1;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(currentChar);
        for (int position = 1; position < word.length(); position++) {
            char nextChar = word.charAt(position);
            if (nextChar == currentChar) {
                occurrences++;
            } else {
                stringBuilder.append(occurrences);
                stringBuilder.append(nextChar);
                occurrences = 1;
                currentChar = nextChar;
            }
        }

        // Attach the last occurrences
        stringBuilder.append(occurrences);

        // Instead, it could use stringBuild.length()
        String result = stringBuilder.toString();
        return result.length() > word.length()? word : result;
    }

    /**
     * Book solution 1
     */
    private String bookSol1Compress(String str) {
        StringBuilder compressed = new StringBuilder();
        int countConsecutive = 0;
        for (int i = 0; i < str.length(); i++) {
            countConsecutive++;

            /* If next character is different from current, append this char to result. */
            if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i+1)) {
                compressed.append(str.charAt(i));
                compressed.append(countConsecutive);
                countConsecutive = 0;
            }
        }
        return compressed.length() < str.length() ? compressed.toString() : str;
    }

    /**
     * Book solution 2
     * Checking in advance
     */
    private String bookSol2Compress(String str) {
        /* Check final length and return input string if it would be longer. */
        int finalLength = countCompression(str);
        if (finalLength >= str.length()) return str;

        StringBuilder compressed = new StringBuilder(finalLength); // initial capacity
        int countConsecutive = 0;
        for (int i = 0; i < str.length(); i++) {
            countConsecutive++;

            /* If next character is different from current, append this char to result. */
            if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
                compressed.append(str.charAt(i));
                compressed.append(countConsecutive);
                countConsecutive = 0;
            }
        }
        return compressed.toString();
    }

    int countCompression(String str) {
        int compressedLength = 0;
        int countConsecutive = 0;
        for (int i = 0; i < str.length(); i++) {
            countConsecutive++;

            /* If next character is different from current, increase the length. */
            if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
                compressedLength += 1 + String.valueOf(countConsecutive).length();
                countConsecutive = 0;
            }
        }
        return compressedLength;
    }

    @Test
    public void test1() {
        // Given
        String input = "aabcccccaaa";

        // When
        String result = bookSol2Compress(input);

        // Then
        assertEquals("a2b1c5a3", result);
    }

    @Test
    public void test2() {
        // Given
        String input = "aabcaaa";

        // When
        String result = bookSol2Compress(input);

        // Then
        assertEquals("aabcaaa", result);
    }

}
