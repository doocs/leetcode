class Solution {
public:
    long long maxRatings(vector<vector<int>>& units) {
        int n = units[0].size();
        if (n == 1) {
            long long ans = 0;
            for (auto& x : units) {
                ans += x[0];
            }
            return ans;
        }

        long long ans = 0;
        int mn = INT_MAX;
        int mn2 = INT_MAX;

        for (auto& x : units) {
            sort(x.begin(), x.end());
            ans += x[1];
            mn2 = min(mn2, x[1]);
            mn = min(mn, x[0]);
        }

        return ans - (mn2 - mn);
    }
};