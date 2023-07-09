class Solution {
public:
    int minimumBeautifulSubstrings(string s) {
        unordered_set<long long> ss;
        int n = s.size();
        long long x = 1;
        for (int i = 0; i <= n; ++i) {
            ss.insert(x);
            x *= 5;
        }
        int f[n];
        memset(f, -1, sizeof(f));
        function<int(int)> dfs = [&](int i) {
            if (i >= n) {
                return 0;
            }
            if (s[i] == '0') {
                return n + 1;
            }
            if (f[i] != -1) {
                return f[i];
            }
            long long x = 0;
            int ans = n + 1;
            for (int j = i; j < n; ++j) {
                x = x << 1 | (s[j] - '0');
                if (ss.count(x)) {
                    ans = min(ans, 1 + dfs(j + 1));
                }
            }
            return f[i] = ans;
        };
        int ans = dfs(0);
        return ans > n ? -1 : ans;
    }
};