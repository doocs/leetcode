class Solution {
    public int[] missingTwo(int[] nums) {
        int res = 0, n = nums.length;
        for (int i = 0; i < n; ++i) {
            res ^= nums[i];
            res ^= (i + 1);
        }
        res ^= (n + 1);
        res ^= (n + 2);

        int pos = 0;
        while ((res & 1) == 0) {
            pos += 1;
            res >>= 1;
        }

        int a = 0, b = 0;
        for (int num : nums) {
            int t = num >> pos;
            if ((t & 1) == 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }
        for (int i = 1; i <= n + 2; ++i) {
            int t = i >> pos;
            if ((t & 1) == 0) {
                a ^= i;
            } else {
                b ^= i;
            }
        }
        return new int[] { a, b };
    }
}