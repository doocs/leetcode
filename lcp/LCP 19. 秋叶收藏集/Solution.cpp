class Solution {
public:
    int minimumOperations(string leaves) {
        int n = leaves.size();
        int f[n][3];
        memset(f, 0x3f, sizeof(f));
        f[0][0] = leaves[0] == 'y';
        for (int i = 1; i < n; ++i) {
            if (leaves[i] == 'r') {
                f[i][0] = f[i - 1][0];
                f[i][1] = min(f[i - 1][0], f[i - 1][1]) + 1;
                f[i][2] = min(f[i - 1][2], f[i - 1][1]);
            } else {
                f[i][0] = f[i - 1][0] + 1;
                f[i][1] = min(f[i - 1][0], f[i - 1][1]);
                f[i][2] = min(f[i - 1][2], f[i - 1][1]) + 1;
            }
        }
        return f[n - 1][2];
    }
};