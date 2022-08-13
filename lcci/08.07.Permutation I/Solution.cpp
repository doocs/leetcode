class Solution {
public:
    vector<string> permutation(string S) {
        unordered_set<char> vis;
        vector<string> ans;
        string t = "";
        dfs(0, S, t, ans, vis);
        return ans;
    }

    void dfs(int u, string& S, string& t, vector<string>& ans, unordered_set<char>& vis) {
        if (u == S.size()) {
            ans.push_back(t);
            return;
        }
        for (char& c : S) {
            if (vis.count(c)) continue;
            vis.insert(c);
            t.push_back(c);
            dfs(u + 1, S, t, ans, vis);
            vis.erase(c);
            t.pop_back();
        }
    }
};