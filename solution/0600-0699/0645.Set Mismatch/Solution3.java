class Solution {
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        int xs = 0;
        for (int i = 1; i <= n; ++i) {
            xs ^= i ^ nums[i - 1];
        }
        int lb = xs & -xs;
        int a = 0;
        for (int i = 1; i <= n; ++i) {
            if ((i & lb) > 0) {
                a ^= i;
            }
            if ((nums[i - 1] & lb) > 0) {
                a ^= nums[i - 1];
            }
        }
        int b = xs ^ a;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == a) {
                return new int[] {a, b};
            }
        }
        return new int[] {b, a};
    }
}