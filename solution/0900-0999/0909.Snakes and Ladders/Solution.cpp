class Solution {
public:
    int n;

    int snakesAndLadders(vector<vector<int>>& board) {
        n = board.size();
        queue<int> q{{1}};
        vector<bool> vis(n * n + 1);
        vis[1] = true;
        int ans = 0;
        while (!q.empty()) {
            for (int t = q.size(); t; --t) {
                int curr = q.front();
                if (curr == n * n) return ans;
                q.pop();
                for (int k = curr + 1; k <= min(curr + 6, n * n); ++k) {
                    auto p = get(k);
                    int next = k;
                    int i = p[0], j = p[1];
                    if (board[i][j] != -1) next = board[i][j];
                    if (!vis[next]) {
                        vis[next] = true;
                        q.push(next);
                    }
                }
            }
            ++ans;
        }
        return -1;
    }

    vector<int> get(int x) {
        int i = (x - 1) / n, j = (x - 1) % n;
        if (i % 2 == 1) j = n - 1 - j;
        return {n - 1 - i, j};
    }
};