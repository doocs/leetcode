class Solution {
    public int maximumUniqueSubarray(int[] nums) {
        Set<Integer> vis = new HashSet<>();
        int ans = 0, s = 0, i = 0;
        for (int x : nums) {
            while (vis.contains(x)) {
                s -= nums[i];
                vis.remove(nums[i++]);
            }
            vis.add(x);
            s += x;
            ans = Math.max(ans, s);
        }
        return ans;
    }
}