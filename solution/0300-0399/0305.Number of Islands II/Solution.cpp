class Solution {
public:
    vector<int> p;

    vector<int> numIslands2(int m, int n, vector<vector<int>>& positions) {
        p.resize(m * n);
        for (int i = 0; i < p.size(); ++i) p[i] = i;
        vector<vector<int>> grid(m, vector<int>(n));
        vector<int> ans;
        int cnt = 0;
        vector<int> dirs = {-1, 0, 1, 0, -1};
        for (auto& pos : positions) {
            int i = pos[0], j = pos[1];
            if (grid[i][j] == 1) {
                ans.push_back(cnt);
                continue;
            }
            grid[i][j] = 1;
            ++cnt;
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1 && find(x * n + y) != find(i * n + j)) {
                    p[find(x * n + y)] = find(i * n + j);
                    --cnt;
                }
            }
            ans.push_back(cnt);
        }
        return ans;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};