class Solution {
    public int reductionOperations(int[] nums) {
        Arrays.sort(nums);
        int cnt = 0, res = 0, n = nums.length;
        for (int i = 1; i < n; ++i) {
            if (nums[i] != nums[i - 1]) {
                ++cnt;
            }
            res += cnt;
        }
        return res;
    }
}