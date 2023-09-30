class Solution {
    public long maximumBeauty(int[] flowers, long newFlowers, int target, int full, int partial) {
        Arrays.sort(flowers);
        int n = flowers.length;
        long[] s = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + flowers[i];
        }
        long ans = 0;
        int x = 0;
        for (int v : flowers) {
            if (v >= target) {
                ++x;
            }
        }
        for (; x <= n; ++x) {
            newFlowers -= (x == 0 ? 0 : Math.max(target - flowers[n - x], 0));
            if (newFlowers < 0) {
                break;
            }
            int l = 0, r = n - x - 1;
            while (l < r) {
                int mid = (l + r + 1) >> 1;
                if ((long) flowers[mid] * (mid + 1) - s[mid + 1] <= newFlowers) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            long y = 0;
            if (r != -1) {
                long cost = (long) flowers[l] * (l + 1) - s[l + 1];
                y = Math.min(flowers[l] + (newFlowers - cost) / (l + 1), target - 1);
            }
            ans = Math.max(ans, (long) x * full + y * partial);
        }
        return ans;
    }
}