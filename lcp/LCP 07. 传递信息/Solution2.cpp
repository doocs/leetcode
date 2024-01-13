class Solution {
public:
    int numWays(int n, vector<vector<int>>& relation, int k) {
        vector<int> f(n);
        f[0] = 1;
        while (k--) {
            vector<int> g(n);
            for (auto& r : relation) {
                int a = r[0], b = r[1];
                g[b] += f[a];
            }
            f = move(g);
        }
        return f[n - 1];
    }
};