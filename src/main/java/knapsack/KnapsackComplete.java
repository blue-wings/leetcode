package knapsack;

import util.Print;

/**
 * User: FR
 * Time: 11/3/14 5:21 PM
 */
public class KnapsackComplete {

    public static int  getOptimizationUse1dArray(int[] v, int[] w, int c){
        int[] s = new int[c+1];
        for(int i=1; i<=v.length; i++){
            for(int j=1; j<=c; j++){
                int wi = w[i-1];
                int vi = v[i-1];
                if(j>= vi ){
                    s[j] = Math.max(s[j], s[j-vi]+wi);
                }
            }
        }
        Print.printArray(s);
        return s[c];
    }

    public static int getOptimizationUse2dArray(int[] v, int[] w, int c){
        int[][] s = new int[v.length+1][c+1];

        for(int i=1; i<=v.length; i++){
            for(int j=1; j<=c; j++){
                int wi = w[i-1];
                int vi = v[i-1];
                s[i][j] = s[i-1][j];
                if(j>= vi ){
                    s[i][j] = Math.max(s[i-1][j], s[i][j-vi]+wi);
                }
            }
        }
        Print.print2DArray(s);
        return s[v.length][c];
    }

    public static void main(String[] args){
        int[] v = {2, 3, 5, 9, 6, 4};
        int[] w = {3, 4, 6, 4, 7, 6};
        int c =22 ;
        KnapsackComplete.getOptimizationUse2dArray(v, w, c);
        KnapsackComplete.getOptimizationUse1dArray(v, w, c);
    }

}
