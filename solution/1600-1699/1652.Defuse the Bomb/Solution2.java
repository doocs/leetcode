class Solution {
    public int[] decrypt(int[] code, int k) {
        int n = code.length;
        int[] ans = new int[n];
        if (k == 0) {
            return ans;
        }
        int[] s = new int[n << 1 | 1];
        for (int i = 0; i < n << 1; ++i) {
            s[i + 1] = s[i] + code[i % n];
        }
        for (int i = 0; i < n; ++i) {
            if (k > 0) {
                ans[i] = s[i + k + 1] - s[i + 1];
            } else {
                ans[i] = s[i + n] - s[i + k + n];
            }
        }
        return ans;
    }
}