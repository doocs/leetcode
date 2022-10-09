class Solution {
public:
    int minimumWhiteTiles(string floor, int numCarpets, int carpetLen) {
        int n = floor.size();
        vector<vector<int>> f(n, vector<int>(numCarpets + 1, -1));
        vector<int> s(n + 1);
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + (floor[i] == '1');
        }
        function<int(int, int)> dfs;
        dfs = [&](int i, int j) {
            if (i >= n) return 0;
            if (j == 0) return s[n] - s[i];
            if (f[i][j] != -1) return f[i][j];
            if (s[i + 1] == s[i]) return dfs(i + 1, j);
            int ans = min(1 + dfs(i + 1, j), dfs(i + carpetLen, j - 1));
            f[i][j] = ans;
            return ans;
        };
        return dfs(0, numCarpets);
    }
};