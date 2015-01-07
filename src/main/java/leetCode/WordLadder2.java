package leetCode;

import com.sun.deploy.util.StringUtils;

import java.util.*;

/**
 * User: FR
 * Time: 1/7/15 11:07 AM
 *
 * Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to end, such that:
 * Only one letter can be changed at a time
 * Each intermediate word must exist in the dictionary
 * For example,
 *
 * Given:
 * start = "hit"
 * end = "cog"
 * dict = ["hot","dot","dog","lot","log"]
 * Return
 * [
 * ["hit","hot","dot","dog","cog"],
 * ["hit","hot","lot","log","cog"]
 * ]
 * Note:
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 */
public class WordLadder2 {


    HashMap<String,Integer> path = new HashMap<String,Integer>();
    //bfs生成path
    void bfs(String start, String end, HashSet<String> dict) {
        Queue queue = new LinkedList<String>();
        queue.add(start);
        path.put(start,0);
        String current;
        while(!queue.isEmpty()) {
            current = (String)queue.poll();
            if(current==end) {
                continue;
            }
            for(int i=0;i<current.length();i++) {
                char[] strCharArr = current.toCharArray();
                for(char ch='a';ch<='z';ch++) {
                    if(strCharArr[i]==ch) {
                        continue;
                    }
                    strCharArr[i] = ch;
                    String newWord = new String(strCharArr);
                    if(newWord.equals(end)==true||dict.contains(newWord)) {
                        //每个单词在path中只能出现一次，也就是每个单词只能出现在一层中，这样就很巧妙的解决了环的问题。
                        if(path.get(newWord)==null) {
                            int depth = (int)path.get(current);
                            path.put(newWord,depth + 1);
                            queue.add(newWord);
                        }
                    }
                }
            }
        }
    }
    //从目标单词往回找开始单词，记录所有路径
    void dfs(String start, String end, HashSet<String> dict, ArrayList<String> pathArray,ArrayList<ArrayList<String>> result) {
        //找到了，需要reverse加入的所有单词
        if(start.equals(end)==true) {
            pathArray.add(start);
            Collections.reverse(pathArray);
            result.add(pathArray);
            return;
        }
        if(path.get(start)==null) {
            return;
        }
        pathArray.add(start);
        int nextDepth = (int)path.get(start) - 1;
        for(int i=0;i<start.length();i++) {
            char[] strCharArr = start.toCharArray();
            for(char ch='a';ch<='z';ch++) {
                if(strCharArr[i]==ch) {
                    continue;
                }
                strCharArr[i] = ch;
                String newWord = new String(strCharArr);
                //只相差一个字母同时这个单词所在的层数也是当前单词的上一层
                if(path.get(newWord)!=null&&(path.get(newWord)==nextDepth)) {
                    ArrayList<String> newPathArray = new ArrayList<String>(pathArray);
                    dfs(newWord,end,dict,newPathArray,result);
                }
            }
        }
    }

    public ArrayList<ArrayList<String>> findLadders(String start, String end, HashSet<String> dict) {
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        ArrayList<String> path = new ArrayList<String>();
        if(start==null||end==null||start.length()!=end.length()) {
            return result;
        }
        bfs(start, end, dict);
        dfs(end,start, dict, path, result);
        return result;
    }


    public List<List<String>> findLaddersTraceback(String start, String end, Set<String> dict) {
        List<String> words = new ArrayList<String>();
        List<List<String>> result = new ArrayList<List<String>>();
        backtrack(start, end, dict, words, result);
        return result;
    }

    private void backtrack(String start, String end, Set<String> dict, List<String> words, List<List<String>> result){
        if(start.equals(end)){
            if(!result.isEmpty() && result.get(0).size()<words.size()){
                return;
            }
            if(!result.isEmpty() && words.size() < result.get(0).size()){
                result.clear();
            }
            List<String> rs = new ArrayList<String>();
            rs.addAll(words);
            result.add(rs);
            return;
        }
        List<String> cw = new ArrayList<String>();
        for(int i=0; i<start.length(); i++){
            char[] startCharArray = start.toCharArray();
            for(int j=0; j<26; j++){
                startCharArray[i] = (char)('a' +j);
                String word = new String(startCharArray);
                if((dict.contains(word) && !words.contains(word)) || word.equals(end)){
                    cw.add(word);
                }
            }
        }
        for(String word : cw){
            int size = words.size();
            words.add(word);
            backtrack(word, end, dict, words, result);
            words.remove(size);
        }
    }

    public static void main(String[] args){
        String start = "hot";
        String end = "dog";
        String[] dict = {"hot","dog","dot"};
        HashSet<String> d = new HashSet<String>(Arrays.asList(dict));
        WordLadder2 wordLadder2 = new WordLadder2();
        List<List<String>> result = wordLadder2.findLaddersTraceback(start, end, d);
        for(List<String> r : result){
            System.out.println(StringUtils.join(r, ","));
        }
        List<ArrayList<String>> result1 = wordLadder2.findLadders(start, end, d);
        for(List<String> r : result1){
            System.out.println(StringUtils.join(r, ","));
        }
    }
}
