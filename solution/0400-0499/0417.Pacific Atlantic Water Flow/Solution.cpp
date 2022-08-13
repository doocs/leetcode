typedef pair<int, int> pii;

class Solution {
public:
    vector<vector<int>> heights;
    int m;
    int n;

    vector<vector<int>> pacificAtlantic(vector<vector<int>>& heights) {
        m = heights.size();
        n = heights[0].size();
        this->heights = heights;
        queue<pii> q1;
        queue<pii> q2;
        unordered_set<int> vis1;
        unordered_set<int> vis2;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 || j == 0) {
                    vis1.insert(i * n + j);
                    q1.emplace(i, j);
                }
                if (i == m - 1 || j == n - 1) {
                    vis2.insert(i * n + j);
                    q2.emplace(i, j);
                }
            }
        }
        bfs(q1, vis1);
        bfs(q2, vis2);
        vector<vector<int>> ans;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int x = i * n + j;
                if (vis1.count(x) && vis2.count(x)) {
                    ans.push_back({i, j});
                }
            }
        }
        return ans;
    }

    void bfs(queue<pii>& q, unordered_set<int>& vis) {
        vector<int> dirs = {-1, 0, 1, 0, -1};
        while (!q.empty()) {
            for (int k = q.size(); k > 0; --k) {
                auto p = q.front();
                q.pop();
                for (int i = 0; i < 4; ++i) {
                    int x = p.first + dirs[i];
                    int y = p.second + dirs[i + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && !vis.count(x * n + y) && heights[x][y] >= heights[p.first][p.second]) {
                        vis.insert(x * n + y);
                        q.emplace(x, y);
                    }
                }
            }
        }
    }
};