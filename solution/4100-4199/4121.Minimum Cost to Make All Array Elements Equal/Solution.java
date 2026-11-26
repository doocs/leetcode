class Solution {
    public long minCost(int[] nums) {
        int n = nums.length;
        long ans = Long.MAX_VALUE;
        int l = 0;
        for (int r = 0; r < n; r++) {
            int x = nums[r];
            if (r == n - 1 || x != nums[r + 1]) {
                if (r > 0 && l < n - 1) {
                    ans = Math.min(ans, (long) (l + n - r - 1) * x);
                }
                l = r + 1;
            }
        }
        return ans;
    }
}