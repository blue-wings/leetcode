package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * User: FR
 * Time: 1/22/15 10:13 AM
 *
 * Given numRows, generate the first numRows of Pascal's triangle.
 * For example, given numRows = 5,
 * Return
 *
 * [
 * [1],
 * [1,1],
 * [1,2,1],
 * [1,3,3,1],
 * [1,4,6,4,1]
 * ]
 * */
public class PascalTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<List<Integer>>();
        if(numRows<0){
            return triangle;
        }
        for(int i=0; i<numRows; i++){
            List<Integer> row = new ArrayList<Integer>();
            triangle.add(row);
            for(int j=0; j<=i; j++){
                if(j==0 || j==i){
                    row.add(1);
                }else {
                    int ele = triangle.get(i-1).get(j-1)+triangle.get(i-1).get(j);
                    row.add(ele);
                }
            }
        }
        return triangle;
    }

    public static void main(String[] args){
        List<List<Integer>> triangle = new PascalTriangle().generate(5);
        System.out.println(triangle);
    }
}
