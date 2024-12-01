import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationStats {
    private final int trials;
    private final double[] thresholds;
    private final double mean;
    private final double stddev;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException("n and trials must be greater than 0");
        this.trials = trials;
        this.thresholds = new double[trials];

        for (int t = 0; t < trials; t++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                if (!percolation.isOpen(row, col)) {
                    percolation.open(row, col);
                }
            }
            thresholds[t] = (double) percolation.numberOfOpenSites() / (n * n);
        }

        mean = StdStats.mean(thresholds);
        stddev = StdStats.stddev(thresholds);
    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean - (1.96 * stddev / Math.sqrt(trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean + (1.96 * stddev / Math.sqrt(trials));
    }

    // test client
    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Usage: PercolationStats n T");
        }
        
        int n = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(n, T);
        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
    }
}

