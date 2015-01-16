package leetCode;

/**
 * User: FR
 * Time: 1/14/15 11:07 AM
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit.
 * You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times).
 * However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 */
public class BestTimeToBuyAndSellStock2 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int preMin = prices[0];
        int sum = 0;
        for (int i = 1; i < prices.length; i++) {
            int price = prices[i];
            if (price < preMin) {
                preMin = price;
            } else if (price > preMin) {
                sum += price - preMin;
            }
            preMin = prices[i];
        }
        return sum;
    }
}
