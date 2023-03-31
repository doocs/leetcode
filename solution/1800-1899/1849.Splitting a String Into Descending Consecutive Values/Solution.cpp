class Solution {
public:
    bool splitString(string s) {
        function<bool(int, long long, int)> dfs = [&](int i, long long x, int k) -> bool {
            if (i == s.size()) {
                return k > 1;
            }
            long long y = 0;
            for (int j = i; j < s.size(); ++j) {
                y = y * 10 + (s[j] - '0');
                if (y > 1e10) {
                    break;
                }
                if ((x == -1 || x - y == 1) && dfs(j + 1, y, k + 1)) {
                    return true;
                }
            }
            return false;
        };
        return dfs(0, -1, 0);
    }
};