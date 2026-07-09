class Solution {
public:
    vector<vector<int>> specialGrid(int n) {
        int m = 1 << n;
        vector<vector<int>> ans(m, vector<int>(m));
        int val = 0;

        auto dfs = [&](this auto&& dfs, int x, int y, int k) -> void {
            if (k == 1) {
                ans[x][y] = val++;
                return;
            }

            int h = k / 2;
            dfs(x, y, h);
            dfs(x + h, y, h);
            dfs(x + h, y - h, h);
            dfs(x, y - h, h);
        };

        dfs(0, m - 1, m);
        return ans;
    }
};