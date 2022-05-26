class Solution {
public:
    vector<int> eventualSafeNodes(vector<vector<int>> &graph) {
        int n = graph.size();
        vector<vector<int>> revGraph(n);
        vector<int> outDegree(n);
        for (int i = 0; i < n; ++i)
        {
            outDegree[i] += graph[i].size();
            for (int j : graph[i])
                revGraph[j].push_back(i);
        }
        queue<int> q;
        for (int i = 0; i < n; ++i)
            if (outDegree[i] == 0)
                q.push(i);
        while (!q.empty())
        {
            int i = q.front();
            q.pop();
            for (int j : revGraph[i])
            {
                if (--outDegree[j] == 0)
                    q.push(j);
            }
        }
        vector<int> ans;
        for (int i = 0; i < n; ++i)
            if (outDegree[i] == 0)
                ans.push_back(i);
        return ans;
    }
};