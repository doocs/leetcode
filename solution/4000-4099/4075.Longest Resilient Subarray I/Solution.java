class Solution {
    public int resilientSubarray(int[] nums, int k) {
        int ans = 0, cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i] % k;
            if (i > 0 && nums[i - 1] % k == x) {
                cnt++;
            } else {
                cnt = 1;
            }
            if (x * (cnt - 1) % k == 0) {
                ans = Math.max(ans, cnt);
            }
        }
        return ans;
    }
}
