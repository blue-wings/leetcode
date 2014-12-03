package backtrack;

import java.util.*;

/**
 * User: FR
 * Time: 12/2/14 2:32 PM
 * 输出不重复数字的全排列
 */
public class NoRepeatAllPermutation extends Methodology {

    @Override
    public void backtrack(int[] a, int k, int[] t) {
        List<Integer> candidates = new ArrayList<Integer>();
        if(isASolution(a, k, t )){
            processSolution(a, k, t);
        }else {
            k++;
            constructCandidates(a, k-1, t, candidates);
            for(Integer candidate : candidates){
                a[k-1] = candidate;
                backtrack(a , k, t);
                remove(a, k, t);
            }
        }
    }

    @Override
    public void add(int[] a, int k, int[] t) {

    }

    @Override
    public void remove(int[] a, int k, int[] t) {
        a[k-1] = 0;
    }

    @Override
    public void constructCandidates(int[] a, int k, int[] t, List<Integer> candidates) {
        for(int e : t){
            boolean used = false;
            for(int ae : a){
                if(e == ae){
                    used = true;
                    break;
                }
            }
            if(!used && !candidates.contains(e)){
                candidates.add(e);
            }
        }
        System.out.println("");
    }

    @Override
    public void processSolution(int[] a, int k, int[] t) {
        StringBuilder sb = new StringBuilder("{");
        for(int i=0; i<k; i++){
            sb.append(a[i]);
            sb.append(",");
        }
        if(sb.charAt(sb.length()-1) == 44){
            sb.deleteCharAt(sb.length()-1);
        }
        sb.append("}");
        System.out.println(sb.toString());
    }

    @Override
    public boolean isASolution(int[] a, int k, int[] t) {
        return k == t.length;
    }

    public void fastDone(int[] t, int k){
        if(k == t.length){
            for(int e : t){
                System.out.print(e);
            }
            System.out.println("");
            return;
        }
        for(int i=k; i<t.length; i++){
            swap(t, i, k);
            fastDone(t, k+1);
            swap(t, i, k);
        }
    }

    private void swap(int[] t, int index1, int index2){
        int temp = t[index1];
        t[index1] = t[index2];
        t[index2] = temp;
    }

    public static void main(String[] args){
        NoRepeatAllPermutation permutation = new NoRepeatAllPermutation();
        int[] t = {1, 2, 3};
        int[] a = new int[t.length];
        permutation.backtrack(a, 0, t);
    }
}
