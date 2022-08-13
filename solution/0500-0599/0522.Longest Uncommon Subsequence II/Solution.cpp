class Solution {
public:
    int findLUSlength(vector<string>& strs) {
        int ans = -1;
        for (int i = 0, j = 0, n = strs.size(); i < n; ++i) {
            for (j = 0; j < n; ++j) {
                if (i == j) continue;
                if (check(strs[j], strs[i])) break;
            }
            if (j == n) ans = max(ans, (int)strs[i].size());
        }
        return ans;
    }

    bool check(string a, string b) {
        int j = 0;
        for (int i = 0; i < a.size() && j < b.size(); ++i)
            if (a[i] == b[j]) ++j;
        return j == b.size();
    }
};