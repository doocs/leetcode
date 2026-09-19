class Solution {
public:
    vector<vector<int>> indexPairs(string text, vector<string>& words) {
        unordered_set<string> s(words.begin(), words.end());
        int n = text.size();
        vector<vector<int>> ans;
        for (int i = 0; i < n; ++i) {
            for (int j = i; j < n; ++j) {
                if (s.count(text.substr(i, j - i + 1))) {
                    ans.push_back({i, j});
                }
            }
        }
        return ans;
    }
};
