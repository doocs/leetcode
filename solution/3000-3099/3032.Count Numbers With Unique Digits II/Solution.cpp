class Solution {
public:
    int numberCount(int a, int b) {
        string num = to_string(b);
        int f[num.size()][1 << 10];
        memset(f, -1, sizeof(f));
        function<int(int, int, bool)> dfs = [&](int pos, int mask, bool limit) {
            if (pos >= num.size()) {
                return mask ? 1 : 0;
            }
            if (!limit && f[pos][mask] != -1) {
                return f[pos][mask];
            }
            int up = limit ? num[pos] - '0' : 9;
            int ans = 0;
            for (int i = 0; i <= up; ++i) {
                if (mask >> i & 1) {
                    continue;
                }
                int nxt = mask == 0 && i == 0 ? 0 : mask | 1 << i;
                ans += dfs(pos + 1, nxt, limit && i == up);
            }
            if (!limit) {
                f[pos][mask] = ans;
            }
            return ans;
        };

        int y = dfs(0, 0, true);
        num = to_string(a - 1);
        memset(f, -1, sizeof(f));
        int x = dfs(0, 0, true);
        return y - x;
    }
};