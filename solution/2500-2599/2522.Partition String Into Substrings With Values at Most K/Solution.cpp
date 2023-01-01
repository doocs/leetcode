class Solution {
public:
    int minimumPartition(string s, int k) {
        int n = s.size();
        int f[n];
        memset(f, 0, sizeof f);
        const int inf = 1 << 30;
        function<int(int)> dfs = [&](int i) -> int {
            if (i >= n) return 0;
            if (f[i]) return f[i];
            int res = inf;
            long v = 0;
            for (int j = i; j < n; ++j) {
                v = v * 10 + (s[j] - '0');
                if (v > k) break;
                res = min(res, dfs(j + 1));
            }
            return f[i] = res + 1;
        };
        int ans = dfs(0);
        return ans < inf ? ans : -1;
    }
};