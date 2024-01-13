class Solution {
public:
    int superEggDrop(int k, int n) {
        int f[n + 1][k + 1];
        memset(f, 0, sizeof(f));
        for (int i = 1; i <= n; ++i) {
            f[i][1] = i;
        }
        for (int i = 1; i <= n; ++i) {
            for (int j = 2; j <= k; ++j) {
                int l = 1, r = i;
                while (l < r) {
                    int mid = (l + r + 1) >> 1;
                    int a = f[mid - 1][j - 1];
                    int b = f[i - mid][j];
                    if (a <= b) {
                        l = mid;
                    } else {
                        r = mid - 1;
                    }
                }
                f[i][j] = max(f[l - 1][j - 1], f[i - l][j]) + 1;
            }
        }
        return f[n][k];
    }
};