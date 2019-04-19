/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class PercolationTest {
    public static void main(String[] args) {
        Percolation p = new Percolation(4);
        int[] lmao = StdRandom.permutation(16);
        System.out.println(Arrays.toString(lmao));
        System.out.println(p.n);


    }
}
