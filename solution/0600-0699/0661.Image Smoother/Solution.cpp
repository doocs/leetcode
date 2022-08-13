class Solution {
public:
    vector<vector<int>> imageSmoother(vector<vector<int>>& img) {
        int m = img.size(), n = img[0].size();
        vector<vector<int>> ans(m, vector<int>(n));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int s = 0, cnt = 0;
                for (int x = i - 1; x <= i + 1; ++x) {
                    for (int y = j - 1; y <= j + 1; ++y) {
                        if (x < 0 || x >= m || y < 0 || y >= n) continue;
                        ++cnt;
                        s += img[x][y];
                    }
                }
                ans[i][j] = s / cnt;
            }
        }
        return ans;
    }
};