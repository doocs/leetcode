class Solution {
public:
    vector<bool> checkIfPrerequisite(int n, vector<vector<int>>& prerequisites, vector<vector<int>>& queries) {
        bool f[n][n];
        memset(f, false, sizeof(f));
        vector<int> g[n];
        vector<int> indeg(n);
        for (auto& p : prerequisites) {
            g[p[0]].push_back(p[1]);
            ++indeg[p[1]];
        }
        queue<int> q;
        for (int i = 0; i < n; ++i) {
            if (indeg[i] == 0) {
                q.push(i);
            }
        }
        while (!q.empty()) {
            int i = q.front();
            q.pop();
            for (int j : g[i]) {
                f[i][j] = true;
                for (int h = 0; h < n; ++h) {
                    f[h][j] |= f[h][i];
                }
                if (--indeg[j] == 0) {
                    q.push(j);
                }
            }
        }
        vector<bool> ans;
        for (auto& qry : queries) {
            ans.push_back(f[qry[0]][qry[1]]);
        }
        return ans;
    }
};