class Solution {
public:
    int minCost(int n, vector<int>& cuts) {
        cuts.push_back(0);
        cuts.push_back(n);
        sort(cuts.begin(), cuts.end());
        int m = cuts.size();
        int f[110][110]{};
        for (int i = m - 1; ~i; --i) {
            for (int j = i + 2; j < m; ++j) {
                f[i][j] = 1 << 30;
                for (int k = i + 1; k < j; ++k) {
                    f[i][j] = min(f[i][j], f[i][k] + f[k][j] + cuts[j] - cuts[i]);
                }
            }
        }
        return f[0][m - 1];
    }
};