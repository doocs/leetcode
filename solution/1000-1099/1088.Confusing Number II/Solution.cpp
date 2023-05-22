class Solution {
public:
    int confusingNumberII(int n) {
        string s = to_string(n);
        int d[10] = {0, 1, -1, -1, -1, -1, 9, -1, 8, 6};
        auto check = [&](int x) -> bool {
            int y = 0;
            for (int t = x; t; t /= 10) {
                int v = t % 10;
                y = y * 10 + d[v];
            }
            return x != y;
        };
        function<int(int, int, int)> dfs = [&](int pos, int limit, int x) -> int {
            if (pos >= s.size()) {
                return check(x);
            }
            int up = limit ? s[pos] - '0' : 9;
            int ans = 0;
            for (int i = 0; i <= up; ++i) {
                if (d[i] != -1) {
                    ans += dfs(pos + 1, limit && i == up, x * 10 + i);
                }
            }
            return ans;
        };
        return dfs(0, 1, 0);
    }
};