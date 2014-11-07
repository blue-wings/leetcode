package knapsack;

import util.Print;

/**
 * User: FR
 * Time: 11/3/14 3:03 PM
 */
public class Knapsack01 {

    public static int getOptimization(int[] v, int[] w, int c){
        int[][] s = new int[v.length+1][c+1];

        for(int i=1; i<=v.length; i++){
            for(int j=1; j<=c; j++){
                int wi = w[i-1];
                int vi = v[i-1];
                s[i][j] = s[i-1][j];
                if(j>= vi ){
                    s[i][j] = Math.max(s[i-1][j], s[i-1][j-vi]+wi);
                }
            }
        }
        Print.print2DArray(s);
        return s[v.length][c];
    }

    public static void main(String[] args){
        int[] v = {2, 3, 5, 9, 6, 4};
        int[] w = {3, 4, 6, 4, 7, 6};
        int c =10 ;
        Knapsack01.getOptimization(v, w, c);
    }
}
