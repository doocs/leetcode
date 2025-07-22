class Solution {
public:
    int minMoves(vector<string>& matrix) {
        int m = matrix.size(), n = matrix[0].size();
        unordered_map<char, vector<pair<int, int>>> g;
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j) {
                char c = matrix[i][j];
                if (isalpha(c)) g[c].push_back({i, j});
            }
        int dirs[5] = {-1, 0, 1, 0, -1};
        int INF = numeric_limits<int>::max() / 2;
        vector<vector<int>> dist(m, vector<int>(n, INF));
        dist[0][0] = 0;
        deque<pair<int, int>> q;
        q.push_back({0, 0});
        while (!q.empty()) {
            auto [i, j] = q.front();
            q.pop_front();
            int d = dist[i][j];
            if (i == m - 1 && j == n - 1) return d;
            char c = matrix[i][j];
            if (g.count(c)) {
                for (auto [x, y] : g[c])
                    if (d < dist[x][y]) {
                        dist[x][y] = d;
                        q.push_front({x, y});
                    }
                g.erase(c);
            }
            for (int idx = 0; idx < 4; ++idx) {
                int x = i + dirs[idx], y = j + dirs[idx + 1];
                if (0 <= x && x < m && 0 <= y && y < n && matrix[x][y] != '#' && d + 1 < dist[x][y]) {
                    dist[x][y] = d + 1;
                    q.push_back({x, y});
                }
            }
        }
        return -1;
    }
};
