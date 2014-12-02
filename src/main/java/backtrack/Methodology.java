package backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * User: FR
 * Time: 11/28/14 2:07 PM
 */
public class Methodology {

    /**
     * @param a 保存每步结果的数组
     * @param k 递归深度
     * @param t 被递归的原始数组
     */
    public void backtrack(int a[], int k, int[] t){
        List<Integer> candidates = new ArrayList<Integer>();
        if(isASolution(a, k, t )){
            processSolution(a, k, t);
        }else {
            k++;
            constructCandidates(a, k-1, t, candidates);
            for(Integer candidate : candidates){
                a[k-1] = candidate;
                add(a, k, t);
                backtrack(a , k, t);
                remove(a, k, t);
            }
        }
    }

    public void add(int[] a, int k, int[] t) {

    }

    public void remove(int[] a, int k, int[] t) {

    }

    public void constructCandidates(int[] a, int k, int[] t, List<Integer> candidates) {

    }

    public void processSolution(int[] a, int k, int[] t) {

    }

    public boolean isASolution(int[] a, int k, int[] t) {
        return false;
    }
}
