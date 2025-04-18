class Solution {
    public int makeArrayPositive(int[] nums) {
        int ans = 0;
        long preMx = 0, s = 0;
        for (int l = -1, r = 0; r < nums.length; r++) {
            int x = nums[r];
            s += x;
            if (r - l > 2 && s <= preMx) {
                ans++;
                l = r;
                preMx = s = 0;
            } else if (r - l >= 2) {
                preMx = Math.max(preMx, s - x - nums[r - 1]);
            }
        }
        return ans;
    }
}
