class Solution {
public:
    int minFallingPathSum(vector<vector<int>>& matrix) {
        int n = matrix.size();
        vector<int> f(n);
        for (auto& row : matrix) {
            auto g = f;
            for (int j = 0; j < n; ++j) {
                if (j) {
                    g[j] = min(g[j], f[j - 1]);
                }
                if (j + 1 < n) {
                    g[j] = min(g[j], f[j + 1]);
                }
                g[j] += row[j];
            }
            f = move(g);
        }
        return *min_element(f.begin(), f.end());
    }
};