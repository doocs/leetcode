class Solution {
public:
    vector<string> validStrings(int n) {
        vector<string> ans;
        string t;
        auto dfs = [&](this auto&& dfs, int i) {
            if (i >= n) {
                ans.emplace_back(t);
                return;
            }
            for (int j = 0; j < 2; ++j) {
                if ((j == 0 && (i == 0 || t[i - 1] == '1')) || j == 1) {
                    t.push_back('0' + j);
                    dfs(i + 1);
                    t.pop_back();
                }
            }
        };
        dfs(0);
        return ans;
    }
};
