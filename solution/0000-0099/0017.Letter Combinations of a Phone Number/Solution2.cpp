class Solution {
public:
    vector<string> letterCombinations(string digits) {
        if (digits.empty()) {
            return {};
        }
        vector<string> d = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        vector<string> ans;
        string t;
        function<void(int)> dfs = [&](int i) {
            if (i >= digits.size()) {
                ans.push_back(t);
                return;
            }
            for (auto& c : d[digits[i] - '2']) {
                t.push_back(c);
                dfs(i + 1);
                t.pop_back();
            }
        };
        dfs(0);
        return ans;
    }
};