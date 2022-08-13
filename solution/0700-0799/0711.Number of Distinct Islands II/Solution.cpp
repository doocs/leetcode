typedef pair<int, int> PII;

class Solution {
public:
    int numDistinctIslands2(vector<vector<int>>& grid) {
        set<vector<PII>> s;
        for (int i = 0; i < grid.size(); ++i) {
            for (int j = 0; j < grid[0].size(); ++j) {
                if (grid[i][j]) {
                    vector<PII> shape;
                    dfs(i, j, grid, shape);
                    s.insert(normalize(shape));
                }
            }
        }
        return s.size();
    }

    vector<PII> normalize(vector<PII>& shape) {
        vector<vector<PII>> shapes(8);
        for (auto& e : shape) {
            int i = e.first, j = e.second;
            shapes[0].push_back({i, j});
            shapes[1].push_back({i, -j});
            shapes[2].push_back({-i, j});
            shapes[3].push_back({-i, -j});
            shapes[4].push_back({j, i});
            shapes[5].push_back({j, -i});
            shapes[6].push_back({-j, -i});
            shapes[7].push_back({-j, i});
        }
        for (auto& e : shapes) {
            sort(e.begin(), e.end());
            for (int k = e.size() - 1; k >= 0; --k) {
                e[k].first -= e[0].first;
                e[k].second -= e[0].second;
            }
        }
        sort(shapes.begin(), shapes.end());
        return shapes[0];
    }

    void dfs(int i, int j, vector<vector<int>>& grid, vector<PII>& shape) {
        shape.push_back({i, j});
        grid[i][j] = 0;
        vector<int> dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < grid.size() && y >= 0 && y < grid[0].size() && grid[x][y] == 1)
                dfs(x, y, grid, shape);
        }
    }
};