class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int res = 0;
        int cnt = 0;
        for (int num : nums) {
            if (num == 1) {
                res = Math.max(res, ++cnt);
            } else {
                cnt = 0;
            }
        }
        return res;
    }
}
