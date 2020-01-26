public class Solution {
    public int MaxProfit(int[] prices) {
        var result = 0;
        var minPrice = int.MaxValue;
        foreach (var price in prices)
        {
            if (price > minPrice && result < price - minPrice)
            {
                result = price - minPrice;
            }
            if (price < minPrice)
            {
                minPrice = price;
            }
        }
        return result;
    }
}