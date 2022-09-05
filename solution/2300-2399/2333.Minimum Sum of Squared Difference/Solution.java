class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int[] d = new int[n];
        long s = 0;
        int mx = 0;
        int k = k1 + k2;
        for (int i = 0; i < n; ++i) {
            d[i] = Math.abs(nums1[i] - nums2[i]);
            s += d[i];
            mx = Math.max(mx, d[i]);
        }
        if (s <= k) {
            return 0;
        }
        int left = 0, right = mx;
        while (left < right) {
            int mid = (left + right) >> 1;
            long t = 0;
            for (int v : d) {
                t += Math.max(v - mid, 0);
            }
            if (t <= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        for (int i = 0; i < n; ++i) {
            k -= Math.max(0, d[i] - left);
            d[i] = Math.min(d[i], left);
        }
        for (int i = 0; i < n && k > 0; ++i) {
            if (d[i] == left) {
                --k;
                --d[i];
            }
        }
        long ans = 0;
        for (int v : d) {
            ans += (long) v * v;
        }
        return ans;
    }
}