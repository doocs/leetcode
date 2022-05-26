class Solution {
    public int sumOfDigits(int[] nums) {
        int x = nums[0];
        for (int v : nums) {
            x = Math.min(x, v);
        }
        int s = 0;
        while (x != 0) {
            s += x % 10;
            x /= 10;
        }
        return 1 - s % 2;
    }
}