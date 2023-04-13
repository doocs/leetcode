class Solution {
public:
    int numRookCaptures(vector<vector<char>>& board) {
        int ans = 0;
        int dirs[5] = {-1, 0, 1, 0, -1};
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (board[i][j] == 'R') {
                    for (int k = 0; k < 4; ++k) {
                        int x = i, y = j;
                        int a = dirs[k], b = dirs[k + 1];
                        while (x + a >= 0 && x + a < 8 && y + b >= 0 && y + b < 8 && board[x + a][y + b] != 'B') {
                            x += a;
                            y += b;
                            if (board[x][y] == 'p') {
                                ++ans;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return ans;
    }
};