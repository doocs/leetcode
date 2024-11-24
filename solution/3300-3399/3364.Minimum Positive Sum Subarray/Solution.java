class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        final int inf = Integer.MAX_VALUE;
        int ans = inf;
        for (int i = 0; i < n; ++i) {
            int s = 0;
            for (int j = i; j < n; ++j) {
                s += nums.get(j);
                int k = j - i + 1;
                if (k >= l && k <= r && s > 0) {
                    ans = Math.min(ans, s);
                }
            }
        }
        return ans == inf ? -1 : ans;
    }
}
