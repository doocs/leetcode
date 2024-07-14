class Solution {
    public long minimumMoves(int[] nums, int k, int maxChanges) {
        int n = nums.length;
        int[] cnt = new int[n + 1];
        long[] s = new long[n + 1];
        for (int i = 1; i <= n; ++i) {
            cnt[i] = cnt[i - 1] + nums[i - 1];
            s[i] = s[i - 1] + i * nums[i - 1];
        }
        long ans = Long.MAX_VALUE;
        for (int i = 1; i <= n; ++i) {
            long t = 0;
            int need = k - nums[i - 1];
            for (int j = i - 1; j <= i + 1; j += 2) {
                if (need > 0 && 1 <= j && j <= n && nums[j - 1] == 1) {
                    --need;
                    ++t;
                }
            }
            int c = Math.min(need, maxChanges);
            need -= c;
            t += c * 2;
            if (need <= 0) {
                ans = Math.min(ans, t);
                continue;
            }
            int l = 2, r = Math.max(i - 1, n - i);
            while (l <= r) {
                int mid = (l + r) >> 1;
                int l1 = Math.max(1, i - mid), r1 = Math.max(0, i - 2);
                int l2 = Math.min(n + 1, i + 2), r2 = Math.min(n, i + mid);
                int c1 = cnt[r1] - cnt[l1 - 1];
                int c2 = cnt[r2] - cnt[l2 - 1];
                if (c1 + c2 >= need) {
                    long t1 = 1L * c1 * i - (s[r1] - s[l1 - 1]);
                    long t2 = s[r2] - s[l2 - 1] - 1L * c2 * i;
                    ans = Math.min(ans, t + t1 + t2);
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
        }
        return ans;
    }
}