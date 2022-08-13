class Solution {
public:
    int m;
    int n;
    vector<int> dist;

    int cutOffTree(vector<vector<int>>& forest) {
        m = forest.size();
        n = forest[0].size();
        dist.resize(3600);
        vector<pair<int, int>> trees;
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                if (forest[i][j] > 1)
                    trees.push_back({forest[i][j], i * n + j});
        sort(trees.begin(), trees.end());
        int ans = 0;
        int start = 0;
        for (auto& tree : trees) {
            int end = tree.second;
            int t = bfs(start, end, forest);
            if (t == -1) return -1;
            ans += t;
            start = end;
        }
        return ans;
    }

    int bfs(int start, int end, vector<vector<int>>& forest) {
        priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> q;
        q.push({f(start, end), start});
        fill(dist.begin(), dist.end(), INT_MAX);
        dist[start] = 0;
        vector<int> dirs = {-1, 0, 1, 0, -1};
        while (!q.empty()) {
            int state = q.top().second;
            q.pop();
            if (state == end) return dist[state];
            for (int k = 0; k < 4; ++k) {
                int x = state / n + dirs[k], y = state % n + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && forest[x][y] && dist[x * n + y] > dist[state] + 1) {
                    dist[x * n + y] = dist[state] + 1;
                    q.push({dist[x * n + y] + f(x * n + y, end), x * n + y});
                }
            }
        }
        return -1;
    }

    int f(int start, int end) {
        int a = start / n, b = start % n;
        int c = end / n, d = end % n;
        return abs(a - c) + abs(b - d);
    }
};