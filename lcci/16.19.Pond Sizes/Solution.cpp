class Solution {
public:
    vector<int> pondSizes(vector<vector<int>>& land) {
        int m = land.size(), n = land[0].size();
        function<int(int, int)> dfs = [&](int i, int j) -> int {
            int res = 1;
            land[i][j] = 1;
            for (int x = i - 1; x <= i + 1; ++x) {
                for (int y = j - 1; y <= j + 1; ++y) {
                    if (x >= 0 && x < m && y >= 0 && y < n && land[x][y] == 0) {
                        res += dfs(x, y);
                    }
                }
            }
            return res;
        };
        vector<int> ans;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (land[i][j] == 0) {
                    ans.push_back(dfs(i, j));
                }
            }
        }
        sort(ans.begin(), ans.end());
        return ans;
    }
};