class Solution {
public:
    vector<string> generateParenthesis(int n) {
        vector<string> res;
        dfs(res, "", 0, 0, n);
        return res;
    }

private:
    void dfs(vector<string>& res, string ans, int l, int r, int n) {
        if (ans.size() == (n << 1)) {
            res.push_back(ans);
            return;
        }
        if (l < n) dfs(res, ans + "(", l + 1, r, n);
        if (r < l) dfs(res, ans + ")", l, r + 1, n);
    }
};