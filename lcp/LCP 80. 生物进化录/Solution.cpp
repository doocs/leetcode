class Solution {
public:
    string evolutionaryRecord(vector<int>& parents) {
        int n = parents.size();
        vector<vector<int>> g(n);
        for (int i = 1; i < n; ++i) {
            g[parents[i]].push_back(i);
        }

        function<string(int)> dfs = [&](int i) -> string {
            vector<string> t;
            for (int j : g[i]) {
                t.push_back(dfs(j));
            }
            sort(t.begin(), t.end());
            string res = "0";
            for (const string& s : t) {
                res += s;
            }
            res += "1";
            return res;
        };

        string ans = dfs(0);
        return ans.substr(1, ans.find_last_not_of('1'));
    }
};