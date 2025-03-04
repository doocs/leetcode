class Solution {
public:
    vector<string> letterCasePermutation(string s) {
        string t = s;
        vector<string> ans;
        auto dfs = [&](this auto&& dfs, int i) -> void {
            if (i >= t.size()) {
                ans.push_back(t);
                return;
            }
            dfs(i + 1);
            if (isalpha(t[i])) {
                t[i] ^= 32;
                dfs(i + 1);
            }
        };
        dfs(0);
        return ans;
    }
};
