class Solution {
    public int minOperations(int[] nums, int[] numsDivide) {
        int x = numsDivide[0];
        for (int i = 1; i < numsDivide.length; ++i) {
            x = gcd(x, numsDivide[i]);
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; ++i) {
            if (x % nums[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}