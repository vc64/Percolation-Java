import edu.princeton.cs.algs4.StdRandom;

import edu.princeton.cs.algs4.StdDraw;


public class runPerc {

    // n = size = side length of percolation grid
    public static int n = 20;

    // makes new instance of percolation3 class
    public static Percolation3 perc = new Percolation3(n);

    // declare xcoor and ycoor here so that al methods can use it. can also just be in the main method but whatever
    static int xcoor;
    static int ycoor;

    public static void main(String[] arg) {
        // allows for animation --> halts updating grid drawing until show() is called
        StdDraw.enableDoubleBuffering();

        // black background
        StdDraw.filledSquare(1,1,0.5*n);

        // sets scale of x and y
        StdDraw.setXscale(-1, n);
        StdDraw.setYscale(-1, n);

        // loops until percgrid percolates and opens random sites
        while(!perc.percolates()) {
            xcoor = StdRandom.uniform(n);
            ycoor = StdRandom.uniform(n);

            if (perc.isFull(xcoor, ycoor)) {
                perc.open(xcoor, ycoor);
                //StdDraw.pause(1);
            }
            // draws the visual
            drawperc();
        }

        // outputs number of sites opened and a textual visual
        System.out.println(perc.numberOfOpenSites());
        perc.visual(xcoor, ycoor);

    }


    public static void drawperc() {
        // lops through entire grid and changes pen color based on whether the square is connected to the top node, open, or blocked
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if (perc.isFull(i, j)) {
                    StdDraw.setPenColor(0, 0, 0);
                } else if (perc.topuf.connected(j*n + i, n*n)) {
                    StdDraw.setPenColor(0, 100, 255);
                } else {
                    StdDraw.setPenColor(155, 155, 155);
                }

                // draws colored square at site
                StdDraw.setPenRadius(0.005);
                StdDraw.filledSquare(j, n - i - 1, 0.5);

                // draws black square to help separate squares in visual
                StdDraw.setPenRadius(0.008);
                StdDraw.setPenColor(0, 0, 0);
                StdDraw.square(j, n - i - 1, 0.5);

            }
        }
        StdDraw.show();
    }

}