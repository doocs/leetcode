class Solution {
    public long maxProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        long a = nums[0], b = nums[1];
        long c = nums[n - 2], d = nums[n - 1];
        final int x = 100000;
        return Math.max(Math.max(a * b * x, c * d * x), -a * d * x);
    }
}
