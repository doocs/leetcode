class Solution {
public:
    long long minCuttingCost(int n, int m, int k) {
        int x = max(n, m);
        return x <= k ? 0 : 1LL * k * (x - k);
    }
};