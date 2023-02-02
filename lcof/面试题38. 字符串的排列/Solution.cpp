class Solution {
public:
    vector<string> permutation(string s) {
        vector<string> ans;
        function<void(int)> dfs = [&](int i) {
            if (i == s.size() - 1) {
                ans.push_back(s);
                return;
            }
            unordered_set<char> vis;
            for (int j = i; j < s.size(); ++j) {
                if (!vis.count(s[j])) {
                    vis.insert(s[j]);
                    swap(s[i], s[j]);
                    dfs(i + 1);
                    swap(s[i], s[j]);
                }
            }
        };
        dfs(0);
        return ans;
    }
};