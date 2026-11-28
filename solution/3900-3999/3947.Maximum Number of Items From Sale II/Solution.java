class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] freq = new int[n + 1];
        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            freq[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

        int[] divCount = new int[n + 1];
        for (int f = 1; f <= n; f++) {
            if (freq[f] == 0) continue;
            for (int m = f; m <= n; m += f) {
                divCount[f] += freq[m];
            }
        }

        int[][] sortedItems = new int[n][2];
        for (int i = 0; i < n; i++) {
            sortedItems[i][0] = items[i][1];
            sortedItems[i][1] = divCount[items[i][0]] - 1;
        }

        Arrays.sort(sortedItems, Comparator.comparingInt(a -> a[0]));

        long rem = budget;
        long ans = 0;
        long limit = 2L * minPrice;

        for (int[] item : sortedItems) {
            int price = item[0];
            int gain = item[1];
            if (price >= limit) break;
            if (gain <= 0) continue;

            long canBuy = Math.min((long) gain, rem / price);
            ans += canBuy * 2;
            rem -= canBuy * price;

            if (canBuy < gain) break;
        }

        ans += rem / minPrice;
        return (int) ans;
    }
}