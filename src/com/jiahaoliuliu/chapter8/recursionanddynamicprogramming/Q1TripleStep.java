package com.jiahaoliuliu.chapter8.recursionanddynamicprogramming;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 *
 * A child is running up a staircase with n steps and can hop either 1 step, 2 steps, or 3
 * steps at a time. Implement a method to count how many possible ways the child can run up
 * the stairs.
 * <ul>Hints:
 *     <li>152: Approach this from the top down. What is the very last hop the child made?</li>
 *     <li>178: If we know the number of paths to each of the steps before step 100, could we compute
 *              the number of steps to 100?</li>
 *     <li>217: We can compute the number of steps to 100 by the number of steps to 99, 98, 97.
 *              This corresponds to the child hopping 1, 2, or 3 steps at the end. Do we add those or
 *              multiply them? That is: Is it f(100) = f(99) + f(98) + f(97) or f(100) = f(99) * f(98)
 *              * f(97)?</li>
 *     <li>237: We multiply the values when it's "we do this then this."
 *              We add them when it's "we do this or this".</li>
 *     <li>262: What is the runtime of this method? Think carefully. Can you optimize it?</li>
 *     <li>359: Try memorization as a way to optimize an inefficient recursive program.</li>
 * </ul>
 * Related: https://leetcode.com/problems/climbing-stairs/solution/
 */
public class Q1TripleStep {
    /**
     * Initial analysis. For a given step, there is three ways he can reach there
     * - 1 step from (i - 1)th stair
     * - 2 step from (i - 2)th stair
     * - 3 step from (i - 3)th stair
     * Given d[n] is the number of ways he can reach to such step
     * d[n] = d[n - 1] + d[n - 2] + d[n - 3]
     *
     * @param n
     * @return
     */
    int countWays(int n) {
        // Building the base cases
        if (n == 1 || n == 2) return n;
        if (n == 3) return 4;
        // Building up the memory
        int[] memory = new int[n+1];
        memory[0] = 0; // It does not mattter
        memory[1] = 1;
        memory[2] = 2;
        memory[3] = 4;
        for (int i = 4; i <= n; i++) {
            memory[i] = memory[i -1] + memory[i - 2] + memory[i - 3];
        }
        return memory[n];
    }

    @Test
    public void test1() {
        // Given
        int n = 1;

        // When
        int numberOfWays = countWays(n);

        // Then
        assertEquals(1, numberOfWays);
    }

    @Test
    public void test2() {
        // Given
        int n = 2; // [1, 2] [12]

        // When
        int numberOfWays = countWays(n);

        // Then
        assertEquals(2, numberOfWays);
    }

    @Test
    public void test3() {
        // Given
        int n = 3; // [1, 2, 3] [1, 23] [12, 3] [123]

        // When
        int numberOfWays = countWays(n);

        // Then
        assertEquals(4, numberOfWays);
    }

    @Test
    public void test4() {
        // Given
        int n = 4;
        //                                          1234
        //                                1[234] - 12[34] -      123[4]
        //            1,2[34]  -  1,23[4]-1,234  12,3[4]-12,34     123,4
        //        1,2,3[4]-1,2,34 1,23,4         12,3,4
        //        1,2,3,4

        // When
        int numberOfWays = countWays(n);

        // Then
        assertEquals(7, numberOfWays);
    }


    /**
     * Book solution 1 - Recursive
     */
    private int bookSol1CountWays(int n) {
        if (n < 0) {
            return 0;
        } else if (n == 0) {
            return 1;
        } else {
            return bookSol1CountWays(n - 1) + bookSol1CountWays(n - 2) + bookSol1CountWays(n - 3);
        }
    }

    @Test
    public void testBoolSol1CountWays1() {
        // Given
        int n = 1;

        // When
        int numberOfWays = bookSol1CountWays(n);

        // Then
        assertEquals(1, numberOfWays);
    }

    @Test
    public void testBoolSol1CountWays2() {
        // Given
        int n = 2; // [1, 2] [12]

        // When
        int numberOfWays = bookSol1CountWays(n);

        // Then
        assertEquals(2, numberOfWays);
    }

    @Test
    public void testBoolSol1CountWays3() {
        // Given
        int n = 3; // [1, 2, 3] [1, 23] [12, 3] [123]

        // When
        int numberOfWays = bookSol1CountWays(n);

        // Then
        assertEquals(4, numberOfWays);
    }

    @Test
    public void testBoolSol1CountWays4() {
        // Given
        int n = 4;
        //                                          1234
        //                                1[234] - 12[34] -      123[4]
        //            1,2[34]  -  1,23[4]-1,234  12,3[4]-12,34     123,4
        //        1,2,3[4]-1,2,34 1,23,4         12,3,4
        //        1,2,3,4

        // When
        int numberOfWays = bookSol1CountWays(n);

        // Then
        assertEquals(7, numberOfWays);
    }

    /**
     * Book solution 2 - Memorization
     * All the solution overflows when n = 37
     */
    private int bookSol2CountWays(int n) {
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return bookSol2CountWays(n, memo);
    }

    private int bookSol2CountWays(int n, int[] memo) {
        if (n < 0) {
            return 0;
        } else if (n == 0) {
            return 1;
        } else if (memo[0] > -1) {
            return memo[n];
        } else {
            memo[n] = bookSol2CountWays(n - 1, memo) +  bookSol2CountWays(n - 2, memo) +
                    bookSol2CountWays(n - 3, memo);
            return memo[n];
        }
    }

    @Test
    public void testBoolSol2CountWays1() {
        // Given
        int n = 1;

        // When
        int numberOfWays = bookSol2CountWays(n);

        // Then
        assertEquals(1, numberOfWays);
    }

    @Test
    public void testBoolSol2CountWays2() {
        // Given
        int n = 2; // [1, 2] [12]

        // When
        int numberOfWays = bookSol2CountWays(n);

        // Then
        assertEquals(2, numberOfWays);
    }

    @Test
    public void testBoolSol2CountWays3() {
        // Given
        int n = 3; // [1, 2, 3] [1, 23] [12, 3] [123]

        // When
        int numberOfWays = bookSol2CountWays(n);

        // Then
        assertEquals(4, numberOfWays);
    }

    @Test
    public void testBoolSol2CountWays4() {
        // Given
        int n = 4;
        //                                          1234
        //                                1[234] - 12[34] -      123[4]
        //            1,2[34]  -  1,23[4]-1,234  12,3[4]-12,34     123,4
        //        1,2,3[4]-1,2,34 1,23,4         12,3,4
        //        1,2,3,4

        // When
        int numberOfWays = bookSol2CountWays(n);

        // Then
        assertEquals(7, numberOfWays);
    }
}
