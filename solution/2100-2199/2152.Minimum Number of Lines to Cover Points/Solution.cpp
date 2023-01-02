class Solution {
public:
    int minimumLines(vector<vector<int>>& points) {
        auto check = [&](int i, int j, int k) {
            int x1 = points[i][0], y1 = points[i][1];
            int x2 = points[j][0], y2 = points[j][1];
            int x3 = points[k][0], y3 = points[k][1];
            return (x2 - x1) * (y3 - y1) == (x3 - x1) * (y2 - y1);
        };
        int n = points.size();
        int f[1 << n];
        memset(f, 0, sizeof f);
        function<int(int)> dfs = [&](int state) -> int {
            if (state == (1 << n) - 1) return 0;
            if (f[state]) return f[state];
            int ans = 1 << 30;
            for (int i = 0; i < n; ++i) {
                if (!(state >> i & 1)) {
                    for (int j = i + 1; j < n; ++j) {
                        int nxt = state | 1 << i | 1 << j;
                        for (int k = j + 1; k < n; ++k) {
                            if (!(nxt >> k & 1) && check(i, j, k)) {
                                nxt |= 1 << k;
                            }
                        }
                        ans = min(ans, dfs(nxt) + 1);
                    }
                    if (i == n - 1) {
                        ans = min(ans, dfs(state | 1 << i) + 1);
                    }
                }
            }
            return f[state] = ans;
        };
        return dfs(0);
    }
};