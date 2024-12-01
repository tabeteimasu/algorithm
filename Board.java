import java.util.List;
import java.util.ArrayList;

public class Board {

    private final int[][] tiles;
    private final int n;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.n = tiles.length;
	this.tiles = new int[this.n][this.n];
	for(int i=0;i<this.n;i++)
		for(int j=0;j<this.n;j++)
			this.tiles[i][j] = tiles[i][j];
    }

    // string representation of this board
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(tiles[i][j] + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of tiles out of place
    public int hamming() {
        int hammingDistance = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] != 0 && tiles[i][j] != i * n + j + 1) {
                    hammingDistance++;
                }
            }
        }
        return hammingDistance;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int manhattanDistance = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] != 0) {
                    int goalRow = (tiles[i][j] - 1) / n;
                    int goalCol = (tiles[i][j] - 1) % n;
                    manhattanDistance += Math.abs(i - goalRow) + Math.abs(j - goalCol);
                }
            }
        }
        return manhattanDistance;
    }

    // is this board the goal board?
    public boolean isGoal() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == n - 1 && j == n - 1) {
                    if (tiles[i][j] != 0) {
                        return false;
                    }
                } else {
                    if (tiles[i][j] != i * n + j + 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == null || getClass() != y.getClass()) {
            return false;
        }
        Board that = (Board) y;
        if (this.n != that.n) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.tiles[i][j] != that.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        List<Board> neighbors = new ArrayList<>();
        int blankRow = -1;
        int blankCol = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] == 0) {
                    blankRow = i;
                    blankCol = j;
                    break;
                }
            }
        }
        // top
        if (blankRow > 0) {
            int[][] top = copyTiles();
            swap(top, blankRow, blankCol, blankRow - 1, blankCol);
            neighbors.add(new Board(top));
        }
        // bottom
        if (blankRow < n - 1) {
            int[][] bottom = copyTiles();
            swap(bottom, blankRow, blankCol, blankRow + 1, blankCol);
            neighbors.add(new Board(bottom));
        }
        // left
        if (blankCol > 0) {
            int[][] left = copyTiles();
            swap(left, blankRow, blankCol, blankRow, blankCol - 1);
            neighbors.add(new Board(left));
        }
        // right
        if (blankCol < n - 1) {
            int[][] right = copyTiles();
            swap(right, blankRow, blankCol, blankRow, blankCol + 1);
            neighbors.add(new Board(right));
        }
        return neighbors;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int[][] twinTiles = copyTiles();
        if (twinTiles[0][0] != 0 && twinTiles[0][1] != 0) {
            swap(twinTiles, 0, 0, 0, 1);
        } else if (twinTiles[0][0] != 0 && twinTiles[1][0] != 0) {
            swap(twinTiles, 0, 0, 1, 0);
        } else {
            swap(twinTiles, 1, 0, 1, 1);
        }
        return new Board(twinTiles);
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        int[][] tiles = {
                {8, 1, 3},
                {4, 0, 2},
                {7, 6, 5}
        };
        Board board = new Board(tiles);
        System.out.println(board.toString());
        System.out.println("Dimension: " + board.dimension());
        System.out.println("Hamming distance: " + board.hamming());
        System.out.println("Manhattan distance: " + board.manhattan());
        System.out.println("Is goal: " + board.isGoal());
        System.out.println("Neighbors:");
        for (Board neighbor : board.neighbors()) {
            System.out.println(neighbor.toString());
        }
        System.out.println("Twin:");
        System.out.println(board.twin().toString());
    }

    private int[][] copyTiles() {
        int[][] copy = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copy[i][j] = tiles[i][j];
            }
        }
        return copy;
    }

    private void swap(int[][] tiles, int i, int j, int k, int l) {
        int temp = tiles[i][j];
        tiles[i][j] = tiles[k][l];
        tiles[k][l] = temp;
    }
}
