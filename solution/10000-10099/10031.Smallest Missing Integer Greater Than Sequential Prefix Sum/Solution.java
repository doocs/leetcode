class Solution {
    public int missingInteger(int[] nums) {
        int s = nums[0], j = 1;
        while (j < nums.length && nums[j] == nums[j - 1] + 1) {
            s += nums[j++];
        }
        boolean[] vis = new boolean[51];
        for (int x : nums) {
            vis[x] = true;
        }
        for (int x = s;; ++x) {
            if (x >= vis.length || !vis[x]) {
                return x;
            }
        }
    }
}