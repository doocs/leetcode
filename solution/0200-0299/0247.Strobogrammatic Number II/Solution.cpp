class Solution {
public:
    int n;
    vector<string> findStrobogrammatic(int n) {
        this->n = n;
        return dfs(n);
    }

    vector<string> dfs(int u) {
        if (u == 0) return {""};
        if (u == 1) return {"0", "1", "8"};
        vector<string> ans;
        vector<vector<char>> pairs = {{'1', '1'}, {'8', '8'}, {'6', '9'}, {'9', '6'}};
        for (string v : dfs(u - 2)) {
            for (auto& p : pairs) ans.push_back({p[0] + v + p[1]});
            if (u != n) ans.push_back('0' + v + '0');
        }
        return ans;
    }
};