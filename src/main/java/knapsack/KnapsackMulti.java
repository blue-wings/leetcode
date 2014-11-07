package knapsack;

import util.Print;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * User: FR
 * Time: 11/6/14 9:12 AM
 */
public class KnapsackMulti {

    /**
     * 通过二进制拆分转化成01背包问题
     * @param vs
     * @param ws
     * @param nums
     * @param c
     * @return
     */
    public static int getOptimizationByBinaryTo01(int[] vs, int[] ws, int[] nums, int c){
        List<Integer> v1s = new LinkedList<Integer>();
        List<Integer> w1s = new LinkedList<Integer>();
        for(int i=0; i<vs.length; i++){
            int w = ws[i];
            int v = vs[i];
            int num = nums[i];

            int k=1;
            int sum = (k<<1)-1;
            while(sum<=num && v*sum<=c){
                int w1 = w*k;
                w1s.add(w1);
                int v1 = v*k;
                v1s.add(v1);
                k = k<<1;
                sum = (k<<1)-1;
            }
            if(k-1 < num && v*(k-1)<c){
                int num1 = (c/v - k+1)<(num-k+1)?(c/v - k+1):(num-k+1);
                int w1 = w*num1;
                w1s.add(w1);
                int v1 = v*num1;
                v1s.add(v1);
            }
        }
        int[][] s = new int[v1s.size()+1][c+1];
        for(int i=1; i<v1s.size()+1; i++){
            for(int j=1; j<c+1; j++){
                s[i][j] = s[i-1][j];
                int vi = v1s.get(i-1);
                int wi = w1s.get(i-1);
                if(j>=vi && (s[i-1][j-vi]+wi)>s[i][j]){
                    s[i][j] =s[i-1][j-vi]+wi;
                }
            }
        }
        Print.print2DArray(s);
        return s[v1s.size()][c];
    }

    /**
     * 通过将多个物品拆分成单个物品进行横向扩展，转换成01背包问题
     * @param vs
     * @param ws
     * @param nums
     * @param c
     * @return
     */
    public static int getOptimizationByDirect01(int[] vs, int[] ws, int[] nums, int c){
        int length = 0;
        for(int num : nums){
            length += num;
        }
        int[] vs1 = new int[length];
        int[] ws1 = new int[length];
        int index = 0;
        for(int i=0; i<vs.length; i++){
            int v = vs[i];
            int w = ws[i];
            int num = nums[i];
            while (num-- != 0){
                vs1[index] = v;
                ws1[index] = w;
                index++;
            }
        }
        int[][] s = new int[length+1][c+1];
        for(int i=1; i<length+1; i++){
            for(int j=1; j<c+1; j++){
                s[i][j] = s[i-1][j];
                int v = vs1[i-1];
                int w = ws1[i-1];
                if(j>=v && (s[i-1][j-v]+w)>s[i][j]){
                    s[i][j] = s[i-1][j-v]+w;
                }
            }
        }
        Print.print2DArray(s);
        return s[length][c];
    }

    /**
     * 使用二位数组在每个容量上限制物品的数量
     * @param vs
     * @param ws
     * @param nums
     * @param c
     * @return
     */
    public static int getOptimizationByComplete(int[] vs, int[] ws, int[] nums, int c){
        int[] s = new int[c+1];
        int[][] jNum = new int[vs.length+1][c+1];
        for(int i=1; i<=vs.length; i++){
            int v = vs[i-1];
            int w = ws[i-1];
            int num = nums[i-1];
            for(int j=1; j<=c; j++){
                if(j>=v && s[j]<(s[j-v]+w) && (jNum[i][j]<num)){
                    s[j] = s[j-v]+w;
                    jNum[i][j] = jNum[i][j]+1;
                }
            }
        }
        Print.printArray(s);
        return s[c];
    }

    public static void main(String[] args){
        int[] v = {2, 3, 5, 9, 6, 4};
        int[] w = {3, 4, 6, 4, 7, 6};
        int[] num = {1,2,2,3,1,10};
        int c =10 ;
        int r1 = KnapsackMulti.getOptimizationByBinaryTo01(v, w, num, c);
        System.out.println("***************************************");
        int r2 = KnapsackMulti.getOptimizationByDirect01(v, w, num, c);
        System.out.println("***************************************");
        int r3 = KnapsackMulti.getOptimizationByComplete(v, w, num, c);
        System.out.println(r1+"-"+r2+"-"+r3);
    }
}
