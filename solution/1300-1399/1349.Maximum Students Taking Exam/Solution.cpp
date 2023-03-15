class Solution {
public:
    int maxStudents(vector<vector<char>>& seats) {
        int m = seats.size();
        int n = seats[0].size();
        vector<int> ss(m);
        vector<vector<int>> f(1 << n, vector<int>(m, -1));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (seats[i][j] == '.') {
                    ss[i] |= 1 << j;
                }
            }
        }
        function<int(int, int)> dfs = [&](int seat, int i) -> int {
            if (f[seat][i] != -1) {
                return f[seat][i];
            }
            int ans = 0;
            for (int mask = 0; mask < 1 << n; ++mask) {
                if ((seat | mask) != seat || (mask & (mask << 1)) != 0) {
                    continue;
                }
                int cnt = __builtin_popcount(mask);
                if (i == m - 1) {
                    ans = max(ans, cnt);
                } else {
                    int nxt = ss[i + 1];
                    nxt &= ~(mask >> 1);
                    nxt &= ~(mask << 1);
                    ans = max(ans, cnt + dfs(nxt, i + 1));
                }
            }
            return f[seat][i] = ans;
        };
        return dfs(ss[0], 0);
    }
};