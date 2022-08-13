class Solution {
public:
    int minimumSemesters(int n, vector<vector<int>>& relations) {
        vector<vector<int>> g(n);
        vector<int> indeg(n);
        for (auto& r : relations) {
            int a = r[0] - 1, b = r[1] - 1;
            g[a].push_back(b);
            ++indeg[b];
        }
        queue<int> q;
        for (int i = 0; i < n; ++i)
            if (indeg[i] == 0) q.push(i);
        int ans = 0;
        while (!q.empty()) {
            ++ans;
            for (int k = q.size(); k; --k) {
                int i = q.front();
                q.pop();
                --n;
                for (int j : g[i])
                    if (--indeg[j] == 0) q.push(j);
            }
        }
        return n == 0 ? ans : -1;
    }
};