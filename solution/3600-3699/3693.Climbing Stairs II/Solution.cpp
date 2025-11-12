class Solution {
public:
    int climbStairs(int n, vector<int>& costs) {
        vector<int> f(n + 1, INT_MAX / 2);
        f[0] = 0;
        for (int i = 1; i <= n; ++i) {
            int x = costs[i - 1];
            for (int j = max(0, i - 3); j < i; ++j) {
                f[i] = min(f[i], f[j] + x + (i - j) * (i - j));
            }
        }
        return f[n];
    }
};
