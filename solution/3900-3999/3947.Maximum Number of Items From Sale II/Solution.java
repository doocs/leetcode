import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int maxF = 0, minP = Integer.MAX_VALUE;

        for (int[] item : items) {
            maxF = Math.max(maxF, item[0]);
            minP = Math.min(minP, item[1]);
        }

        int[] freq = new int[maxF + 1];
        int[] cnt = new int[maxF + 1];

        for (int[] item : items) {
            freq[item[0]]++;
        }

        for (int f = 1; f <= maxF; f++) {
            for (int j = f; j <= maxF; j += f) {
                cnt[f] += freq[j];
            }
            cnt[f]--;
        }

        List<int[]> deals = new ArrayList<>();

        for (int[] item : items) {
            int f = item[0], p = item[1];

            if (cnt[f] > 0 && p <= 2 * minP) {
                deals.add(new int[]{p, cnt[f]});
            }
        }

        deals.sort(Comparator.comparingInt(a -> a[0]));

        int ans = 0;

        for (int[] deal : deals) {
            int take = Math.min(deal[1], budget / deal[0]);
            ans += take * 2;
            budget -= take * deal[0];
        }

        return ans + budget / minP;
    }
}
