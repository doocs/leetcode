class Solution {
public:
    int checkWays(vector<vector<int>>& pairs) {
        vector<vector<bool>> g(510, vector<bool>(510));
        vector<vector<int>> v(510);
        for (auto& p : pairs) {
            int x = p[0], y = p[1];
            g[x][y] = g[y][x] = 1;
            v[x].push_back(y);
            v[y].push_back(x);
        }
        vector<int> nodes;
        for (int i = 1; i <= 500; ++i) {
            if (v[i].size()) {
                nodes.push_back(i);
                g[i][i] = 1;
            }
        }
        sort(nodes.begin(), nodes.end(), [&](int x, int y) -> bool { return v[x].size() < v[y].size(); });
        bool equal = 0;
        int root = 0;
        for (int i = 0; i < nodes.size(); ++i) {
            int x = nodes[i];
            int j = i + 1;
            for (; j < nodes.size() && !g[x][nodes[j]]; ++j)
                ;
            if (j < nodes.size()) {
                int y = nodes[j];
                if (v[x].size() == v[y].size()) equal = 1;
                for (int z : v[x])
                    if (!g[y][z])
                        return 0;
            } else
                ++root;
        }
        if (root > 1) return 0;
        if (equal) return 2;
        return 1;
    }
};