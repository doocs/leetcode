class Solution {
    public void sortColors(int[] nums) {
        int i = -1, j = nums.length;
        int cur = 0;
        while (cur < j) {
            if (nums[cur] == 0) {
                swap(nums, cur++, ++i);
            } else if (nums[cur] == 1) {
                ++cur;
            } else {
                swap(nums, cur, --j);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}