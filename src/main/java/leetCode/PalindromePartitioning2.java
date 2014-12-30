package leetCode;

/**
 * User: FR
 * Time: 12/29/14 10:38 AM
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * For example, given s = "aab",
 * Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
 */
public class PalindromePartitioning2 {
    public int minCut(String s) {
        boolean[][] p = new boolean[s.length()][s.length()];
        char[] c = s.toCharArray();
        fillArray(p, c);
        int[] f = new int[s.length()];
        for(int i=1; i<f.length; i++){
            f[i] = i;
        }
        for(int i=1; i<s.length(); i++){
            if(p[0][i]){
                f[i]=0;
                continue;
            }
            for(int j=0; j<=i; j++){
                if(p[j][i] && (f[j-1]+1)<f[i]){
                    f[i]=f[j-1]+1;
                }
            }
        }
        return f[s.length()-1];
    }

    /**
     *  注意代码的技巧，普通的双层循环为导致超时
     * 第一层循环定义了i和j的间距，后一次循环可以使用前一层循环的结果， 时间复杂度小于O(N*N),更重要的减小回文判断的复杂度以及次数
     * @param p
     * @param c
     */
    private void fillArray(boolean[][] p, char[] c) {
        for (int i = 0; i < c.length; i++) {
            p[i][i] = true;
        }
        for (int k = 1; k < c.length; k++) {
            for (int i = 0; i + k < c.length; i++) {
                int j = i + k;
                p[i][j] = c[i] == c[j] && (i + 1 < j - 1 && p[i + 1][j - 1] || i + 1 >= j - 1);
            }
        }
    }

    public static void main(String[] args){
        String s = "abbcca";
        System.out.println(new PalindromePartitioning2().minCut(s));
    }
}
