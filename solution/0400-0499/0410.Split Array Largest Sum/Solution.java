class Solution {
    public int splitArray(int[] nums, int m) {
        long l = 0, r = 0;
        for (int x : nums) {
            l = Math.max(l, x);
            r += x;
        }
        while (l < r) {
            long mid = l + r >>> 1;
            if (check(nums, m, mid)) r = mid;
            else l = mid + 1;
        }
        return (int) r;
    }

    private boolean check(int[] nums, int m, long cap) {
        int cnt = 1;
        long tot = 0;
        for (int x : nums) {
            tot += x;
            if (tot > cap) {
                ++cnt;
                tot = x;
            }
        }
        return cnt <= m;
    }
}
