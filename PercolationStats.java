import edu.princeton.cs.algs4.StdRandom; 
import edu.princeton.cs.algs4.StdStats; 
import edu.princeton.cs.algs4.StdIn; 
import edu.princeton.cs.algs4.StdOut; 

public class PercolationStats {
    private int N; // stores the number of rows/columns
    private int T; // stores the nuber of independent trials
    private double[] fOpen; // this array stores the fraction of opensites for  each trial,  hence it has size T
   
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new java.lang.IllegalArgumentException(); 
        else {
            N = n; 
            T = trials; 
        }
        fOpen = new double[trials]; 
        for (int i = 0; i < trials; i++)
            fOpen[i] = 0; 
    }
    public double mean() {
        double m=StdStats.mean(fOpen);
        return m;
    }
    public double stddev() {
        double sd=StdStats.stddev(fOpen);
        return sd;
    }
    public double confidenceLo() {
        double l = (mean()-1.96*stddev()/Math.sqrt(T)); 
        return l; 
    }
    public double confidenceHi() {
        double h = (mean()+1.96*stddev()/Math.sqrt(T)); 
        return h; 
    }

    public static void main(String[] args) {
        int a = Integer.parseInt(args[0]); 
        int b = Integer.parseInt(args[1]); 
        
        PercolationStats ps = new PercolationStats(a, b); 
        for (int i = 0; i < ps.T;i++) {
            Percolation p1 = new Percolation(ps.N); 
            while (!p1.percolates()) {
                    int randomRow = StdRandom.uniform(1, (ps.N)+1); 
                    int randomCol = StdRandom.uniform(1, (ps.N)+1); 
                    if (!p1.isOpen(randomRow, randomCol)) {
                        p1.open(randomRow, randomCol); 
                    }
            }
            ps.fOpen[i] = p1.numberOfOpenSites()/(double)(ps.N*ps.N); 
        }
        double m, s, l, h; 
        m = ps.mean(); 
        s = ps.stddev(); 
        l = ps.confidenceLo(); 
        h = ps.confidenceHi(); 
        StdOut.println("mean\t = "+m); 
        StdOut.println("stddev\t = "+s); 
        StdOut.println("95% confidence interval\t = ["+l+",  "+h+"]"); 
    }


}
