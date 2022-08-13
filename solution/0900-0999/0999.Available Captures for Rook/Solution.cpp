class Solution {
public:
    int numRookCaptures(vector<vector<char>>& board) {
        vector<int> pos = find(board);
        int ans = 0, n = 8;
        vector<vector<int>> dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (auto& dir : dirs) {
            int x = pos[0], y = pos[1], a = dir[0], b = dir[1];
            while (x + a >= 0 && x + a < n && y + b >= 0 && y + b < n && board[x + a][y + b] != 'B') {
                x += a;
                y += b;
                if (board[x][y] == 'p') {
                    ++ans;
                    break;
                }
            }
        }
        return ans;
    }

    vector<int> find(vector<vector<char>>& board) {
        int n = 8;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] == 'R') {
                    return {i, j};
                }
            }
        }
        return {};
    }
};