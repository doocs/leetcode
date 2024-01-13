class Solution {
public:
    bool canCross(vector<int>& stones) {
        int n = stones.size();
        bool f[n][n];
        memset(f, false, sizeof(f));
        f[0][0] = true;
        for (int i = 1; i < n; ++i) {
            for (int j = i - 1; j >= 0; --j) {
                int k = stones[i] - stones[j];
                if (k - 1 > j) {
                    break;
                }
                f[i][k] = f[j][k - 1] || f[j][k] || f[j][k + 1];
                if (i == n - 1 && f[i][k]) {
                    return true;
                }
            }
        }
        return false;
    }
};