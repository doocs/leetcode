class Solution {
public:
    int minChanges(string s) {
        int ans = 0;
        int n = s.size();
        for (int i = 1; i < n; i += 2) {
            ans += s[i] != s[i - 1];
        }
        return ans;
    }
};