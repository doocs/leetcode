class Solution {
    public int subarrayLCM(int[] nums, int k) {
        int ans = 0;
        for (int i = 0; i < nums.length; ++i) {
            int a = 1;
            for (int j = i; j < nums.length; ++j) {
                if (k % nums[j] != 0) {
                    break;
                }
                a = lcm(a, nums[j]);
                if (a == k) {
                    ++ans;
                }
            }
        }
        return ans;
    }

    private int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
