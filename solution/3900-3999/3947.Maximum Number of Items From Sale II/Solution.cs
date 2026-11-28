public class Solution {
    public int MaximumSaleItems(int[][] items, int budget) {
        int n = items.Length;
        int[] freq = new int[n + 1];
        int minPrice = int.MaxValue;

        foreach (var item in items) {
            freq[item[0]]++;
            if (item[1] < minPrice) minPrice = item[1];
        }

        int[] divCount = new int[n + 1];
        for (int f = 1; f <= n; f++) {
            if (freq[f] == 0) continue;
            for (int m = f; m <= n; m += f) {
                divCount[f] += freq[m];
            }
        }

        var sortedItems = new (int price, int gain)[n];
        for (int i = 0; i < n; i++) {
            sortedItems[i] = (items[i][1], divCount[items[i][0]] - 1);
        }

        Array.Sort(sortedItems, (a, b) => a.price.CompareTo(b.price));

        long rem = budget;
        long ans = 0;
        long limit = 2L * minPrice;

        foreach (var (price, gain) in sortedItems) {
            if (price >= limit) break;
            if (gain <= 0) continue;

            long canBuy = Math.Min((long)gain, rem / price);
            ans += canBuy * 2;
            rem -= canBuy * price;

            if (canBuy < gain) break;
        }

        ans += rem / minPrice;
        return (int)ans;
    }
}