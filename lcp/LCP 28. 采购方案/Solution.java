class Solution {
    public int purchasePlans(int[] nums, int target) {
        Arrays.sort(nums);
        int res = 0, mod = 1000000007;
        for (int i = 0, j = nums.length - 1; i < j; ++i) {
            while (i < j && nums[i] + nums[j] > target) {
                --j;
            }
            if (i < j) {
                res = (res + j - i) % mod;
            }
        }
        return res % mod;
    }
}