class Solution {
    public int findMagicIndex(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }

    private int dfs(int[] nums, int i, int j) {
        if (i > j) {
            return -1;
        }
        int mid = (i + j) >> 1;
        int l = dfs(nums, i, mid - 1);
        if (l != -1) {
            return l;
        }
        if (nums[mid] == mid) {
            return mid;
        }
        return dfs(nums, mid + 1, j);
    }
}