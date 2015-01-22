package leetCode;

import com.sun.xml.internal.ws.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * User: FR
 * Time: 1/22/15 10:23 AM
 *
 * Given an index k, return the kth row of the Pascal's triangle.
 *
 * For example, given k = 3,
 * Return [1,3,3,1].
 *
 * Note:
 * Could you optimize your algorithm to use only O(k) extra space?
 */
public class PascalTriangle2 {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<Integer>(rowIndex+1);
        for(int i=0; i<=rowIndex; i++){
            row.add(0);
        }
        for(int i=0; i<=rowIndex; i++){
            row.set(rowIndex, 1);
            for(int j=rowIndex-1; j>0; j--){
                row.set(j, row.get(j)+row.get(j-1));
            }
            row.set(0, 1);
        }
        return row;
    }

    public static void main(String[] args){
        List<Integer> c = new PascalTriangle2().getRow(3);
    }
}
