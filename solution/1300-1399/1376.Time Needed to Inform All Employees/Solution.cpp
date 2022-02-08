class Solution {
public:
    unordered_map<int, vector<int>> g;
    vector<int> manager;
    vector<int> informTime;

    int numOfMinutes(int n, int headID, vector<int>& manager, vector<int>& informTime) {
        this->manager = manager;
        this->informTime = informTime;
        for (int i = 0; i < n; ++i) g[manager[i]].push_back(i);
        return dfs(headID);
    }

    int dfs(int i) {
        int ans = 0;
        for (int j : g[i]) ans = max(ans, informTime[i] + dfs(j));
        return ans;
    }
};