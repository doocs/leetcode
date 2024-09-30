class Solution {
public:
    bool splitString(string s) {
        auto dfs = [&](auto&& dfs, int i, long long x) -> bool {
            if (i >= s.size()) {
                return true;
            }
            long long y = 0;
            int r = x < 0 ? s.size() - 1 : s.size();
            for (int j = i; j < r; ++j) {
                y = y * 10 + s[j] - '0';
                if (y > 1e10) {
                    break;
                }
                if ((x < 0 || x - y == 1) && dfs(dfs, j + 1, y)) {
                    return true;
                }
            }
            return false;
        };
        return dfs(dfs, 0, -1);
    }
};
