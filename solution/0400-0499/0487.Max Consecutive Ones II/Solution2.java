class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int j = 0, cnt = 0;
        int ans = 1;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == 0) {
                ++cnt;
            }
            while (cnt > 1) {
                if (nums[j++] == 0) {
                    --cnt;
                }
            }
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    }
}