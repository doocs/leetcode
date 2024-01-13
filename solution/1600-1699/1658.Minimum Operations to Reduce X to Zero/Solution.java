class Solution {
    public int minOperations(int[] nums, int x) {
        x = -x;
        for (int v : nums) {
            x += v;
        }
        Map<Integer, Integer> vis = new HashMap<>();
        vis.put(0, -1);
        int n = nums.length;
        int ans = 1 << 30;
        for (int i = 0, s = 0; i < n; ++i) {
            s += nums[i];
            vis.putIfAbsent(s, i);
            if (vis.containsKey(s - x)) {
                int j = vis.get(s - x);
                ans = Math.min(ans, n - (i - j));
            }
        }
        return ans == 1 << 30 ? -1 : ans;
    }
}