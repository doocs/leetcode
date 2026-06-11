class Solution {
public:
    long long minCost(int n) {
        return 1LL * n * (n - 1) / 2;
    }
};