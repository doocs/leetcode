class Solution {
public:
    int minimumCost(vector<int>& cost) {
        sort(cost.begin(), cost.end());
        int ans = 0, n = cost.size();
        for (int i = n - 1; i >= 0; i -= 3) {
            ans += cost[i];
            if (i >= 1) ans += cost[i - 1];
        }
        return ans;
    }
};