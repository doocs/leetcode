class Solution {
    public int scoreDifference(int[] nums) {
        int ans = 0;
        int k = 1;
        for (int i = 0; i < nums.length; ++i) {
            int x = nums[i];
            if ((x & 1) == 1) {
                k = -k;
            }
            if (i % 6 == 5) {
                k = -k;
            }
            ans += k * x;
        }
        return ans;
    }
}
