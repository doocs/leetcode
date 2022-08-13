class Solution {
public:
    vector<int> shortestToChar(string s, char c) {
        int n = s.size();
        vector<int> ans(n);
        for (int i = 0, j = INT_MAX; i < n; ++i) {
            if (s[i] == c) j = i;
            ans[i] = abs(i - j);
        }
        for (int i = n - 1, j = INT_MAX; i >= 0; --i) {
            if (s[i] == c) j = i;
            ans[i] = min(ans[i], abs(i - j));
        }
        return ans;
    }
};