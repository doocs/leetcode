class Solution {
public:
    vector<int> killProcess(vector<int>& pid, vector<int>& ppid, int kill) {
        unordered_map<int, vector<int>> g;
        vector<int> ans;
        int n = pid.size();
        for (int i = 0; i < n; ++i) {
            int c = pid[i], p = ppid[i];
            g[p].push_back(c);
        }
        dfs(kill, g, ans);
        return ans;
    }

    void dfs(int u, unordered_map<int, vector<int>>& g, vector<int>& ans) {
        ans.push_back(u);
        for (int v : g[u]) dfs(v, g, ans);
    }
};