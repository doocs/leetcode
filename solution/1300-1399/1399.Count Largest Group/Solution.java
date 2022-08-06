class Solution {
    public int countLargestGroup(int n) {
        int[] cnt = new int[40];
        int mx = 0, ans = 0;
        for (int i = 1; i <= n; ++i) {
            int t = 0;
            int j = i;
            while (j != 0) {
                t += j % 10;
                j /= 10;
            }
            ++cnt[t];
            if (mx < cnt[t]) {
                mx = cnt[t];
                ans = 1;
            } else if (mx == cnt[t]) {
                ++ans;
            }
        }
        return ans;
    }
}