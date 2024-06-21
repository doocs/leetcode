class Solution {
public:
    vector<int> goodSubsetofBinaryMatrix(vector<vector<int>>& grid) {
        unordered_map<int, int> g;
        for (int i = 0; i < grid.size(); ++i) {
            int mask = 0;
            for (int j = 0; j < grid[0].size(); ++j) {
                mask |= grid[i][j] << j;
            }
            if (mask == 0) {
                return {i};
            }
            g[mask] = i;
        }
        for (auto& [a, i] : g) {
            for (auto& [b, j] : g) {
                if ((a & b) == 0) {
                    return {min(i, j), max(i, j)};
                }
            }
        }
        return {};
    }
};