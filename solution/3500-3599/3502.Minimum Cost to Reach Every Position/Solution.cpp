class Solution {
public:
    vector<int> minCosts(vector<int>& cost) {
        int n = cost.size();
        vector<int> ans(n);
        int mi = cost[0];
        for (int i = 0; i < n; ++i) {
            mi = min(mi, cost[i]);
            ans[i] = mi;
        }
        return ans;
    }
};