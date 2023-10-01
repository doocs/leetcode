class Solution {
    public int maxSubarrays(int[] nums) {
        int score = -1;
        int ans = 1;
        for (int num : nums) {
            score &= num;
            if (score == 0) {
                ans++;
                score = -1;
            }
        }
        return ans == 1 ? 1 : ans - 1;
    }
}