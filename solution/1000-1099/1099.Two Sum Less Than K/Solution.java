class Solution {
    public int twoSumLessThanK(int[] nums, int k) {
        Arrays.sort(nums);
        int low = 0, high = nums.length - 1;
        int res = -1;
        while (low < high) {
            int val = nums[low] + nums[high];
            if (val < k) {
                res = Math.max(res, val);
                ++low;
            } else {
                --high;
            }
        }
        return res;
    }
}