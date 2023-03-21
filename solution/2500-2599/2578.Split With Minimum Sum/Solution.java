class Solution {
    public int splitNum(int num) {
        int[] cnt = new int[10];
        int n = 0;
        for (; num > 0; num /= 10) {
            ++cnt[num % 10];
            ++n;
        }
        int[] ans = new int[2];
        for (int i = 0, j = 0; i < n; ++i) {
            while (cnt[j] == 0) {
                ++j;
            }
            --cnt[j];
            ans[i & 1] = ans[i & 1] * 10 + j;
        }
        return ans[0] + ans[1];
    }
}