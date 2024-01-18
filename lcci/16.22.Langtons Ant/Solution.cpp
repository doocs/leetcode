class Solution {
public:
    vector<string> printKMoves(int K) {
        int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
        int dirs[5] = {0, 1, 0, -1, 0};
        string d = "RDLU";
        int x = 0, y = 0, p = 0;
        set<pair<int, int>> black;
        while (K--) {
            auto t = make_pair(x, y);
            if (black.count(t)) {
                black.erase(t);
                p = (p + 3) % 4;
            } else {
                black.insert(t);
                p = (p + 1) % 4;
            }
            x += dirs[p];
            y += dirs[p + 1];
            x1 = min(x1, x);
            y1 = min(y1, y);
            x2 = max(x2, x);
            y2 = max(y2, y);
        }
        int m = x2 - x1 + 1, n = y2 - y1 + 1;
        vector<string> g(m, string(n, '_'));
        for (auto& [i, j] : black) {
            g[i - x1][j - y1] = 'X';
        }
        g[x - x1][y - y1] = d[p];
        return g;
    }
};