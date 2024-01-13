class Solution {
public:
    int equalSubstring(string s, string t, int maxCost) {
        int n = s.size();
        int ans = 0, sum = 0;
        for (int i = 0, j = 0; i < n; ++i) {
            sum += abs(s[i] - t[i]);
            while (sum > maxCost) {
                sum -= abs(s[j] - t[j]);
                ++j;
            }
            ans = max(ans, i - j + 1);
        }
        return ans;
    }
};