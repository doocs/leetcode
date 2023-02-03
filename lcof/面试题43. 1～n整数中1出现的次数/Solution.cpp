class Solution {
public:
    int countDigitOne(int n) {
        int a[12]{};
        int f[12][12];
        memset(f, -1, sizeof f);
        int i = -1;
        for (; n; n /= 10) {
            a[++i] = n % 10;
        }
        function<int(int, int, bool)> dfs = [&](int pos, int cnt, bool limit) -> int {
            if (pos < 0) {
                return cnt;
            }
            if (!limit && f[pos][cnt] != -1) {
                return f[pos][cnt];
            }
            int up = limit ? a[pos] : 9;
            int ans = 0;
            for (int i = 0; i <= up; ++i) {
                ans += dfs(pos - 1, cnt + (i == 1), limit && i == up);
            }
            return f[pos][cnt] = ans;
        };
        return dfs(i, 0, true);
    }
};