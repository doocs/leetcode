class Solution {
public:
    int countDigitOne(int n) {
        string s = to_string(n);
        int m = s.size();
        int f[m][m];
        memset(f, -1, sizeof(f));
        auto dfs = [&](this auto&& dfs, int i, int cnt, bool limit) -> int {
            if (i >= m) {
                return cnt;
            }
            if (!limit && f[i][cnt] != -1) {
                return f[i][cnt];
            }
            int up = limit ? s[i] - '0' : 9;
            int ans = 0;
            for (int j = 0; j <= up; ++j) {
                ans += dfs(i + 1, cnt + (j == 1), limit && j == up);
            }
            if (!limit) {
                f[i][cnt] = ans;
            }
            return ans;
        };
        return dfs(0, 0, true);
    }
};
