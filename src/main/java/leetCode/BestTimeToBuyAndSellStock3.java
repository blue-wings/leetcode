package leetCode;

/**
 * User: FR
 * Time: 1/14/15 11:07 AM
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
 * Note:
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 */
public class BestTimeToBuyAndSellStock3 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int[] maxLeft = new int[prices.length];
        int preMin = prices[0];
        for(int i=1; i<prices.length; i++){
            int price = prices[i];
            if(price<preMin){
                preMin=price;
                maxLeft[i] =maxLeft[i-1];
            }else if(price-preMin>maxLeft[i-1]) {
                maxLeft[i]=price - preMin;
            }else {
                maxLeft[i] =maxLeft[i-1];
            }
        }
        int[] maxRight = new int[prices.length];
        int preMax = prices[prices.length-1];
        for(int i=prices.length-2; i>=0; i--){
            int price = prices[i];
            if(price>preMax){
                preMax = price;
                maxRight[i] = maxRight[i+1];
            }else  if(preMax - price>maxRight[i+1]){
                maxRight[i]=preMax - price;
            }else {
                maxRight[i] = maxRight[i+1];
            }
        }
        int max =0;
        for(int i=1; i<prices.length; i++){
           if(maxLeft[i]+maxRight[i]>max){
               max = maxLeft[i]+maxRight[i];
           }
        }
        return max;
    }

    public static void main(String[] args){
        int[] a = {2,1,2,0,1};
        System.out.println(new BestTimeToBuyAndSellStock3().maxProfit(a));
    }

}
