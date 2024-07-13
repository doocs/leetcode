class Solution {
public:
    int equalSubstring(string s, string t, int maxCost) {
        int n = s.length();
        int ans = 0, cost = 0;
        for (int l = 0, r = 0; r < n; ++r) {
            cost += abs(s[r] - t[r]);
            while (cost > maxCost) {
                cost -= abs(s[l] - t[l]);
                ++l;
            }
            ans = max(ans, r - l + 1);
        }
        return ans;
    }
};
