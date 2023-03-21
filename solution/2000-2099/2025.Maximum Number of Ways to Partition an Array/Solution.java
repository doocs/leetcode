class Solution {
    public int waysToPartition(int[] nums, int k) {
        int n = nums.length;
        int[] s = new int[n];
        s[0] = nums[0];
        Map<Integer, Integer> right = new HashMap<>();
        for (int i = 0; i < n - 1; ++i) {
            right.merge(s[i], 1, Integer::sum);
            s[i + 1] = s[i] + nums[i + 1];
        }
        int ans = 0;
        if (s[n - 1] % 2 == 0) {
            ans = right.getOrDefault(s[n - 1] / 2, 0);
        }
        Map<Integer, Integer> left = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            int d = k - nums[i];
            if ((s[n - 1] + d) % 2 == 0) {
                int t = left.getOrDefault((s[n - 1] + d) / 2, 0)
                    + right.getOrDefault((s[n - 1] - d) / 2, 0);
                ans = Math.max(ans, t);
            }
            left.merge(s[i], 1, Integer::sum);
            right.merge(s[i], -1, Integer::sum);
        }
        return ans;
    }
}