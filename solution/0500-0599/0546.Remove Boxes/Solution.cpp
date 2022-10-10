class Solution {
public:
    int removeBoxes(vector<int>& boxes) {
        int n = boxes.size();
        vector<vector<vector<int>>> f(n, vector<vector<int>>(n, vector<int>(n)));
        function<int(int, int, int)> dfs;
        dfs = [&](int i, int j, int k) {
            if (i > j) return 0;
            while (i < j && boxes[j] == boxes[j - 1]) {
                --j;
                ++k;
            }
            if (f[i][j][k]) return f[i][j][k];
            int ans = dfs(i, j - 1, 0) + (k + 1) * (k + 1);
            for (int h = i; h < j; ++h) {
                if (boxes[h] == boxes[j]) {
                    ans = max(ans, dfs(h + 1, j - 1, 0) + dfs(i, h, k + 1));
                }
            }
            f[i][j][k] = ans;
            return ans;
        };
        return dfs(0, n - 1, 0);
    }
};