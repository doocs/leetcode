class Solution {
public:
    vector<string> removeInvalidParentheses(string s) {
        int ldel = 0, rdel = 0;
        for (char c : s) {
            if (c == '(')
                ++ldel;
            else if (c == ')') {
                if (ldel == 0)
                    ++rdel;
                else
                    --ldel;
            }
        }
        int tdel = ldel + rdel;
        unordered_set<string> ans;
        dfs(0, "", s, 0, 0, ldel, rdel, tdel, ans);
        vector<string> res;
        res.assign(ans.begin(), ans.end());
        return res;
    }

    void dfs(int i, string t, string s, int lcnt, int rcnt, int ldel, int rdel, int tdel, unordered_set<string>& ans) {
        if (ldel * rdel < 0 || lcnt < rcnt || ldel + rdel > s.size() - i) return;
        if (ldel == 0 && rdel == 0) {
            if (s.size() - t.size() == tdel) ans.insert(t);
        }
        if (i == s.size()) return;
        if (s[i] == '(') {
            dfs(i + 1, t, s, lcnt, rcnt, ldel - 1, rdel, tdel, ans);
            dfs(i + 1, t + s[i], s, lcnt + 1, rcnt, ldel, rdel, tdel, ans);
        } else if (s[i] == ')') {
            dfs(i + 1, t, s, lcnt, rcnt, ldel, rdel - 1, tdel, ans);
            dfs(i + 1, t + s[i], s, lcnt, rcnt + 1, ldel, rdel, tdel, ans);
        } else {
            dfs(i + 1, t + s[i], s, lcnt, rcnt, ldel, rdel, tdel, ans);
        }
    }
};