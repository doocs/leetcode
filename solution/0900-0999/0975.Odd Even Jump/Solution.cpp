class Solution {
public:
    int oddEvenJumps(vector<int>& arr) {
        int n = arr.size();
        map<int, int> d;
        int f[n][2];
        int g[n][2];
        memset(f, 0, sizeof(f));
        for (int i = n - 1; ~i; --i) {
            auto it = d.lower_bound(arr[i]);
            g[i][1] = it == d.end() ? -1 : it->second;
            it = d.upper_bound(arr[i]);
            g[i][0] = it == d.begin() ? -1 : prev(it)->second;
            d[arr[i]] = i;
        }
        function<int(int, int)> dfs = [&](int i, int k) -> int {
            if (i == n - 1) {
                return 1;
            }
            if (g[i][k] == -1) {
                return 0;
            }
            if (f[i][k] != 0) {
                return f[i][k];
            }
            return f[i][k] = dfs(g[i][k], k ^ 1);
        };
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += dfs(i, 1);
        }
        return ans;
    }
};