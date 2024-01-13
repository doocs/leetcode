class Solution {
    public int countQuadruplets(int[] nums) {
        int ans = 0, n = nums.length;
        int[] counter = new int[310];
        for (int c = n - 2; c > 1; --c) {
            ++counter[nums[c + 1]];
            for (int a = 0; a < c - 1; ++a) {
                for (int b = a + 1; b < c; ++b) {
                    ans += counter[nums[a] + nums[b] + nums[c]];
                }
            }
        }
        return ans;
    }
}