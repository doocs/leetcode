class Solution {
    public int[] rearrangeArray(int[] nums) {
        int mx = 0;
        for (int x : nums) {
            mx = Math.max(mx, x);
        }
        int[] cnt = new int[mx + 1];
        for (int x : nums) {
            cnt[x]++;
        }

        int[] ans = new int[nums.length];
        int idx = 0;
        while (idx < nums.length) {
            for (int x = 1; x <= mx; x++) {
                if (cnt[x] > 0) {
                    ans[idx++] = x;
                    cnt[x]--;
                }
            }
        }
        return ans;
    }
}