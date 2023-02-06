class Solution {
public:
    vector<string> removeInvalidParentheses(string s) {
        unordered_set<string> ans;
        int l = 0, r = 0, n = s.size();
        for (char& c : s) {
            if (c == '(') {
                ++l;
            } else if (c == ')') {
                if (l) {
                    --l;
                } else {
                    ++r;
                }
            }
        }
        function<void(int, int, int, int, int, string)> dfs;
        dfs = [&](int i, int l, int r, int lcnt, int rcnt, string t) {
            if (i == n) {
                if (l == 0 && r == 0) {
                    ans.insert(t);
                }
                return;
            }
            if (n - i < l + r || lcnt < rcnt) {
                return;
            }
            if (s[i] == '(' && l) {
                dfs(i + 1, l - 1, r, lcnt, rcnt, t);
            }
            if (s[i] == ')' && r) {
                dfs(i + 1, l, r - 1, lcnt, rcnt, t);
            }
            int x = s[i] == '(' ? 1 : 0;
            int y = s[i] == ')' ? 1 : 0;
            dfs(i + 1, l, r, lcnt + x, rcnt + y, t + s[i]);
        };

        dfs(0, l, r, 0, 0, "");
        return vector<string>(ans.begin(), ans.end());
    }
};