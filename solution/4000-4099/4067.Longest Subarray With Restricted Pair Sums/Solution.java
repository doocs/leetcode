class Solution {
    public int maxSubarray(int[] nums) {
        int mx = 0;
        for (int x : nums) {
            mx = Math.max(mx, x);
        }

        int[] cntS = new int[(mx << 1) | 1];
        int[] cntD = new int[mx + 1];
        int ans = 0;
        int l = 0;

        for (int r = 0; r < nums.length; r++) {
            int x = nums[r];
            while (cntS[x] > 0 || cntD[x] > 0) {
                int y = nums[l++];
                for (int i = l; i < r; i++) {
                    int z = nums[i];
                    cntS[y + z]--;
                    cntD[Math.abs(y - z)]--;
                }
            }

            for (int i = l; i < r; i++) {
                int y = nums[i];
                cntS[x + y]++;
                cntD[Math.abs(x - y)]++;
            }

            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }
}