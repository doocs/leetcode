class Solution {
public:
    int conveyorBelt(vector<string>& matrix, vector<int>& start, vector<int>& end) {
        int dirs[5] = {-1, 0, 1, 0, -1};
        unordered_map<char, int> d;
        d['^'] = 0;
        d['>'] = 1;
        d['v'] = 2;
        d['<'] = 3;
        deque<pair<int, int>> q;
        q.emplace_back(start[0], start[1]);
        int m = matrix.size(), n = matrix[0].size();
        int dist[m][n];
        memset(dist, 0x3f, sizeof(dist));
        dist[start[0]][start[1]] = 0;
        while (1) {
            auto [i, j] = q.front();
            q.pop_front();
            if (i == end[0] && j == end[1]) {
                return dist[i][j];
            }
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                int t = dist[i][j] + (k == d[matrix[i][j]] ? 0 : 1);
                if (x >= 0 && x < m && y >= 0 && y < n && t < dist[x][y]) {
                    dist[x][y] = t;
                    if (dist[x][y] == dist[i][j]) {
                        q.emplace_front(x, y);
                    } else {
                        q.emplace_back(x, y);
                    }
                }
            }
        }
    }
};