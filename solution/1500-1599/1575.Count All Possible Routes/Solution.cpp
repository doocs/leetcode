class Solution {
public:
    int countRoutes(vector<int>& locations, int start, int finish, int fuel) {
        int n = locations.size();
        int f[n][fuel + 1];
        memset(f, -1, sizeof(f));
        const int mod = 1e9 + 7;
        function<int(int, int)> dfs = [&](int i, int k) -> int {
            if (k < 0 || abs(locations[i] - locations[finish]) > k) {
                return 0;
            }
            if (f[i][k] != -1) {
                return f[i][k];
            }
            int ans = i == finish;
            for (int j = 0; j < n; ++j) {
                if (j != i) {
                    ans = (ans + dfs(j, k - abs(locations[i] - locations[j]))) % mod;
                }
            }
            return f[i][k] = ans;
        };
        return dfs(start, fuel);
    }
};