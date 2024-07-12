class Solution {
public:
    int snakesAndLadders(vector<vector<int>>& board) {
        int n = board.size();
        queue<int> q{{1}};
        int m = n * n;
        vector<bool> vis(m + 1);
        vis[1] = true;

        for (int ans = 0; !q.empty(); ++ans) {
            for (int k = q.size(); k > 0; --k) {
                int x = q.front();
                q.pop();
                if (x == m) {
                    return ans;
                }
                for (int y = x + 1; y <= min(x + 6, m); ++y) {
                    int i = (y - 1) / n, j = (y - 1) % n;
                    if (i % 2 == 1) {
                        j = n - j - 1;
                    }
                    i = n - i - 1;
                    int z = board[i][j] == -1 ? y : board[i][j];
                    if (!vis[z]) {
                        vis[z] = true;
                        q.push(z);
                    }
                }
            }
        }
        return -1;
    }
};