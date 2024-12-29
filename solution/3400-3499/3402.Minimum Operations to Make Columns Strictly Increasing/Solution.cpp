class Solution {
public:
    int minimumOperations(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int ans = 0;
        for (int j = 0; j < n; ++j) {
            int pre = -1;
            for (int i = 0; i < m; ++i) {
                int cur = grid[i][j];
                if (pre < cur) {
                    pre = cur;
                } else {
                    ++pre;
                    ans += pre - cur;
                }
            }
        }
        return ans;
    }
};
