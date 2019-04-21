/* *****************************************************************************
 *  Name: Ray Zhang
 *  Date: April 20, 2019
 *  Description: PercolationStats.java
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] ratio;
    private int totalTrials;

    public PercolationStats(int n, int trials) {
        totalTrials = trials;
        ratio = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            int[] randomIndexs = StdRandom.permutation(n * n);
            int randIndex = 0;
            while (!p.percolates()) {
                int curr = randomIndexs[randIndex];
                int row = curr / n + 1;
                int col = curr % n + 1;
                p.open(row, col);
                randIndex++;
            }
            ratio[i] = p.numberOfOpenSites() * 1.0 / (n * n);
        }

    }

    public double mean() {
        return StdStats.mean(ratio);
    }

    public double stddev() {
        return StdStats.stddev(ratio);
    }

    public double confidenceLo() {
        return mean() - (1.96 * stddev() / (Math.sqrt(totalTrials)));
    }

    public double confidenceHi() {
        return mean() + (1.96 * stddev() / (Math.sqrt(totalTrials)));
    }

    public static void main(String[] args) {
        PercolationStats stats = new PercolationStats(200, 100);
        System.out.println(stats.mean());
        System.out.println(stats.stddev());
        System.out.println("[ " + stats.confidenceLo() + ", " + stats.confidenceHi() + " ]");

    }
}
