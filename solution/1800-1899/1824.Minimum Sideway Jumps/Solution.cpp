class Solution {
public:
    const int inf = 0x3f3f3f3f;

    int minSideJumps(vector<int>& obstacles) {
        vector<int> f = {1, 0, 1};
        for (int i = 1; i < obstacles.size(); ++i) {
            int v = obstacles[i];
            vector<int> g(3, inf);
            for (int j = 0; j < 3; ++j) if (v != j + 1) g[j] = f[j];
            if (v != 1) g[0] = min(g[0], min(g[1], g[2]) + 1);
            if (v != 2) g[1] = min(g[1], min(g[0], g[2]) + 1);
            if (v != 3) g[2] = min(g[2], min(g[0], g[1]) + 1);
            f = move(g);
        }
        return *min_element(f.begin(), f.end());
    }
};