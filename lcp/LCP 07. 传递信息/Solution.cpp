class Solution {
public:
    int numWays(int n, vector<vector<int>>& relation, int k) {
        int f[k + 1][n];
        memset(f, 0, sizeof(f));
        f[0][0] = 1;
        for (int i = 1; i <= k; ++i) {
            for (auto& r : relation) {
                int a = r[0], b = r[1];
                f[i][b] += f[i - 1][a];
            }
        }
        return f[k][n - 1];
    }
};