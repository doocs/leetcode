class Solution {
    public int arrayNesting(int[] nums) {
        int ans = 0, n = nums.length;
        for (int i = 0; i < n; ++i) {
            int cnt = 0;
            int j = i;
            while (nums[j] < n) {
                int k = nums[j];
                nums[j] = n;
                j = k;
                ++cnt;
            }
            ans = Math.max(ans, cnt);
        }
        return ans;
    }
}
