class Solution {
    private int n;
    private int[] f;
    private int[] nums;

    public boolean validPartition(int[] nums) {
        this.nums = nums;
        n = nums.length;
        f = new int[n];
        Arrays.fill(f, -1);
        return dfs(0);
    }

    private boolean dfs(int i) {
        if (i == n) {
            return true;
        }
        if (f[i] != -1) {
            return f[i] == 1;
        }
        boolean res = false;
        if (i < n - 1 && nums[i] == nums[i + 1]) {
            res = res || dfs(i + 2);
        }
        if (i < n - 2 && nums[i] == nums[i + 1] && nums[i + 1] == nums[i + 2]) {
            res = res || dfs(i + 3);
        }
        if (i < n - 2 && nums[i + 1] - nums[i] == 1 && nums[i + 2] - nums[i + 1] == 1) {
            res = res || dfs(i + 3);
        }
        f[i] = res ? 1 : 0;
        return res;
    }
}