class Solution {
public:
    int maxWeight(vector<int>& weights, int w1, int w2) {
        vector<vector<int>> f(w1 + 1, vector<int>(w2 + 1));
        for (int x : weights) {
            for (int j = w1; j >= 0; --j) {
                for (int k = w2; k >= 0; --k) {
                    if (x <= j) {
                        f[j][k] = max(f[j][k], f[j - x][k] + x);
                    }
                    if (x <= k) {
                        f[j][k] = max(f[j][k], f[j][k - x] + x);
                    }
                }
            }
        }
        return f[w1][w2];
    }
};
