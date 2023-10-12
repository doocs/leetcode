class Solution {
    private int[] nums;
    private int x;
    private int y;

    public int minOperations(int[] nums, int x, int y) {
        this.nums = nums;
        this.x = x;
        this.y = y;
        int l = 0, r = 0;
        for (int v : nums) {
            r = Math.max(r, v);
        }
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private boolean check(int t) {
        long cnt = 0;
        for (int v : nums) {
            if (v > (long) t * y) {
                cnt += (v - (long) t * y + x - y - 1) / (x - y);
            }
        }
        return cnt <= t;
    }
}