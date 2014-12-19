package leetCode;

/**
 * User: FR
 * Time: 12/18/14 2:47 PM
 *
 * Given an array of integers, every element appears twice except for one. Find that single one.
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 *
 * 核心思想
 * 1.  异或操作可交换
 * 2.  相同数的异或为0
 * 3.  任何数和0的异或为它自己
 */
public class SingleNum {

    public int singleNumber(int[] A) {
        if(A == null || A.length==0){
            return 0;
        }
        int result = A[0];
        for(int i=1; i<A.length; i++){
            result ^= A[i];
        }
        return result;
    }

}
