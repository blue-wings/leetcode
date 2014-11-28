package leetCode;

import java.util.*;

/**
 * User: FR
 * Time: 11/25/14 4:54 PM
 */
public class WordBreak2 {

    public List<String> wordBreak(String s, Set<String> dict) {
        Map<Integer, List<Integer>> pMapALL = new HashMap<Integer, List<Integer>>();
        boolean[] f = new boolean[s.length()];
        for (int i = 0; i < s.length(); i++) {
            boolean flag = false;
            if (dict.contains(s.substring(0, i + 1))) {
                put(pMapALL, i, -1);
                flag = true;
            }
            for (int j = 0; j < i; j++) {
                if (f[j] && dict.contains(s.substring(j + 1, i + 1))) {
                    put(pMapALL, i, j);
                    flag = true;
                }
            }
            if (flag) {
                f[i] = true;
            }
        }
        if (f[s.length() - 1]) {
            Map<Integer, List<Integer>> pMap = new HashMap<Integer, List<Integer>>();
            LinkedList<Integer> stack = new LinkedList<Integer>();
            stack.push(s.length()-1);
            while (!stack.isEmpty()){
                Integer node = stack.pop();
                List<Integer> children = pMapALL.get(node);
                if(children != null){
                    pMap.put(node, children);
                    for(Integer child : children){
                        stack.push(child);
                    }
                }
            }

            List<String> results = new ArrayList<String>();
            for(Integer key : pMap.keySet()){
                List<Integer> children = pMap.get(key);
                if(children.contains(-1)){
                    print(pMap, -1, key, s, new LinkedList<String>(), results);
                }
            }
            return results;
        }
        return new ArrayList<String>();
    }

    private void put(Map<Integer, List<Integer>> pMap, Integer key, Integer value) {
        if (pMap.get(key) == null) {
            pMap.put(key, new ArrayList<Integer>());
        }
        pMap.get(key).add(value);
    }

    private void print(Map<Integer, List<Integer>> pMap, Integer startIndex, Integer endIndex, String s, LinkedList<String> stack, List<String> results) {
        String word = s.substring(startIndex+1, endIndex+1);
        stack.push(word);
        if(endIndex == s.length()-1){
            StringBuilder words = new StringBuilder();
           for(int i=stack.size()-1; i>=0; i--){
                words.append(stack.get(i));
               if(i != 0){
                   words.append(" ");
               }
           }
            results.add(words.toString());
            return;
        }
        for(Integer key : pMap.keySet()){
            List<Integer> children = pMap.get(key);
            for(Integer child : children){
                if(child.equals(endIndex)){
                    print(pMap, child, key, s, stack, results);
                    stack.pop();
                }
            }
        }
    }

    public static void main1(String[] args) {
        Set<String> dict = new HashSet<String>();
        dict.add("cat");
        dict.add("cats");
        dict.add("and");
        dict.add("sand");
        dict.add("dog");
        String s = "catsanddog";
        WordBreak2 wordBreak2 = new WordBreak2();
        List<String> results = wordBreak2.wordBreak(s, dict);
        for(String result : results){
            System.out.println(result);
        }
    }

    public static void main2(String[] args) {
        Set<String> dict = new HashSet<String>();
        dict.add("a");
        dict.add("abc");
        dict.add("b");
        dict.add("cd");
        String s = "abcd";
        WordBreak2 wordBreak2 = new WordBreak2();
        List<String> results = wordBreak2.wordBreak(s, dict);
        for(String result : results){
            System.out.println(result);
        }
    }

    public static void main(String[] args) {
        Set<String> dict = new HashSet<String>();
        dict.add("aaaa");
        dict.add("aa");
        dict.add("a");
        String s = "aaaaaaa";
        WordBreak2 wordBreak2 = new WordBreak2();
        List<String> results = wordBreak2.wordBreak(s, dict);
        for(String result : results){
            System.out.println(result);
        }
    }

}
