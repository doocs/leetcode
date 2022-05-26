class Solution {
public:
    bool canFinish(int numCourses, vector<vector<int>>& prerequisites) {
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
        int n = 0;
        while (!q.empty())
        {
            int b = q.front();
            q.pop();
            ++n;
            for (int a : edges[b])
                if (--indegree[a] == 0)
                    q.push(a);
        }
        return n == numCourses;
    }
};