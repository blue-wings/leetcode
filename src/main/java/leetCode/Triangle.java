package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * User: FR
 * Time: 1/16/15 1:33 PM
 *
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
 *
 * For example, given the following triangle
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 *
 * Note:
 * Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
 */
public class Triangle {
    //DP solution
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle==null || triangle.isEmpty()){
            return 0;
        }
        int maxLength = triangle.get(triangle.size()-1).size();
        int[][] f = new int[triangle.size()][maxLength];
        f[0][0]=triangle.get(0).get(0);
        for(int i=1; i<triangle.size(); i++){
            for(int j=0; j<i+1; j++){
                int left = j-1<0?0:j-1;
                int right = j>i-1?i-1:j;
                f[i][j]=Math.min(f[i-1][left], f[i-1][right])+triangle.get(i).get(j);
            }
        }
        int min = f[triangle.size()-1][0];
        for(int i=1; i<triangle.get(triangle.size()-1).size(); i++){
            if(f[triangle.size()-1][i]<min){
                min = f[triangle.size()-1][i];
            }
        }
        return min;
    }

    public static void main(String[] args){
        Triangle t = new Triangle();
        Integer[] n1 = {-1};
        Integer[] n2 = {2,3};
        Integer[] n3 = {1,-1,-1};
        List<List<Integer>> triangle = new ArrayList<List<Integer>>();
        triangle.add(Arrays.asList(n1));
        triangle.add(Arrays.asList(n2));
        triangle.add(Arrays.asList(n3));
        System.out.println(t.minimumTotal(triangle));
    }
}
