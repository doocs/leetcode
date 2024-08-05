class Solution {
public:
    int findIntegers(int n) {
        string s = bitset<32>(n).to_string();
        s = s.substr(s.find('1'));
        int m = s.size();
        int f[m][2];
        memset(f, -1, sizeof(f));
        auto dfs = [&](auto&& dfs, int pos, int pre, bool limit) -> int {
            if (pos >= m) {
                return 1;
            }
            if (!limit && f[pos][pre] != -1) {
                return f[pos][pre];
            }
            int up = limit ? s[pos] - '0' : 1;
            int ans = 0;
            for (int i = 0; i <= up; ++i) {
                if (!(pre == 1 && i == 1)) {
                    ans += dfs(dfs, pos + 1, i, limit && i == up);
                }
            }
            if (!limit) {
                f[pos][pre] = ans;
            }
            return ans;
        };
        return dfs(dfs, 0, 0, true);
    }
};
