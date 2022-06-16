class Solution {
    public int findPairs(int[] nums, int k) {
        Set<Integer> vis = new HashSet<>();
        Set<Integer> ans = new HashSet<>();
        for (int v : nums) {
            if (vis.contains(v - k)) {
                ans.add(v - k);
            }
            if (vis.contains(v + k)) {
                ans.add(v);
            }
            vis.add(v);
        }
        return ans.size();
    }
}