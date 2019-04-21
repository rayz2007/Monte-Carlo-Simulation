/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    public WeightedQuickUnionUF uf;
    public WeightedQuickUnionUF back;
    public boolean[] isClosed;
    public boolean[] isFull;
    public int openAmount;
    public int n;


    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        this.uf = new WeightedQuickUnionUF(n * n + 2);
        this.back = new WeightedQuickUnionUF(n * n + 2);
        this.isClosed = new boolean[n * n + 2];
        this.isFull = new boolean[n * n + 2];
        this.openAmount = 0;
        for (int i = 1; i <= n * n; i++) {
            isClosed[i] = true;
        }
    }

    public void open(int row, int col) {
        if (row <= 0 || row > n || col <= 0 || col > n) {
            throw new IllegalArgumentException();
        }
        int ind = (row - 1) * n + col;
        if (!isOpen(row, col)) {
            isClosed[ind] = false;
            if (row - 1 > 0 && isOpen(row - 1, col)) {
                uf.union(ind, index(row - 1, col));
                back.union(ind, index(row - 1, col));
            }
            if (row < n && isOpen(row + 1, col)) {
                uf.union(ind, index(row + 1, col));
                back.union(ind, index(row + 1, col));
            }
            if (col > 1 && isOpen(row, col - 1)) {
                uf.union(ind, index(row, col - 1));
                back.union(ind, index(row, col - 1));
            }
            if (col < n && isOpen(row, col + 1)) {
                uf.union(ind, index(row, col + 1));
                back.union(ind, index(row, col + 1));
            }
            if (row == 1) {
                uf.union(0, ind);
                back.union(ind, 0);
                isFull[ind] = true;
            }
            if (row == n) {
                back.union(ind, n * n + 1);
                if (isFull[ind]) {
                    isFull[n * n + 1] = true;
                }
            }
            openAmount++;
            isClosed[ind] = false;

        }
    }

    private int index(int row, int col) {
        return (row - 1) * n + col;
    }

    public boolean isOpen(int row, int col) {
        if (row <= 0 || row > n || col <= 0 || col > n) {
            throw new IllegalArgumentException();
        }
        int index = (row - 1) * n + col;
        return !isClosed[index];
    }

    public boolean isFull(int row, int col) {
        if (row <= 0 || row > n || col <= 0 || col > n) {
            throw new IllegalArgumentException();
        }
        int ind = (row - 1) * n + col;
        return uf.connected(0, ind);

    }

    public int numberOfOpenSites() {
        return openAmount;
    }

    public boolean percolates() {
        return back.connected(0, n * n + 1);
    }

   /* private void print(String s) {
        System.out.println(s);
    }

    public static void main(String[] args) {
        try {
            Percolation p = new Percolation(3);
            p.open(1, 1);
            p.open(2, 1);
            p.open(3, 1);
            System.out.println((p.isOpen(1, 1)));
            System.out.println((p.isFull(1, 1)));
            System.out.println((p.percolates()));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    } */

}

