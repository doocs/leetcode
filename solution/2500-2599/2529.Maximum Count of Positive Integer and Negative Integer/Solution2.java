class Solution {
    public int maximumCount(int[] nums) {
        int i = Arrays.binarySearch(nums, 1);
        int a = i < 0 ? nums.length - (-i - 1) : nums.length - i;
        int j = Arrays.binarySearch(nums, 0);
        int b = j < 0 ? -j - 1 : j;
        return Math.max(a, b);
    }
}