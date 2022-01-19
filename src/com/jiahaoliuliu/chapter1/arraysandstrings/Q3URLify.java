package com.jiahaoliuliu.chapter1.arraysandstrings;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Write a method to replace all spaces in a string with '%20' You may assume that the string has sufficient space at the
 * end to hold additional characters, and that you are given the "true" length of the string.
 * Note: If implementing in Java, please use a character array so that you can perform this operation in place
 * Example:
 * input:    "Mr John Smith       ", 13
 * Output:   "Mr%20John%20S%20mith"
 * Hints:
 * - 53: It's often easiest to modify strings by going from the end of the string to the beginning
 * - 118: You might find you need to know the number of spaces. Can you count them?
 */
public class Q3URLify {

    /**
     * Assume ASCII
     * 1. Count the number of spaces
     * 2. Build the Char array
     * 3. Loop the string from the last position to the first
     * @param word
     * @return The same character, but replaced " " with %20
     */
    private String urlify(String word) {
        char[] charsArrayReplaced = new char[word.length()];
        int position = 0;
        int wordPosition = 0;
        while (position < charsArrayReplaced.length) {
            if (word.charAt(wordPosition) != ' ') {
                charsArrayReplaced[position++] = word.charAt(wordPosition);
            } else {
                charsArrayReplaced[position++] = '%';
                charsArrayReplaced[position++] = '2';
                charsArrayReplaced[position++] = '0';
            }
            wordPosition++;
        }

        return new String(charsArrayReplaced);
    }

    /**
     * Replacing all the ' ' in str by '%20'
     * @param str The str to be replaced
     * @param trueLength The true length of the str without counting all the spaces needed at the end
     * @return the original str but with all the ' ' replaced by '%20'
     */
    private void urlify(char[] str, int trueLength) {
        // number of spaces
        int numberOfSpaces = 0;
        for (int i = 0; i < trueLength; i++) {
            if (str[i] == ' ') {
                numberOfSpaces++;
            }
        }

        // Going through the char array from back
        int writePosition = trueLength - 1 + numberOfSpaces*2;
        int readPosition = trueLength - 1;
        while(readPosition >= 0) {
            if (str[readPosition] == ' ') {
                str[writePosition--] = '0';
                str[writePosition--] = '2';
                str[writePosition--] = '%';
            } else {
                str[writePosition--] = str[readPosition];
            }

            readPosition--;
        }
    }

    /**
     * Editing the string starting from the end and working backwards, because we do have extra buffet at the end.
     * The algorithm employs a two-scan approach
     * @param str
     * @param trueLength
     */
    private void bookSolReplaceSpaces(char[] str, int trueLength) {
        int spaceCount = 0, index, i = 0;
        for (i = 0; i <  trueLength; i++) {
            if (str[i] == ' ') {
                spaceCount++;
            }
        }

        index = trueLength + spaceCount * 2;
        if (trueLength < str.length) str[trueLength] = '\0'; // End array
        for (i = trueLength - 1; i >=0; i--) {
            if (str[i] == ' ') {
                str[index - 1] = '0';
                str[index - 2] = '2';
                str[index - 3] = '%';
                index = index - 3;
            } else {
                str[index - 1] = str[i];
                index --;
            }
        }

    }

    @Test
    public void test1() {
        // Given
        String input = "Mr John Smith    ";

        // When
        String result = urlify(input);

        // Then
        assertEquals("Mr%20John%20Smith", result);
    }

    @Test
    public void test2() {
        // Given
        char[] input = "Mr John Smith        ".toCharArray();
        int trueLength = 13;

        // When
        bookSolReplaceSpaces(input, trueLength);

        // Then
        assertEquals("Mr%20John%20Smith", new String(input).trim());
    }
}
