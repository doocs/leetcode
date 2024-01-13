class Solution {
public:
    vector<long long> minimumCosts(vector<int>& regular, vector<int>& express, int expressCost) {
        int n = regular.size();
        long long f[n + 1];
        long long g[n + 1];
        f[0] = 0;
        g[0] = 1 << 30;
        vector<long long> cost(n);
        for (int i = 1; i <= n; ++i) {
            int a = regular[i - 1];
            int b = express[i - 1];
            f[i] = min(f[i - 1] + a, g[i - 1] + a);
            g[i] = min(f[i - 1] + expressCost + b, g[i - 1] + b);
            cost[i - 1] = min(f[i], g[i]);
        }
        return cost;
    }
};