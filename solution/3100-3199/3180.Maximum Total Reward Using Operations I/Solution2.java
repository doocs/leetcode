class Solution {
    public int maxTotalReward(int[] rewardValues) {
        int[] nums = Arrays.stream(rewardValues).distinct().sorted().toArray();
        int n = nums.length;
        int m = nums[n - 1] << 1;
        boolean[] f = new boolean[m];
        f[0] = true;
        for (int v : nums) {
            for (int j = 0; j < m; ++j) {
                if (0 <= j - v && j - v < v) {
                    f[j] |= f[j - v];
                }
            }
        }
        int ans = m - 1;
        while (!f[ans]) {
            --ans;
        }
        return ans;
    }
}