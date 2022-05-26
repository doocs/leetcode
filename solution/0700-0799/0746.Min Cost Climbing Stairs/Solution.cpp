class Solution {
public:
    int minCostClimbingStairs(vector<int>& cost) {
        int a = 0, b = 0;
        for (int i = 1; i < cost.size(); ++i) {
            int c = min(a + cost[i - 1], b + cost[i]);
            a = b;
            b = c;
        }
        return b;
    }
};