class Solution {
public:
    int findLUSlength(vector<string>& strs) {
        int ans = -1;
        int n = strs.size();
        auto check = [&](const string& s, const string& t) {
            int m = s.size(), n = t.size();
            int i = 0;
            for (int j = 0; i < m && j < n; ++j) {
                if (s[i] == t[j]) {
                    ++i;
                }
            }
            return i == m;
        };
        for (int i = 0, j; i < n; ++i) {
            int x = strs[i].size();
            for (j = 0; j < n; ++j) {
                if (i != j && check(strs[i], strs[j])) {
                    x = -1;
                    break;
                }
            }
            ans = max(ans, x);
        }
        return ans;
    }
};