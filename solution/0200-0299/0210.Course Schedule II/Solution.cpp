class Solution {
public:
    vector<int> findOrder(int numCourses, vector<vector<int>>& prerequisites) {
        vector<vector<int>> edges(numCourses);
        vector<int> indegree(numCourses);
        for (auto& p : prerequisites)
        {
            int a = p[0], b = p[1];
            edges[b].push_back(a);
            ++indegree[a];
        }
        queue<int> q;
        for (int i = 0; i < numCourses; ++i)
            if (indegree[i] == 0)
                q.push(i);
        vector<int> ans;
        while (!q.empty())
        {
            int b = q.front();
            q.pop();
            ans.push_back(b);
            for (int a : edges[b])
            {
                --indegree[a];
                if (indegree[a] == 0) q.push(a);
            }
        }
        return ans.size() == numCourses ? ans : vector<int>();
    }
};