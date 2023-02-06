class Solution {
public:
    int maxProfit(vector<int>& prices) {
        int mi = 1 << 30, ans = 0;
        for (int& x : prices) {
            ans = max(ans, x - mi);
            mi = min(mi, x);
        }
        return ans;
    }
};