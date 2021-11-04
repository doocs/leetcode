class Solution {
    public int minimumDifference(int[] nums, int k) {
        if (k == 1) {
            return 0;
        }
        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - k + 1; i++) {
            min = Math.min((nums[i + k - 1] - nums[i]), min);
        }
        return min;
    }
}