class Solution {
    public int removeElement(int[] nums, int val) {
        int res = 0, length = nums.length;
        for (int i = 0; i < length; i ++) {
            if (nums[i] != val) {
                nums[res] = nums[i];
                res ++;
            }
        }
        return res;
    }
}