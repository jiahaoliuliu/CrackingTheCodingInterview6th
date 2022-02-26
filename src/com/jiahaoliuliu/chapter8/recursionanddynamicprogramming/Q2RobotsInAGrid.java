package com.jiahaoliuliu.chapter8.recursionanddynamicprogramming;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Imagine a robot sitting on the upper left corner of grid with r rows and c columns. The robot can only
 * move in two directions, right and down, but certain cells are "off limits" such that the robot cannot
 * step on them. Design an algorithm to find a path for the robot from the top left to the bottom right.
 * <ul>Hints:
 *  <li>331: For the robot to reach the last cell, it must find a path to the second-to-last cell. For it
 *           it to find a path to the second-to-last-cell, it must find a path to the third-to-last cells.</li>
 *  <li>360: Simplify this problem a bit by first figuring out if there's a path. Then, modify your algorithm
 *           to track the path</li>
 *  <li>388: Think again about the efficiency of your algorithm. Can you optimize it?</li>
 * </ul>
 */
public class Q2RobotsInAGrid {

    static class Point {
        int row;
        int col;

        Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Point)) return false;
            Point point = (Point) o;
            return row == point.row && col == point.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }

        @Override
        public String toString() {
            return "Point{" +
                    "row=" + row +
                    ", col=" + col +
                    '}';
        }
    }

    /**
     * Initial analysis. The robot only can move two directions: Right and down.
     * So for the last point, the unique way to reach there is from left and from up,
     * Same for the rest of the points
     * Recursive solution
     * @param maze
     * @return
     */
    private List<Point> getPath(boolean[][] maze) {
        // We need to record the size of the maze
        int rows = maze.length;
        int columns = maze[0].length;

        List<Point> path = new ArrayList<>();
        // Base case. If there is only one item in the list
        if (rows == 1 && columns == 1) {
            return path;
        }

        List<Point> correctPath = getPath(maze, 0, 0, path);
        if  (correctPath != null) {
            correctPath.add(new Point(maze.length - 1, maze[0].length - 1));
        }

        return correctPath;
    }

    // Recursive way
    private List<Point> getPath(boolean[][]maze, int row, int column, List<Point> existingPath) {
        // If we are at the last point of the maze, then finish
        if (row == maze.length - 1 && column == maze[0].length - 1) {
            return existingPath;
        }

        Point currentPoint = new Point(row, column);
        existingPath.add(currentPoint);

        // Go to next row if possible
        int nextRow = row + 1;
        if (nextRow < maze.length && maze[nextRow][column]) {
            List<Point> newListPoint = new ArrayList<>(existingPath);
            if (getPath(maze, nextRow, column, newListPoint) != null) {
                return newListPoint;
            }
        }

        // Go to next column
        int nextColumn = column + 1;
        if (nextColumn < maze[0].length && maze[row][nextColumn]) {
            if (getPath(maze, row, nextColumn, existingPath) != null) {
                return existingPath;
            }
        }

        // If it is not possible to advance left and right, return null
        return null;
    }

    @Test
    public void test1() {
        // Given
        boolean[][]maze = new boolean[1][1];
        maze[0][0] = true;

        // When
        List<Point> path = getPath(maze);

        // Then
        List<Point> expected = new ArrayList<>();
        assertEquals(expected, path);
    }

    @Test
    public void test2() {
        // Given
        // Maze       [[ t, f ]
        //             [ t, t ]]
        boolean[][]maze = new boolean[2][2];
        maze[0][0] = true;
        maze[0][1] = false;
        maze[1][0] = true;
        maze[1][1] = true;

        // When
        List<Point> path = getPath(maze);

        // Then
        List<Point> expected = new ArrayList<>();
        expected.add(new Point(0, 0));
        expected.add(new Point(1, 0));
        expected.add(new Point(1, 1));
        assertEquals(expected, path);
    }

    @Test
    public void test3() {
        // Given
        // Maze       [[ t, f, t ]
        //             [ t, t, t ]]
        boolean[][]maze = new boolean[2][3];
        maze[0][0] = true;
        maze[0][1] = false;
        maze[0][2] = true;
        maze[1][0] = true;
        maze[1][1] = true;
        maze[1][2] = true;

        // When
        List<Point> path = getPath(maze);

        // Then
        List<Point> expected = new ArrayList<>();
        expected.add(new Point(0, 0));
        expected.add(new Point(1, 0));
        expected.add(new Point(1, 1));
        expected.add(new Point(1, 2));
        assertEquals(expected, path);
    }

    @Test
    public void test4() {
        // Given
        // Maze       [[ t, t, t ]
        //             [ t, f, t ]]
        boolean[][]maze = new boolean[2][3];
        maze[0][0] = true;
        maze[0][1] = true;
        maze[0][2] = true;
        maze[1][0] = true;
        maze[1][1] = false;
        maze[1][2] = true;

        // When
        List<Point> path = getPath(maze);

        // Then
        List<Point> expected = new ArrayList<>();
        expected.add(new Point(0, 0));
        expected.add(new Point(0, 1));
        expected.add(new Point(0, 2));
        expected.add(new Point(1, 2));
        assertEquals(expected, path);
    }

    /**
     * The dynamic programming solution
     * [[   ,  F  , F   ],
     *  [   ,  F  ,    ],
     *  [   ,    ,    ]]
     * It is better start from the finish point and then, advance to the starting point (0, 0)
     * Building a cache where per each point of the map we add a list?
     *
     * Time complexity: In the worse case scenario, we need to build the memory passing by all the cells. O(n)
     * Space complexity: In the worse case scenario, the size of the memory is n, where n = maze.row * maze.column
     *                  And per each entry of the map, we have up to list of all the points
     *                  so the space is O(n^n)
     * @param maze
     * @return
     */
    private static List<Point> getPathDp(boolean[][] maze) {
        if (maze == null || maze.length == 0) return null;
        Map<Point, List<Point>> memory = new HashMap<>();
        int row = maze.length - 1;
        int col = maze[0].length - 1;
        List<Point> path = new ArrayList<>();
        fillMemory(maze, row, col, path, memory);
        List<Point> reversedPath = memory.get(new Point(0, 0));
        Collections.reverse(reversedPath);
        return reversedPath;
    }

    private static void fillMemory(boolean[][]maze, int row, int col, List<Point> actualPath, Map<Point, List<Point>> memory) {
        // Finish if we found the origin
        if (row == 0 && col== 0) {
            Point origin = new Point(row, col);
            actualPath.add(origin);
            memory.put(origin, actualPath);
            return;
        }

        // if we are out of bounds or the current point has been visited, finish
        Point currentPoint = new Point(row, col);
        if (row < 0 || col < 0 || memory.containsKey(currentPoint)) {
            return;
        }

        // if the current path is possible, add it to the point
        if (maze[row][col]) {
            actualPath.add(currentPoint);
            memory.put(currentPoint, actualPath);
        } else {
            return;
        }

        // Try it for the upper cell
        fillMemory(maze, row-1, col, new ArrayList<>(actualPath), memory);

        // Try it for the left cell
        fillMemory(maze, row, col - 1, new ArrayList<>(actualPath), memory);
    }

    @Test
    public void testGetPathDp1() {
        // Given
        boolean[][]maze = new boolean[1][1];
        maze[0][0] = true;

        // When
        List<Point> path = getPathDp(maze);

        // Then
        List<Point> expected = new ArrayList<>();
        expected.add(new Point(0, 0));
        assertEquals(expected, path);
    }

    @Test
    public void testGetPathDp2() {
        // Given
        // Maze       [[ t, f ]
        //             [ t, t ]]
        boolean[][]maze = new boolean[2][2];
        maze[0][0] = true;
        maze[0][1] = false;
        maze[1][0] = true;
        maze[1][1] = true;

        // When
        List<Point> path = getPathDp(maze);

        // Then
        List<Point> expected = new ArrayList<>();
        expected.add(new Point(0, 0));
        expected.add(new Point(1, 0));
        expected.add(new Point(1, 1));
        assertEquals(expected, path);
    }

    @Test
    public void testGetPathDp3() {
        // Given
        // Maze       [[ t, f, t ]
        //             [ t, t, t ]]
        boolean[][]maze = new boolean[2][3];
        maze[0][0] = true;
        maze[0][1] = false;
        maze[0][2] = true;
        maze[1][0] = true;
        maze[1][1] = true;
        maze[1][2] = true;

        // When
        List<Point> path = getPathDp(maze);

        // Then
        List<Point> expected = new ArrayList<>();
        expected.add(new Point(0, 0));
        expected.add(new Point(1, 0));
        expected.add(new Point(1, 1));
        expected.add(new Point(1, 2));
        assertEquals(expected, path);
    }

    @Test
    public void testGetPathDp4() {
        // Given
        // Maze       [[ t, t, t ]
        //             [ t, f, t ]]
        boolean[][]maze = new boolean[2][3];
        maze[0][0] = true;
        maze[0][1] = true;
        maze[0][2] = true;
        maze[1][0] = true;
        maze[1][1] = false;
        maze[1][2] = true;

        // When
        List<Point> path = getPathDp(maze);

        // Then
        List<Point> expected = new ArrayList<>();
        expected.add(new Point(0, 0));
        expected.add(new Point(0, 1));
        expected.add(new Point(0, 2));
        expected.add(new Point(1, 2));
        assertEquals(expected, path);
    }

    /**
     * Book solution 1
     *
     * O(2^(r + c))
     */
    ArrayList<Point> bookSol1GetPath(boolean[][] maze) {
        if (maze == null || maze.length == 0) return null;
        ArrayList<Point> path = new ArrayList<>();
        if (bookSol1GetPath(maze, maze.length - 1, maze[0].length - 1, path)) {
            return path;
        }
        return null;
    }

    boolean bookSol1GetPath(boolean[][] maze, int row, int col, ArrayList<Point> path) {
        // If out of bounds or not available, return
        if (col < 0 || row < 0 || !maze[row][col]) {
            return false;
        }

        boolean isAtOrigin = (row == 0) && (col == 0);

        // If there is a path from the start to here, add my location
        if (isAtOrigin || bookSol1GetPath(maze, row, col - 1, path) || bookSol1GetPath(maze, row - 1, col, path)) {
            Point p = new Point(row, col);
            path.add(p);
            return true;
        }
        return false;
    }

    @Test
    public void testBookSol1GetPath1() {
        // Given
        boolean[][]maze = new boolean[1][1];
        maze[0][0] = true;

        // When
        List<Point> path = bookSol1GetPath(maze);

        // Then
        List<Point> expected = new ArrayList<>();
        expected.add(new Point(0, 0));
        assertEquals(expected, path);
    }

    @Test
    public void testBookSol1GetPath2() {
        // Given
        // Maze       [[ t, f ]
        //             [ t, t ]]
        boolean[][]maze = new boolean[2][2];
        maze[0][0] = true;
        maze[0][1] = false;
        maze[1][0] = true;
        maze[1][1] = true;

        // When
        List<Point> path = bookSol1GetPath(maze);

        // Then
        List<Point> expected = new ArrayList<>();
        expected.add(new Point(0, 0));
        expected.add(new Point(1, 0));
        expected.add(new Point(1, 1));
        assertEquals(expected, path);
    }

    @Test
    public void testBookSol1GetPath3() {
        // Given
        // Maze       [[ t, f, t ]
        //             [ t, t, t ]]
        boolean[][]maze = new boolean[2][3];
        maze[0][0] = true;
        maze[0][1] = false;
        maze[0][2] = true;
        maze[1][0] = true;
        maze[1][1] = true;
        maze[1][2] = true;

        // When
        List<Point> path = bookSol1GetPath(maze);

        // Then
        List<Point> expected = new ArrayList<>();
        expected.add(new Point(0, 0));
        expected.add(new Point(1, 0));
        expected.add(new Point(1, 1));
        expected.add(new Point(1, 2));
        assertEquals(expected, path);
    }

    @Test
    public void testBookSol1GetPath4() {
        // Given
        // Maze       [[ t, t, t ]
        //             [ t, f, t ]]
        boolean[][]maze = new boolean[2][3];
        maze[0][0] = true;
        maze[0][1] = true;
        maze[0][2] = true;
        maze[1][0] = true;
        maze[1][1] = false;
        maze[1][2] = true;

        // When
        List<Point> path = bookSol1GetPath(maze);

        // Then
        List<Point> expected = new ArrayList<>();
        expected.add(new Point(0, 0));
        expected.add(new Point(0, 1));
        expected.add(new Point(0, 2));
        expected.add(new Point(1, 2));
        assertEquals(expected, path);
    }

    /**
     * Book solution 2 - More efficient
     *
     * Remove duplicated work
     * We are visiting the same squad many times
     * O(rc)
     */
    ArrayList<Point> bookSol2GetPath(boolean[][] maze) {
        if (maze == null || maze.length == 0) return null;
        ArrayList<Point> path = new ArrayList<>();
        HashSet<Point> failedPoints = new HashSet<>();
        if (bookSol2GetPath(maze, maze.length - 1, maze[0].length - 1, path, failedPoints)) {
            return path;
        }
        return null;
    }

    boolean bookSol2GetPath(boolean[][] maze, int row, int col, ArrayList<Point> path, HashSet<Point> failedPoints) {
        // If out of bounds or not available return
        if (col < 0 || row < 0 || !maze[row][col]) {
            return false;
        }

        Point p = new Point(row, col);

        // If we've already visited this cell, return
        if (failedPoints.contains(p)) {
            return false;
        }

        boolean isAtOrigin = (row == 0) && (col == 0);

        // If there's a path from start to my current location, add my location
        if (isAtOrigin || bookSol2GetPath(maze, row, col - 1, path, failedPoints) ||
                bookSol2GetPath(maze, row - 1, col, path, failedPoints)) {
            path.add(p);
            return true;
        }

        failedPoints.add(p); // Cache the result
        return false;
    }

    @Test
    public void testBookSol2GetPath1() {
        // Given
        boolean[][]maze = new boolean[1][1];
        maze[0][0] = true;

        // When
        List<Point> path = bookSol2GetPath(maze);

        // Then
        List<Point> expected = new ArrayList<>();
        expected.add(new Point(0, 0));
        assertEquals(expected, path);
    }

    @Test
    public void testBookSol2GetPath2() {
        // Given
        // Maze       [[ t, f ]
        //             [ t, t ]]
        boolean[][]maze = new boolean[2][2];
        maze[0][0] = true;
        maze[0][1] = false;
        maze[1][0] = true;
        maze[1][1] = true;

        // When
        List<Point> path = bookSol2GetPath(maze);

        // Then
        List<Point> expected = new ArrayList<>();
        expected.add(new Point(0, 0));
        expected.add(new Point(1, 0));
        expected.add(new Point(1, 1));
        assertEquals(expected, path);
    }

    @Test
    public void testBookSol2GetPath3() {
        // Given
        // Maze       [[ t, f, t ]
        //             [ t, t, t ]]
        boolean[][]maze = new boolean[2][3];
        maze[0][0] = true;
        maze[0][1] = false;
        maze[0][2] = true;
        maze[1][0] = true;
        maze[1][1] = true;
        maze[1][2] = true;

        // When
        List<Point> path = bookSol2GetPath(maze);

        // Then
        List<Point> expected = new ArrayList<>();
        expected.add(new Point(0, 0));
        expected.add(new Point(1, 0));
        expected.add(new Point(1, 1));
        expected.add(new Point(1, 2));
        assertEquals(expected, path);
    }

    @Test
    public void testBookSol2GetPath4() {
        // Given
        // Maze       [[ t, t, t ]
        //             [ t, f, t ]]
        boolean[][]maze = new boolean[2][3];
        maze[0][0] = true;
        maze[0][1] = true;
        maze[0][2] = true;
        maze[1][0] = true;
        maze[1][1] = false;
        maze[1][2] = true;

        // When
        List<Point> path = bookSol2GetPath(maze);

        // Then
        List<Point> expected = new ArrayList<>();
        expected.add(new Point(0, 0));
        expected.add(new Point(0, 1));
        expected.add(new Point(0, 2));
        expected.add(new Point(1, 2));
        assertEquals(expected, path);
    }


}
