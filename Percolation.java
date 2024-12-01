import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private final int n;
    private final WeightedQuickUnionUF uf;
    private int openSites;
    private final int virtualTop; // Virtual top site index
    private final int virtualBottom; // Virtual bottom site index

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("n must be greater than 0");
        this.n = n;
        grid = new boolean[n][n];
        uf = new WeightedQuickUnionUF(n * n + 2); // Plus two for virtual top and bottom
        openSites = 0;
        virtualTop = 0;
        virtualBottom = n * n + 1;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n)
            throw new IllegalArgumentException("Indices are out of prescribed range");

        if (!isOpen(row, col)) {
            grid[row - 1][col - 1] = true;
            openSites++;
            int index = xyTo1D(row, col);
            // Connect to adjacent open sites
            if (row == 1) uf.union(virtualTop, index); // Connect to virtual top
            if (row == n) uf.union(virtualBottom, index); // Connect to virtual bottom

            // Connect with adjacent open sites
            int[] dx = {0, 0, -1, 1};
            int[] dy = {-1, 1, 0, 0};
            for (int i = 0; i < 4; i++) {
                int newRow = row + dx[i];
                int newCol = col + dy[i];
                if (isValid(newRow, newCol) && isOpen(newRow, newCol)) {
                    int neighborIndex = xyTo1D(newRow, newCol);
                    uf.union(index, neighborIndex);
                }
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        int index = xyTo1D(row, col);
        return uf.find(virtualTop) == uf.find(index);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(virtualTop) == uf.find(virtualBottom);
    }

    // Convert 2D grid coordinates to 1D array index
    private int xyTo1D(int row, int col) {
        return (row - 1) * n + col;
    }

    // Check if the given indices are valid
    private boolean isValid(int row, int col) {
        return row >= 1 && row <= n && col >= 1 && col <= n;
    }

    // test client (optional)
    public static void main(String[] args) {
        // ... (Monte Carlo simulation logic)
    }
}
			
