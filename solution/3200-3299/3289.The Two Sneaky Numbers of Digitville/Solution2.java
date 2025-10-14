class Solution {
    public int[] getSneakyNumbers(int[] nums) {
        int n = nums.length - 2;
        int xx = nums[n] ^ nums[n + 1];
        for (int i = 0; i < n; ++i) {
            xx ^= i ^ nums[i];
        }
        int k = Integer.numberOfTrailingZeros(xx);
        int[] ans = new int[2];
        for (int x : nums) {
            ans[x >> k & 1] ^= x;
        }
        for (int i = 0; i < n; ++i) {
            ans[i >> k & 1] ^= i;
        }
        return ans;
    }
}
