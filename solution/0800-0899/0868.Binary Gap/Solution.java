class Solution {
    public int binaryGap(int n) {
        int ans = 0;
        for (int i = 0, j = -1; n != 0; ++i, n >>= 1) {
            if ((n & 1) == 1) {
                if (j != -1) {
                    ans = Math.max(ans, i - j);
                }
                j = i;
            }
        }
        return ans;
    }
}