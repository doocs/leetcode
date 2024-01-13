class Solution {
public:
    int minOperations(string s1, string s2, int x) {
        vector<int> idx;
        for (int i = 0; i < s1.size(); ++i) {
            if (s1[i] != s2[i]) {
                idx.push_back(i);
            }
        }
        int m = idx.size();
        if (m & 1) {
            return -1;
        }
        if (m == 0) {
            return 0;
        }
        int f[m][m];
        memset(f, -1, sizeof(f));
        function<int(int, int)> dfs = [&](int i, int j) {
            if (i > j) {
                return 0;
            }
            if (f[i][j] != -1) {
                return f[i][j];
            }
            f[i][j] = min({dfs(i + 1, j - 1) + x, dfs(i + 2, j) + idx[i + 1] - idx[i], dfs(i, j - 2) + idx[j] - idx[j - 1]});
            return f[i][j];
        };
        return dfs(0, m - 1);
    }
};