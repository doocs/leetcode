class Solution {
    public int reinitializePermutation(int n) {
        int ans = 0;
        for (int i = 1;;) {
            ++ans;
            if (i < (n >> 1)) {
                i <<= 1;
            } else {
                i = (i - (n >> 1)) << 1 | 1;
            }
            if (i == 1) {
                return ans;
            }
        }
    }
}