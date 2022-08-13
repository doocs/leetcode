class Solution {
public:
    int minFlips(vector<vector<int>>& mat) {
        int m = mat.size(), n = mat[0].size();
        int state = 0;
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                if (mat[i][j])
                    state |= (1 << (i * n + j));
        queue<int> q {{state}};
        unordered_set<int> vis {{state}};
        int ans = 0;
        vector<int> dirs = {0, -1, 0, 1, 0, 0};
        while (!q.empty()) {
            for (int t = q.size(); t; --t) {
                state = q.front();
                if (state == 0) return ans;
                q.pop();
                for (int i = 0; i < m; ++i) {
                    for (int j = 0; j < n; ++j) {
                        int nxt = state;
                        for (int k = 0; k < 5; ++k) {
                            int x = i + dirs[k], y = j + dirs[k + 1];
                            if (x < 0 || x >= m || y < 0 || y >= n) continue;
                            if ((nxt & (1 << (x * n + y))) != 0)
                                nxt -= 1 << (x * n + y);
                            else
                                nxt |= 1 << (x * n + y);
                        }
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