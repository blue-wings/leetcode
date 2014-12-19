package leetCode;

/**
 * User: FR
 * Time: 12/18/14 3:45 PM
 * <p/>
 * Given an array of integers, every element appears three times except for one. Find that single one.
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 */
public class SingleNum2 {

    public int singleNumberCommon(int[] A) {
        int[] bitNum = new int[32];
        int result =0;
        for (int j = 0; j < 32; j++) {
            for (int i = 0; i < A.length; i++) {
                int ele = A[i];
                int ifExist = ele & (1 << j);
                if (ifExist != 0) {
                    bitNum[j] = ++bitNum[j];
                }
            }
            bitNum[j] = bitNum[j]%3;
            if(bitNum[j] != 0){
                result |= bitNum[j]<<j;
            }
        }
        return result;
    }

    public int singleNumber(int[] A) {

        for(int i=0; i<A.length; i++){

        }
        return -1;
    }

    public static void main(String[] args){
        int[] A = {1, 1, 1, 2, 3, 3, 3};
        SingleNum2 singleNum2 = new SingleNum2();
        System.out.println(singleNum2.singleNumberCommon(A)+"");
    }
}
