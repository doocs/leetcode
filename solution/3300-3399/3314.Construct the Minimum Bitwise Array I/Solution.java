class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            int x = nums.get(i);
            if (x == 2) {
                ans[i] = -1;
            } else {
                for (int j = 1; j < 32; ++j) {
                    if ((x >> j & 1) == 0) {
                        ans[i] = x ^ 1 << (j - 1);
                        break;
                    }
                }
            }
        }
        return ans;
    }
}
