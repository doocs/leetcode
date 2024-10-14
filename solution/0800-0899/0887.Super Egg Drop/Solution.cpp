class Solution {
public:
    int superEggDrop(int k, int n) {
        int f[n + 1][k + 1];
        memset(f, 0, sizeof(f));
        auto dfs = [&](auto&& dfs, int i, int j) -> int {
            if (i < 1) {
                return 0;
            }
            if (j == 1) {
                return i;
            }
            if (f[i][j]) {
                return f[i][j];
            }
            int l = 1, r = i;
            while (l < r) {
                int mid = (l + r + 1) >> 1;
                int a = dfs(dfs, mid - 1, j - 1);
                int b = dfs(dfs, i - mid, j);
                if (a <= b) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            return f[i][j] = max(dfs(dfs, l - 1, j - 1), dfs(dfs, i - l, j)) + 1;
        };
        return dfs(dfs, n, k);
    }
};
