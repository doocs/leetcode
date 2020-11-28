class Solution {
    fun findRepeatNumber(nums: IntArray): Int {
        for (i in nums.indices) {
            while (i != nums[i]) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                swap(nums, i, nums[i]);
            }
        }
        return -1;
    }

    fun swap(nums: IntArray, i: Int, j: Int) {
        var t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}