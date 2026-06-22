class Solution {
public:
    vector<string> createGrid(int m, int n) {
        vector<string> g(m, string(n, '#'));

        g[0] = string(n, '.');

        for (int i = 0; i < m; i++) {
            g[i][n - 1] = '.';
        }

        return g;
    }
};