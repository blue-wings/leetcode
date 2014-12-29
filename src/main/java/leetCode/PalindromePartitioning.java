package leetCode;

import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * User: FR
 * Time: 12/29/14 10:08 AM
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 * For example, given s = "aab",
 * Return
 * [
 * ["aa","b"],
 * ["a","a","b"]
 * ]
 */
public class PalindromePartitioning {
    public List<List<String>> partition(String s) {
        List<List<String>> allResults = new ArrayList<List<String>>();
        if(s.length() == 1){
            ArrayList<String> result = new ArrayList<String>();
            result.add(s.substring(0, 1));
            allResults.add(result);
            return allResults;
        }
        for(int i=1; i<s.length()+1; i++){
            if(isPalindrome(s.substring(0, i))){
                ArrayList<String> result = new ArrayList<String>();
                result.add(s.substring(0, i));
                trackBack(s, i-1, result, allResults);
            }
        }
        return allResults;
    }

    private void trackBack(String s, int end, ArrayList<String> result, List<List<String>> allResults){
        if(end == s.length()-1){
            ArrayList<String> a =(ArrayList<String>)result.clone();
            allResults.add(a);
            return;
        }
        for(int i=end+2; i<=s.length(); i++){
            String subS = s.substring(end+1, i);
            if(isPalindrome(subS)){
                int size = result.size();
                result.add(size, subS);
                trackBack(s,  i-1, result, allResults);
                result.remove(size);
            }
        }
    }

    private boolean isPalindrome(String s){
        String rs = new StringBuffer(s).reverse().toString();
        if(s.equals(rs)){
            return true;
        }
        return false;
    }

    public static void main(String[] args){
        String s = "aa";
        List<List<String>> rets = new  PalindromePartitioning().partition(s);
        for (List<String> ret : rets){
            System.out.println(StringUtils.join(ret, ","));
        }
    }


}
