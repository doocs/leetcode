class Solution {
public:
    vector<vector<int>> sortMatrix(vector<vector<int>>& grid) {
        int n = grid.size();
        for (int k = n - 2; k >= 0; --k) {
            int i = k, j = 0;
            vector<int> t;
            while (i < n && j < n) {
                t.push_back(grid[i++][j++]);
            }
            ranges::sort(t);
            for (int x : t) {
                grid[--i][--j] = x;
            }
        }
        for (int k = n - 2; k > 0; --k) {
            int i = k, j = n - 1;
            vector<int> t;
            while (i >= 0 && j >= 0) {
                t.push_back(grid[i--][j--]);
            }
            ranges::sort(t);
            for (int x : t) {
                grid[++i][++j] = x;
            }
        }
        return grid;
    }
};
