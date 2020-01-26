public class Solution {
    public int MaxProfit(int[] prices) {
        var result = 0;
        var i = 0;
        while (i + 1 < prices.Length)
        {
            while (i + 1 < prices.Length && prices[i] >= prices[i + 1])
            {
                ++i;
            }
            result -= prices[i];
            while (i + 1 < prices.Length && prices[i] <= prices[i + 1])
            {
                ++i;
            }
            result += prices[i];
        }
        return result;
    }
}