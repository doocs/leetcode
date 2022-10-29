class Solution {
public:
    vector<string> letterCasePermutation(string s) {
        vector<string> ans;
        function<void(int)> dfs = [&](int i) {
            if (i >= s.size()) {
                ans.emplace_back(s);
                return;
            }
            dfs(i + 1);
            if (s[i] >= 'A') {
                s[i] ^= 32;
                dfs(i + 1);
            }
        };
        dfs(0);
        return ans;
    }
};