class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int ans = 0, cnt = 0;
        for (int x : nums) {
            if (x == 1) {
                ans = Math.max(ans, ++cnt);
            } else {
                cnt = 0;
            }
        }
        return ans;
    }
}