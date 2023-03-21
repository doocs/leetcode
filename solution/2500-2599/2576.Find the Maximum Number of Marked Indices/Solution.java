class Solution {
    public int maxNumOfMarkedIndices(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = 0;
        for (int i = 0, j = (n + 1) / 2; j < n; ++i, ++j) {
            while (j < n && nums[i] * 2 > nums[j]) {
                ++j;
            }
            if (j < n) {
                ans += 2;
            }
        }
        return ans;
    }
}