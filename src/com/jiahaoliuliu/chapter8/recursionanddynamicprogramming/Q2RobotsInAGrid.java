package com.jiahaoliuliu.chapter8.recursionanddynamicprogramming;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        // Base case
        if (columns == 0 || (rows == 1 && columns == 1)) {
            return path;
        }

        return getPath(maze, 0, 0, path);
    }

    // Recursive way
    private List<Point> getPath(boolean[][]maze, int row, int column, List<Point> existingPath) {
        // If we are at the last point of the maze, then finish
        if (row == maze.length - 1 && column == maze[0].length - 1) {
            existingPath.add(new Point(maze.length - 1, maze[0].length - 1));
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

}
