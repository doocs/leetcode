class Solution {
    public int[] drawLine(int length, int w, int x1, int x2, int y) {
        int[] ans = new int[length];
        int i = (y * w + x1) / 32;
        int j = (y * w + x2) / 32;
        for (int k = i; k <= j; ++k) {
            ans[k] = -1;
        }
        ans[i] = ans[i] >>> (x1 % 32);
        ans[j] &= 0x80000000 >> (x2 % 32);
        return ans;
    }
}