class Solution {
    public long maxGCDScore(int[] nums, int k) {
        int n = nums.length;
        int[] cnt = new int[n];
        for (int i = 0; i < n; ++i) {
            for (int x = nums[i]; x % 2 == 0; x /= 2) {
                ++cnt[i];
            }
        }
        long ans = 0;
        for (int l = 0; l < n; ++l) {
            int g = 0;
            int mi = 1 << 30;
            int t = 0;
            for (int r = l; r < n; ++r) {
                g = gcd(g, nums[r]);
                if (cnt[r] < mi) {
                    mi = cnt[r];
                    t = 1;
                } else if (cnt[r] == mi) {
                    ++t;
                }
                ans = Math.max(ans, (r - l + 1L) * (t > k ? g : g * 2));
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}