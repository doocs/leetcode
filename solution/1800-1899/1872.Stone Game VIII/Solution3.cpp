class Solution {
public:
    int stoneGameVIII(vector<int>& stones) {
        int n = stones.size();
        for (int i = 1; i < n; ++i) {
            stones[i] += stones[i - 1];
        }
        vector<int> f(n);
        f[n - 1] = stones[n - 1];
        for (int i = n - 2; i > 0; --i) {
            f[i] = max(f[i + 1], stones[i] - f[i + 1]);
        }
        return f[1];
    }
};
