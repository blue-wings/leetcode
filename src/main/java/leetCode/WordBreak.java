package leetCode;

import java.util.HashSet;
import java.util.Set;

/**
 * User: FR
 * Time: 10/31/14 2:15 PM
 * Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 * For example, given
 * s = "leetcode",
 * dict = ["leet", "code"].
 * Return true because "leetcode" can be segmented as "leet code".
 */
public class WordBreak {

    public boolean wordBreak(String s, Set<String> dict) {
        boolean[] f = new boolean[s.length()];
        for(int i =0; i<s.length(); i++){
            if(dict.contains(s.substring(0, i+1))){
                f[i]=true;
                continue;
            }else {
                for(int j=0; j<i; j++){
                    if(f[j] && dict.contains(s.substring(j+1,i+1))){
                        f[i]=true;
                        continue;
                    }
                }
            }
        }
        return f[s.length()-1];
    }

    public static void main(String[] args){
        Set<String> dict = new HashSet<String>();
        dict.add("fang");
        dict.add("rui");
        dict.add("zai");
        dict.add("zhe");
        String s= "fangr";
        WordBreak wordBreak = new WordBreak();
        System.out.println(wordBreak.wordBreak(s, dict));
    }

}
