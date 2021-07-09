class Solution {
public:
    int countNegatives(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int i = 0, j = n - 1, cnt = 0;
        while (i < m && j >= 0) {
            if (grid[i][j] < 0) {
                cnt += (m - i);
                --j;
            } else {
                ++i;
            }
        }
        return cnt;
    }
};