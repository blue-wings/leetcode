package backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * User: FR
 * Time: 11/28/14 2:20 PM
 * 子集合
 */
public class AllSubsets extends Methodology {

    @Override
    public void backtrack(int[] a, int k, int[] t){
        List<Integer> candidates = new ArrayList<Integer>();
        if(isASolution(a, k, t )){
            processSolution(a, k, t);
        }else {
            k++;
            constructCandidates(a, k-1, t, candidates);
            for(Integer candidate : candidates){
                a[k-1] = candidate;
                backtrack(a , k, t);
            }
        }
    }

    @Override
    public boolean isASolution(int[] a, int k, int[] t){
        return k == t.length;
    }

    @Override
    public void constructCandidates(int[] a, int k, int[] t, List<Integer> candidates){
        candidates.add(1);
        candidates.add(0);
    }

    @Override
    public void processSolution(int[] a, int k, int[] t){
        StringBuilder sb = new StringBuilder("{");
        for(int i=0; i<k; i++){
            if(a[i] == 0){
                continue;
            }
            sb.append(t[i]);
            sb.append(",");
        }
        if(sb.charAt(sb.length()-1) == 44){
            sb.deleteCharAt(sb.length()-1);
        }
        sb.append("}");
        System.out.println(sb.toString());
    }


    public static void main(String[] args){
        AllSubsets subsets = new AllSubsets();
        int[] t = {1, 2};
        int[] a = new int[t.length];
        subsets.backtrack(a, 0, t);
    }



}
