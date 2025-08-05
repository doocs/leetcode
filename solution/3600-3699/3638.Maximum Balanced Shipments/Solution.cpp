class Solution {
public:
    int maxBalancedShipments(vector<int>& weight) {
        int ans = 0;
        int mx = 0;
        for (int x : weight) {
            mx = max(mx, x);
            if (x < mx) {
                ++ans;
                mx = 0;
            }
        }
        return ans;
    }
};