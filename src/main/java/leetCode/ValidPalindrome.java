package leetCode;

/**
 * User: FR
 * Time: 10/16/14 7:00 PM
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 * For example,
 * "A man, a plan, a canal: Panama" is a palindrome.
 * "race a car" is not a palindrome.
 * Note:
 * Have you consider that the string might be empty? This is a good question to ask during an interview.
 * For the purpose of this problem, we define empty string as valid palindrome.
 */
public class ValidPalindrome {

    public boolean isPalindrome(String s) {
        if(s.equals("")){
            return true;
        }
        s = s.toLowerCase();
        StringBuilder clearStr = new StringBuilder();
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(c>=48 && c<=57){
                clearStr.append(c);
            }
            if(c>=97 && c<=122){
                clearStr.append(c);
            }
        }
        String str = clearStr.toString();
        String reverseStr = clearStr.reverse().toString();
        if(str.equals(reverseStr)){
            return true;
        }
        return false;
    }

    public static void main(String[] args){
        ValidPalindrome v = new ValidPalindrome();
        System.out.println(v.isPalindrome("A man, a plan, a canal: Panama"));
    }

}
