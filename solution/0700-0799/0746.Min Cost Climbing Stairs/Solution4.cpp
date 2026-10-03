class Solution {
public:
    int minCostClimbingStairs(vector<int>& cost) {
        int n = cost.size();
        vector<int> f(n + 2);
        for (int i = n - 1; i >= 0; --i) {
            f[i] = cost[i] + min(f[i + 1], f[i + 2]);
        }
        return min(f[0], f[1]);
    }
};
