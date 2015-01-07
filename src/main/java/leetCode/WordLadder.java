package leetCode;

import java.util.*;

/**
 * User: FR
 * Time: 1/6/15 2:57 PM
 *
 * Given two words (start and end), and a dictionary, find the length of shortest transformation sequence from start to end, such that:
 * Only one letter can be changed at a time
 * Each intermediate word must exist in the dictionary
 * For example,
 *
 * Given:
 * start = "hit"
 * end = "cog"
 * dict = ["hot","dot","dog","lot","log"]
 * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 *
 * Note:
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 */
public class WordLadder {

    public int ladderLength(String start, String end, Set<String> dict){
        if (start == null || end == null || start.equals(end)
                || start.length() != end.length())
            return 0;

        if (isOneWordDiff(start, end))
            return 2;

        Queue<String> queue=new LinkedList<String>();
        HashMap<String,Integer> dist=new HashMap<String,Integer>();

        queue.add(start);
        dist.put(start, 1);

        while(!queue.isEmpty())
        {
            String head=queue.poll();

            int headDist=dist.get(head);
            //从每一个位置开始替换成a~z
            for(int i=0;i<head.length();i++)
            {
                for(char j='a';j<'z';j++)
                {
                    if(head.charAt(i)==j) continue;
                    char[] s = head.toCharArray();
                    s[i]=j;
                    String sb = new String(s);
                    if(sb.toString().equals(end)) return headDist+1;

                    if(dict.contains(sb.toString())&&!dist.containsKey(sb.toString()))
                    {
                        queue.add(sb.toString());
                        dist.put(sb.toString(), headDist+1);
                    }
                }
            }
        }

        return 0;
    }

    private boolean isOneWordDiff(String a, String b) {
        int diff = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                diff++;
                if (diff >= 2)
                    return false;
            }
        }

        return diff == 1;
    }

    public int ladderLengthTraceback(String start, String end, Set<String> dict) {
        List<String> words = new ArrayList<String>();
        List<String> result = new ArrayList<String>();
        backtrack(start, end, dict, words, result);
        if(result.isEmpty()){
            return 0;
        }
        return result.size()+1;
    }

    private void backtrack(String start, String end, Set<String> dict, List<String> words, List<String> result){
        if(start.equals(end)){
            if(result.isEmpty() || words.size() < result.size()){
                result.clear();
                result.addAll(words);
            }
            return;
        }else if (!result.isEmpty() && words.size()>= result.size()){
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
        String start = "hit";
        String end = "cog";
        String[] dict = {"hot","dot","dog","lot","log"};
        Set<String> d = new HashSet<String>(Arrays.asList(dict));
        WordLadder wordLadder = new WordLadder();
        System.out.println(wordLadder.ladderLengthTraceback(start, end, d));
        System.out.println(wordLadder.ladderLength(start, end, d));
    }


}
