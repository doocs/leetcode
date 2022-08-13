class Solution {
public:
    const int mod = 1e9 + 7;

    int countRoutes(vector<int>& locations, int start, int finish, int fuel) {
        int n = locations.size();
        vector<vector<int>> f(n + 1, vector<int>(fuel + 1, -1));
        return dfs(start, fuel, locations, finish, f);
    }

    int dfs(int i, int t, vector<int>& locations, int target, vector<vector<int>>& f) {
        if (f[i][t] != -1) return f[i][t];
        if (abs(locations[i] - locations[target]) > t) return 0;
        int res = i == target;
        for (int j = 0; j < locations.size(); ++j) {
            if (j == i) continue;
            int cost = abs(locations[i] - locations[j]);
            if (cost <= t) res = (res + dfs(j, t - cost, locations, target, f)) % mod;
        }
        f[i][t] = res;
        return res;
    }
};