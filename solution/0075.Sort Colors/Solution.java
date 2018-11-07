class Solution {
    public void sortColors(int[] nums) {
        int p = -1;
        int q = nums.length;
        int cur = 0;
        while (cur < q) {
            if (nums[cur] == 0) {
                swap(nums, cur++, ++p);
            } else if (nums[cur] == 1) {
                ++cur;
            } else {
                swap(nums, --q, cur);
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}