class Solution {
    public int minOperations(int[] nums, int x) {
        int s = -x;
        for (int v : nums) {
            s += v;
        }
        Map<Integer, Integer> vis = new HashMap<>();
        vis.put(0, -1);
        int mx = -1, t = 0;
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            t += nums[i];
            vis.putIfAbsent(t, i);
            if (vis.containsKey(t - s)) {
                mx = Math.max(mx, i - vis.get(t - s));
            }
        }
        return mx == -1 ? -1 : n - mx;
    }
}