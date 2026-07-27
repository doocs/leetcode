class Solution {
public:
    long long minCost(int m, int n, vector<vector<int>>& penalty) {
        vector<vector<array<long long, 2>>> dist(
            m, vector<array<long long, 2>>(n, {LLONG_MAX, LLONG_MAX}));
        dist[0][0][1] = 1;

        priority_queue<
            array<long long, 4>,
            vector<array<long long, 4>>,
            greater<>>
            pq;
        pq.push({1, 0, 0, 1});

        int dirs[4][2] = {{-1, 0}, {0, 1}, {0, -1}, {1, 0}};

        while (!pq.empty()) {
            auto [d, i, j, k] = pq.top();
            pq.pop();

            if (i == m - 1 && j == n - 1) {
                return d;
            }
            if (d > dist[i][j][k]) {
                continue;
            }

            int p = penalty[i][j];

            long long nd = d + p;
            if (nd < dist[i][j][k ^ 1]) {
                dist[i][j][k ^ 1] = nd;
                pq.push({nd, i, j, k ^ 1});
            }

            for (int idx = 0; idx < 4; idx++) {
                int x = i + dirs[idx][0];
                int y = j + dirs[idx][1];
                if (0 <= x && x < m && 0 <= y && y < n) {
                    nd = d + 1LL * (x + 1) * (y + 1) + (((idx & 1) ^ k) ? p : 0);
                    if (nd < dist[x][y][k ^ 1]) {
                        dist[x][y][k ^ 1] = nd;
                        pq.push({nd, (long long) x, (long long) y, (long long) (k ^ 1)});
                    }
                }
            }
        }

        return -1;
    }
};