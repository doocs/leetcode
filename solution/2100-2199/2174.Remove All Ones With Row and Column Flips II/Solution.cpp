class Solution {
public:
    int removeOnes(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int state = 0;
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                if (grid[i][j])
                    state |= (1 << (i * n + j));
        queue<int> q {{state}};
        unordered_set<int> vis {{state}};
        int ans = 0;
        while (!q.empty()) {
            for (int k = q.size(); k > 0; --k) {
                state = q.front();
                q.pop();
                if (state == 0) return ans;
                for (int i = 0; i < m; ++i) {
                    for (int j = 0; j < n; ++j) {
                        if (grid[i][j] == 0) continue;
                        int nxt = state;
                        for (int r = 0; r < m; ++r) nxt &= ~(1 << (r * n + j));
                        for (int c = 0; c < n; ++c) nxt &= ~(1 << (i * n + c));
                        if (!vis.count(nxt)) {
                            vis.insert(nxt);
                            q.push(nxt);
                        }
                    }
                }
            }
            ++ans;
        }
        return -1;
    }
};