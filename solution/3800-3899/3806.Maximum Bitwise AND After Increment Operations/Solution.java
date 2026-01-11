class Solution {
    public int maximumAND(int[] nums, int k, int m) {
        int max = 0;
        for (int x : nums) {
            max = Math.max(max, x);
        }
        max += k;

        int mx = 32 - Integer.numberOfLeadingZeros(max);
        int n = nums.length;

        int ans = 0;
        int[] cost = new int[n];

        for (int bit = mx - 1; bit >= 0; bit--) {
            int target = ans | (1 << bit);
            for (int i = 0; i < n; i++) {
                int x = nums[i];
                int diff = target & ~x;
                int j = diff == 0 ? 0 : 32 - Integer.numberOfLeadingZeros(diff);
                int mask = (1 << j) - 1;
                cost[i] = (target & mask) - (x & mask);
            }
            Arrays.sort(cost);
            long sum = 0;
            for (int i = 0; i < m; i++) {
                sum += cost[i];
            }
            if (sum <= k) {
                ans = target;
            }
        }

        return ans;
    }
}
