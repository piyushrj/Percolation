import edu.princeton.cs.algs4.WeightedQuickUnionUF;  
import edu.princeton.cs.algs4.StdIn;  
import edu.princeton.cs.algs4.StdOut;  
public class Percolation { 
    private int[] site;  
    private int n;  
    private int nOpen;  
    private WeightedQuickUnionUF uf1;      
    public Percolation(int a) { 
        n  =  a;  
        if  (n  <=   0)
            throw new java.lang.IllegalArgumentException();  
        site  =  new int[n*n];  // this array stores whether the site is open or not
	    uf1  =  new WeightedQuickUnionUF(n*n);  
        for  (int i = 0;  i < n*n;i++) { 
            site[i] = 0;   // 0 represents a blocked site whereas 1 represents an open site
	    }
    }

    public void open(int row, int col) { 
            if (row < 1 || col < 1 || row > n || col > n)
                throw new java.lang.IndexOutOfBoundsException();    
            else { 
                if (!isOpen(row, col)) { 
                    site[(row-1)*n+(col-1)] = 1;  
                    nOpen++;   
                    // now connecting this opened site to its adjacent open sites
                    if (row+1 >= 1 && row+1 <=  n) { 
                        if (isOpen(row+1, col))
                            uf1.union((row-1)*n+(col-1), (row+1-1)*n+(col-1));  // adjacent site in the below row
                    }
                    if (row-1 >= 1 && row-1 <= n) { 
                        if (isOpen(row-1, col))
                        uf1.union((row-1)*n+(col-1), (row-1-1)*n+(col-1));  // adjacent site in the above row
                    }
                    if (col+1 >= 1 && col+1 <= n) { 
                        if (isOpen(row, col+1))
                        uf1.union((row-1)*n+(col-1), (row-1)*n+(col+1-1));  // adjacent site in the right column
                    }
                    if (col-1 >= 1 && col-1 <= n) { 
                        if (isOpen(row, col-1))
                        uf1.union((row-1)*n+(col-1), (row-1)*n+(col-1-1));  // adjacent site in the left column
                    }

                }
            }
    }
    public boolean isOpen(int row, int col) { 
        if (row < 1 || col < 1 || row > n || col > n)
            throw new java.lang.IndexOutOfBoundsException();  
        if (site[(row-1)*n+(col-1)] == 1)
            return true;  
        else
            return false;  
    }
    public boolean isFull(int row, int col) { 
        if (isOpen(row, col)) { 
            for (int i = 0;  i < n;i++) { 
                if (isOpen(1, i+1)) { 
                    if (uf1.connected(i, (row-1)*n+(col-1)))
                        return true;  
                }
            }
        }
        return false;  
    }
    public int numberOfOpenSites() { 
        return nOpen;  

    }
    public boolean percolates() { 
        for (int i = 0;  i < n;i++) { 
            if (isFull(n, i+1))
                return true;  
        }
        return false;  
    }
    public static void main(String[] args) { 
        int a = Integer.parseInt(args[0]);  
        Percolation p = new Percolation(a);  
        while (!p.percolates()) { 
            StdOut.println("Still does not percolate");  
            StdOut.println("Enter the site(row col) to open");  
            int r = StdIn.readInt();  
            int c = StdIn.readInt();  
            StdOut.println(r+"\t"+c+"\t"+p.n);  
            if (!p.isOpen(r, c)) { 
                p.open(r, c);  
            }
        }
        StdOut.println("Now it percolates");  
    }

}

