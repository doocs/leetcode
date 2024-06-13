class Solution {
    private int[] nums;
    private Integer[] f;

    public int maxTotalReward(int[] rewardValues) {
        nums = rewardValues;
        Arrays.sort(nums);
        int n = nums.length;
        f = new Integer[nums[n - 1] << 1];
        return dfs(0);
    }

    private int dfs(int x) {
        if (f[x] != null) {
            return f[x];
        }
        int i = Arrays.binarySearch(nums, x + 1);
        i = i < 0 ? -i - 1 : i;
        int ans = 0;
        for (; i < nums.length; ++i) {
            ans = Math.max(ans, nums[i] + dfs(x + nums[i]));
        }
        return f[x] = ans;
    }
}