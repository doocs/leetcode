class Solution {
public:
    vector<bool> checkIfPrerequisite(int numCourses, vector<vector<int>>& prerequisites, vector<vector<int>>& queries) {
        vector<vector<int>> g(numCourses, vector<int>(numCourses, -1));
        for (auto& e : prerequisites) {
            int a = e[0], b = e[1];
            g[a][b] = 1;
        }
        vector<bool> ans;
        for (auto& e : queries) {
            int a = e[0], b = e[1];
            ans.push_back(dfs(a, b, g));
        }
        return ans;
    }

    bool dfs(int a, int b, vector<vector<int>>& g) {
        if (g[a][b] != -1) return g[a][b] == 1;
        if (a == b) {
            g[a][b] = 1;
            return true;
        }
        for (int i = 0; i < g[a].size(); ++i) {
            if (g[a][i] == 1 && dfs(i, b, g)) {
                g[a][b] = 1;
                return true;
            }
        }
        g[a][b] = 0;
        return false;
    }
};