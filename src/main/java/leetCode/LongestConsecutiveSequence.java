package leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * User: FR
 * Time: 1/4/15 6:01 PM
 *
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * For example,
 * Given [100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
 * Your algorithm should run in O(n) complexity.
 */
public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] num) {
        int maxCounter=0;
        Map<Integer, int[]> map = new HashMap<Integer, int[]>();
        for(int n : num){
            if(map.containsKey(n))
                continue;
            int[] array = new int[2];
            array[0]=n;
            array[1]=n;
            map.put(n, array);
        }
        for(int n : map.keySet()){
            if(map.containsKey(n-1)){
                map.get(n)[0] = map.get(n-1)[0];
            }
            if(map.containsKey(n+1)){
                map.get(n)[1] = map.get(n+1)[1];
            }
            if((map.get(n)[1] - map.get(n)[0]+1)> maxCounter){
                maxCounter = map.get(n)[1] - map.get(n)[0]+1;
            }
            map.get(map.get(n)[0])[1]=map.get(n)[1];
            map.get(map.get(n)[1])[0]=map.get(n)[0];
        }
        return maxCounter;
    }

    public static void main(String[] args){
        int[] a = {-3,2,8,5,1,7,-8,2,-8,-4,-1,6,-6,9,6,0,-7,4,5,-4,8,2,0,-2,-6,9,-4,-1};
        LongestConsecutiveSequence l = new LongestConsecutiveSequence();
        System.out.println(l.longestConsecutive(a));
    }
}
