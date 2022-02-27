package com.jiahaoliuliu.chapter8.recursionanddynamicprogramming;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A magic index in an array A[0...n-1] is defined to be an index such that A[i] = i.
 * Given a sorted array of distinct integers, write a method to find a magic index,
 * if one exits, in array A.
 *
 * <ul>Follow up
 *  <li>What if the values are not distinct?</li>
 * </ul>
 *
 * <ul>Hints:
 *  <li>170: Start with a brute force algorithm.</li>
 *  <li>204: Your brute force algorithm probably run in O(N) time. If you're trying to beat that
 *           runtime, what runtime do you think you will get to? What sorts of algorithms have that
 *           runtime? </li>
 *  <li>240: Can you solve the problem in O(log N)</li>
 *  <li>286: Binary search has a runtime of O(log N). Can you apply a form of binary search to the problem?</li>
 *  <li>340: Given a specific index and value, can you identify if the magic index would be before or after it?</li>
 * </ul>
 */
public class Q3MagicIndex {

    /**
     * Brute force problem.
     *
     * The array is sorted
     * the items are different
     * @param array
     * @return
     */
    private int magicIndex(int[] array) {
        if (array.length == 0) return -1;

        for (int i = 0; i < array.length; i++) {
            // We know the array is sorted and all the items are different,
            // if the current number is bigger than the position, then for the following
            // numbers, it is not possible exist index
            if (array[i] > i) {
                return -1;
            }
            if (array[i] == i) {
                return i;
            }
        }

        return -1;
    }

    @Test
    public void test1() {
        // Given
        int[] array = new int[] {-1, 0, 1, 3, 5};

        // When
        int magicIndex = magicIndex(array);

        // Then
        assertEquals(3, magicIndex);
    }

    @Test
    public void test2() {
        // Given
        int[] array = new int[]{};

        // When
        int magicIndex = magicIndex(array);

        // Then
        assertEquals(-1, magicIndex);
    }

    @Test
    public void test3() {
        // Given
        int[] array = new int[]{1, 2, 3, 4, 5};

        // When
        int magicIndex = magicIndex(array);

        // Then
        assertEquals(-1, magicIndex);
    }

    @Test
    public void test4() {
        // Given
        int[] array = new int[] {2, 3, 4, 5, 6};

        // When
        int magicIndex = magicIndex(array);

        // Then
        assertEquals(-1, magicIndex);
    }

    /**
     * Initial analysis
     * We know the array is sorted. We know all the items are different.
     * Possible cases:
     *  [1 ,2, 3, 4, 5] => The number in the middle (3) is bigger than the position(2). It is not possible to search
     *                     on the right if the magic number exists. We must search it on the left
     *  [-1, 0, 1, 2, 3] => The number in the middle (1) is smaller than the position (2). it is not possible to search
     *                     on the left if the magic number exists. We must search it on the right
     * @param array
     * @return
     */
    private int magicNumberUsingBinarySearch(int[] array) {
        // The target is the index which is not according to the search
        return magicNumberUsingBinarySearch(array, 0, array.length - 1);
    }

    private int magicNumberUsingBinarySearch(int[] array, int lowerBound, int upperBound) {
        // If the number cannot be found, return -1
        if (lowerBound > upperBound) {
            return -1;
        }

        int middle = lowerBound + (upperBound - lowerBound) / 2;
        if (array[middle] == middle) {
            return middle;
        // If the middle is bigger than the target, on the right side it won't be possible.
        // The response got to be on the left side
        } else if (middle > middle) {
            return magicNumberUsingBinarySearch(array, lowerBound, middle - 1);
        } else {
            return magicNumberUsingBinarySearch(array, middle+1, upperBound);
        }
    }

    @Test
    public void testMagicNumberUsingBinarySearch1() {
        // Given
        int[] array = new int[] {-1, 0, 1, 3, 5};

        // When
        int magicIndex = magicNumberUsingBinarySearch(array);

        // Then
        assertEquals(3, magicIndex);
    }

    @Test
    public void testMagicNumberUsingBinarySearch2() {
        // Given
        int[] array = new int[]{};

        // When
        int magicIndex = magicNumberUsingBinarySearch(array);

        // Then
        assertEquals(-1, magicIndex);
    }

    @Test
    public void testMagicNumberUsingBinarySearch3() {
        // Given
        int[] array = new int[]{1, 2, 3, 4, 5};

        // When
        int magicIndex = magicNumberUsingBinarySearch(array);

        // Then
        assertEquals(-1, magicIndex);
    }

    @Test
    public void testMagicNumberUsingBinarySearch4() {
        // Given
        int[] array = new int[] {2, 3, 4, 5, 6};

        // When
        int magicIndex = magicNumberUsingBinarySearch(array);

        // Then
        assertEquals(-1, magicIndex);
    }

    /**
     * Book solution 1
     */
    private int bookSol1MagicFast(int[] array) {
        return bookSol1MagicFast(array, 0, array.length - 1);
    }

    private int bookSol1MagicFast(int[] array, int start, int end) {
        if (end < start) {
            return -1;
        }
        int mid = (start + end) / 2;
        if (array[mid] == mid) {
            return mid;
        } else if (array[mid] > mid) {
            return bookSol1MagicFast(array, start, mid - 1);
        } else {
            return bookSol1MagicFast(array, mid + 1, end);
        }
    }

    @Test
    public void testBookSol1MagicFast1() {
        // Given
        int[] array = new int[] {-1, 0, 1, 3, 5};

        // When
        int magicIndex = bookSol1MagicFast(array);

        // Then
        assertEquals(3, magicIndex);
    }

    @Test
    public void testBookSol1MagicFast2() {
        // Given
        int[] array = new int[]{};

        // When
        int magicIndex = bookSol1MagicFast(array);

        // Then
        assertEquals(-1, magicIndex);
    }

    @Test
    public void testBookSol1MagicFast3() {
        // Given
        int[] array = new int[]{1, 2, 3, 4, 5};

        // When
        int magicIndex = bookSol1MagicFast(array);

        // Then
        assertEquals(-1, magicIndex);
    }

    @Test
    public void testBookSol1MagicFast4() {
        // Given
        int[] array = new int[] {2, 3, 4, 5, 6};

        // When
        int magicIndex = bookSol1MagicFast(array);

        // Then
        assertEquals(-1, magicIndex);
    }

    /**
     * Book solution when all the elements are sorted but not distinct
     */
    public int bookSol2MagicFast(int[] array) {
        return bookSol2MagicFast(array, 0, array.length - 1);
    }

    public int bookSol2MagicFast(int[] array, int start, int end) {
        if (end < start) return -1;

        int midIndex = (start + end ) / 2;
        int midValue = array[midIndex];
        if (midValue == midIndex) {
            return midIndex;
        }

        // Search left first
        int leftIndex = Math.min(midIndex - 1, midValue); // Skip the same elements on the left
        int left = bookSol2MagicFast(array, start, leftIndex);
        if (left >= 0) {
            return left;
        }

        // Search right
        int rightIndex = Math.max(midIndex + 1, midValue);  // Skip the same elements on the right
        int right = bookSol2MagicFast(array, rightIndex, end);
        return right;
    }

    @Test
    public void testBookSol2MagicFast1() {
        // Given
        int[] array = new int[] {-10, -5, 2, 2, 2, 3, 4, 7, 9, 12, 13};

        // When
        int magicIndex = bookSol1MagicFast(array);

        // Then
        assertEquals(7, magicIndex);
    }


}
