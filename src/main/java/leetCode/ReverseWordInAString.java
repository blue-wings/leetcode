package leetCode;

/**
 * User: FR
 * Time: 10/14/14 6:57 PM
 * Given an input string, reverse the string word by word.
 * For example,
 * Given s = "the sky is blue",
 * return "blue is sky the".
 */
public class ReverseWordInAString {

    public String reverseWords(String s){
        boolean postFlag = false;
        boolean spaceFlag = false;
        StringBuilder result = new StringBuilder(200);
        StringBuilder sb = new StringBuilder(10);
        for (int i=s.length()-1; i>=0; i--){
            char c = s.charAt(i);
            if(!postFlag && c==32){
                continue;
            }
            if(!postFlag){
                postFlag = true;
            }
            if(c == 32 && !spaceFlag){
                spaceFlag = true;
                result.append(sb.reverse().toString());
                sb = new StringBuilder(10);
                result.append(c);
            }else if(c==32 && spaceFlag){
                continue;
            }else {
                spaceFlag = false;
                sb.append(c);
            }
        }
        if(sb.length()!=0){
            result.append(sb.reverse());
        }
        for (int i=result.length()-1; i>=0; i--){
            char c = result.charAt(i);
            if(c==32){
                result.deleteCharAt(i);
            }else {
                break;
            }

        }
        return result.toString();
    }

    public static void main(String[] args){
        String s = "who are you";
        ReverseWordInAString r = new ReverseWordInAString();
        System.out.println(r.reverseWords(s));
    }
}
