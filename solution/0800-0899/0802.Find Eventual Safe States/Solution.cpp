class Solution {
public:
    vector<int> eventualSafeNodes(vector<vector<int>>& graph) {
        int n = graph.size();
        vector<int> indeg(n);
        vector<vector<int>> rg(n);
        queue<int> q;
        for (int i = 0; i < n; ++i) {
            for (int j : graph[i]) rg[j].push_back(i);
            indeg[i] = graph[i].size();
            if (indeg[i] == 0) q.push(i);
        }
        while (!q.empty()) {
            int i = q.front();
            q.pop();
            for (int j : rg[i])
                if (--indeg[j] == 0) q.push(j);
        }
        vector<int> ans;
        for (int i = 0; i < n; ++i)
            if (indeg[i] == 0) ans.push_back(i);
        return ans;
    }
};