class Solution {
    public int longestBalanced(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            Set<Integer> vis = new HashSet<>();
            int[] cnt = new int[2];
            for (int j = i; j < n; ++j) {
                if (vis.add(nums[j])) {
                    ++cnt[nums[j] & 1];
                }
                if (cnt[0] == cnt[1]) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }
        return ans;
    }
}
