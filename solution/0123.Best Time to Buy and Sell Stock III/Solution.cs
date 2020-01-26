using System;

public class Solution {
    public int MaxProfit(int[] prices) {
        int[] leftProfit = new int[prices.Length];
        int[] rightProfit = new int[prices.Length];
        
        // iterate from left to right
        var minPrice = int.MaxValue;
        var leftMaxProfit = 0;
        for (var i = 0; i < prices.Length; ++i)
        {
            minPrice = Math.Min(minPrice, prices[i]);
            leftMaxProfit = Math.Max(leftMaxProfit, prices[i] - minPrice);
            leftProfit[i] = leftMaxProfit;
        }

        // iterate from right to left
        var maxPrice = int.MinValue;
        var rightMaxProfit = 0;
        for (var i = prices.Length - 1; i >= 0; --i)
        {
            maxPrice = Math.Max(maxPrice, prices[i]);
            rightMaxProfit = Math.Max(rightMaxProfit, maxPrice - prices[i]);
            rightProfit[i] = rightMaxProfit;
        }
        
        // merge two profits
        var maxProfit = 0;
        for (var i = 0; i < prices.Length; ++i)
        {
            maxProfit = Math.Max(maxProfit, leftProfit[i] + rightProfit[i]);
        }
        
        return maxProfit;
    }
}