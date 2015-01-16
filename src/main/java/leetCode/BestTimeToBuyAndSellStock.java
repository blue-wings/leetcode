package leetCode;

/**
 * User: FR
 * Time: 1/14/15 11:07 AM
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock),
 * design an algorithm to find the maximum profit.
 *
 * 有序差值问题
 */
public class BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int max = 0;
        int minPre = prices[0];
        for (int i = 1; i < prices.length; i++) {
            int price = prices[i];
            if(price<minPre){
                minPre = price;
            }else if(price-minPre>max){
                max = price-minPre;
            }
        }
        return max;
    }


}
