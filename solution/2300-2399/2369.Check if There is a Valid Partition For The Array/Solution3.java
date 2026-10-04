class Solution {
    public boolean validPartition(int[] nums) {
        int n = nums.length;
        boolean[] f = new boolean[n + 1];
        f[n] = true;
        for (int i = n - 1; i >= 0; --i) {
            boolean a = i + 1 < n && nums[i] == nums[i + 1];
            boolean b = i + 2 < n && nums[i] == nums[i + 1] && nums[i + 1] == nums[i + 2];
            boolean c = i + 2 < n && nums[i + 1] - nums[i] == 1 && nums[i + 2] - nums[i + 1] == 1;
            f[i] = (a && f[i + 2]) || ((b || c) && f[i + 3]);
        }
        return f[0];
    }
}
