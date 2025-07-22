class Solution {
public:
    vector<vector<int>> floodFill(vector<vector<int>>& image, int sr, int sc, int color) {
        int m = image.size(), n = image[0].size();
        int oc = image[sr][sc];
        if (oc == color) {
            return image;
        }
        const int dirs[5] = {-1, 0, 1, 0, -1};
        auto dfs = [&](this auto&& dfs, int i, int j) -> void {
            image[i][j] = color;
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && image[x][y] == oc) {
                    dfs(x, y);
                }
            }
        };
        dfs(sr, sc);
        return image;
    }
};
