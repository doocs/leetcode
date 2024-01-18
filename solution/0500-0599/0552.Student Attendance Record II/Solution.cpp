int f[100010][2][3];
const int mod = 1e9 + 7;

class Solution {
public:
    int checkRecord(int n) {
        this->n = n;
        memset(f, -1, sizeof(f));
        return dfs(0, 0, 0);
    }

    int dfs(int i, int j, int k) {
        if (i >= n) {
            return 1;
        }
        if (f[i][j][k] != -1) {
            return f[i][j][k];
        }
        int ans = dfs(i + 1, j, 0);
        if (j == 0) {
            ans = (ans + dfs(i + 1, j + 1, 0)) % mod;
        }
        if (k < 2) {
            ans = (ans + dfs(i + 1, j, k + 1)) % mod;
        }
        return f[i][j][k] = ans;
    }

private:
    int n;
};