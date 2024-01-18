class Solution {
public:
    vector<string> generateAbbreviations(string word) {
        int n = word.size();
        function<vector<string>(int)> dfs = [&](int i) -> vector<string> {
            if (i >= n) {
                return {""};
            }
            vector<string> ans;
            for (auto& s : dfs(i + 1)) {
                string p(1, word[i]);
                ans.emplace_back(p + s);
            }
            for (int j = i + 1; j <= n; ++j) {
                for (auto& s : dfs(j + 1)) {
                    string p = j < n ? string(1, word[j]) : "";
                    ans.emplace_back(to_string(j - i) + p + s);
                }
            }
            return ans;
        };
        return dfs(0);
    }
};