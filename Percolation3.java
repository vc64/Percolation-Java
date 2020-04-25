//compilation: javac -cp C:/users/zehua/Desktop/vc/javacode/percolation/algs4unzip/ percolation.java
//running: java -cp C:/users/zehua/desktop/vc/javacode/percolation/algs4unzip;. percolation
// import edu.princeton.cs.algs4.WeightedQuickUnionUF;


//import edu.princeton.cs.algs4.StdRandom;
//import edu.princeton.cs.algs4.*;
//import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation3 {

    // initialize variables/attributes of Percolation3 class/object or whatever

    // side length of the percolation grid
    int size;

    // area is just size squared bc size squared is used commonly
    int area;

    boolean[][] grid;
    int totalopen = 0;

    // unionfind object that stores each node and its connection. stores two extra nodes to represent the top and bottom of the grid
    WeightedQuickUnionUF uf;

    // same as uf but only one extra node to determine what nodes are connected to the top
    WeightedQuickUnionUF topuf;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation3(int n) {
        size = n;
        area = size * size;
        grid = new boolean[size][size];
        uf = new WeightedQuickUnionUF(area + 2);
        topuf = new WeightedQuickUnionUF(area + 1);
    }

    // opens the site (row, col)
    public void open(int row, int col) {

        totalopen++;
        grid[row][col] = true;

        // checks to see if site is on bottom or top and connects it to the top or bottom node
        if (row == 0) {
            uf.union(col * size + row, area);
            topuf.union(col * size + row, area);
        } else if (row == size - 1) {
            uf.union(col * size + row, area + 1);
        }

        // connects current site to all adjacent open sites. if statements account for edge sites that only have 2 or 3 possible adjacent sites
        if (row > 0) {
            if (grid[row - 1][col]) {
                uf.union(col * size + row, col * size + row - 1);
                topuf.union(col * size + row, col * size + row - 1);
            }
        }
        if (row < size - 1) {
            if (grid[row + 1][col]) {
                uf.union(col * size + row, col * size + row + 1);
                topuf.union(col * size + row, col * size + row + 1);
            }
        }
        if (col > 0) {
            if (grid[row][col - 1]) {
                uf.union(col * size + row, col * size - size + row);
                topuf.union(col * size + row, col * size - size + row);
            }
        }
        if (col < size - 1) {
            if (grid[row][col + 1]) {
                uf.union(col * size + row, col * size + size + row);
                topuf.union(col * size + row, col * size + size + row);
            }
        }
    }

    // checks if site is blocked
    public boolean isFull(int row, int col) {
        return !grid[row][col];
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return totalopen;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(area, area + 1);
    }

    // prints visual of grid: space = blocked, @ = open, * = last opened
    public void visual(int lastx, int lasty) {
        for(int i = 0; i < size; i++) {
            System.out.print("[");
            for(int j = 0; j < size; j++) {
                if (i == lastx && j == lasty) {
                    System.out.print("*");
                } else if (grid[i][j]) {
                    System.out.print("@");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println("]");
        }
    }
}