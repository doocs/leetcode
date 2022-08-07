class Solution {
    public int arithmeticTriplets(int[] nums, int diff) {
        boolean[] vis = new boolean[310];
        for (int v : nums) {
            vis[v] = true;
        }
        int ans = 0;
        for (int v : nums) {
            if (vis[v + diff] && vis[v + diff + diff]) {
                ++ans;
            }
        }
        return ans;
    }
}