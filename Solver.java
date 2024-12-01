import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.MinPQ;
import java.util.List;
import java.util.ArrayList;

public class Solver {

    private final Board initial;
    private final Board twin;
    private boolean solvable;
    private int moves;
    private List<Board> solution;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        this.initial = initial;
        this.twin = initial.twin();
        solve();
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return solvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return solvable ? moves : -1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        return solvable ? solution : null;
    }

    private void solve() {
        MinPQ<Node> pq = new MinPQ<>();
        MinPQ<Node> twinPq = new MinPQ<>();
        pq.insert(new Node(initial, 0, null));
        twinPq.insert(new Node(twin, 0, null));

        while (!pq.isEmpty() && !twinPq.isEmpty()) {
            Node node = pq.delMin();
            Node twinNode = twinPq.delMin();

            if (node.board.isGoal()) {
                solvable = true;
                moves = node.moves;
                solution = new ArrayList<>();
                while (node != null) {
                    solution.add(0, node.board);
                    node = node.prev;
                }
                return;
            }

            if (twinNode.board.isGoal()) {
                solvable = false;
                return;
            }

            for (Board neighbor : node.board.neighbors()) {
                if (node.prev == null || !neighbor.equals(node.prev.board)) {
                    pq.insert(new Node(neighbor, node.moves + 1, node));
                }
            }

            for (Board twinNeighbor : twinNode.board.neighbors()) {
                if (twinNode.prev == null || !twinNeighbor.equals(twinNode.prev.board)) {
                    twinPq.insert(new Node(twinNeighbor, twinNode.moves + 1, twinNode));
                }
            }
        }
    }

    private class Node implements Comparable<Node> {
        private final Board board;
        private final int moves;
        private final Node prev;

        public Node(Board board, int moves, Node prev) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(moves + board.manhattan(), other.moves + other.board.manhattan());
        }
    }

    // test client (see below)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
