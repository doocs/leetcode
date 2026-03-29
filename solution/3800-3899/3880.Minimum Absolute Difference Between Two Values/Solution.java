class Solution {
    public int minAbsoluteDifference(int[] nums) {
        int n = nums.length;
        int ans = n + 1;
        int[] last = new int[3];
        Arrays.fill(last, -(n + 1));

        for (int i = 0; i < n; ++i) {
            int x = nums[i];
            if (x != 0) {
                ans = Math.min(ans, i - last[3 - x]);
                last[x] = i;
            }
        }
        return ans > n ? -1 : ans;
    }
}
