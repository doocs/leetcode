class Solution {
    public int[] countOppositeParity(int[] nums) {
        int[] cnt = new int[2];
        for (int x : nums) {
            cnt[x & 1]++;
        }
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            cnt[nums[i] & 1]--;
            ans[i] = cnt[nums[i] & 1 ^ 1];
        }
        return ans;
    }
}
