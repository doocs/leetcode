class Solution {
public:
    int countWays(vector<vector<int>>& ranges) {
        sort(ranges.begin(), ranges.end());
        int ans = 1, mx = -1;
        const int mod = 1e9 + 7;
        for (auto& e : ranges) {
            if (e[0] > mx) {
                ans = ans * 2 % mod;
            }
            mx = max(mx, e[1]);
        }
        return ans;
    }
};