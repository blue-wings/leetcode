package backtrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: FR
 * Time: 12/3/14 1:56 PM
 * 给定一个字符串，生成组成这个字符串的字母的全排列
 */
public class RepeatAllPermutation extends Methodology {

    private  Map<Integer, Integer> valueTimes;

    public RepeatAllPermutation(int[] t) {
        this.valueTimes  = new HashMap<Integer, Integer>();
        for(Integer value : t){
            Integer times = valueTimes.get(value) == null?0:valueTimes.get(value);
            valueTimes.put(value, ++times);
        }
    }

    @Override
    public void backtrack(int[] a, int k, int[] t) {
        if(isASolution(a, k, t)){
            processSolution(a, k, t);
        }else {
            k++;
            List<Integer>  candidates = new ArrayList<Integer>();
            constructCandidates(a, k-1, t, candidates);
            for(Integer candidate : candidates){
                a[k-1] = candidate;
                add(a, k , t);
                backtrack(a, k, t);
                remove(a, k, t);
            }
        }
    }

    @Override
    public void add(int[] a, int k, int[] t) {
        Integer times = valueTimes.get(a[k - 1]);
        valueTimes.put(a[k-1], --times);
    }

    @Override
    public void remove(int[] a, int k, int[] t) {
        Integer times = valueTimes.get(a[k - 1]);
        valueTimes.put(a[k-1], ++times);
    }

    @Override
    public void constructCandidates(int[] a, int k, int[] t, List<Integer> candidates) {
        for(Integer value : valueTimes.keySet()){
            Integer times = valueTimes.get(value);
            if(times != 0){
                candidates.add(value);
            }
        }
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

    public static void main(String[] args){
        int[] t = {1, 2, 3, 1};
        RepeatAllPermutation permutation = new RepeatAllPermutation(t);
        int[] a = new int[t.length];
        permutation.backtrack(a, 0, t);
    }
}
