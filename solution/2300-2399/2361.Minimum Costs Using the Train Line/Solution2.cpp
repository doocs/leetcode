class Solution {
public:
    vector<long long> minimumCosts(vector<int>& regular, vector<int>& express, int expressCost) {
        int n = regular.size();
        long long f = 0;
        long long g = 1 << 30;
        vector<long long> cost(n);
        for (int i = 0; i < n; ++i) {
            int a = regular[i];
            int b = express[i];
            long long ff = min(f + a, g + a);
            long long gg = min(f + expressCost + b, g + b);
            f = ff;
            g = gg;
            cost[i] = min(f, g);
        }
        return cost;
    }
};