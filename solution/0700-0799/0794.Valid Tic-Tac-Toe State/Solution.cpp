class Solution {
public:
    bool validTicTacToe(vector<string>& board) {
        int x = 0, o = 0;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (board[i][j] == 'X') {
                    ++x;
                } else if (board[i][j] == 'O') {
                    ++o;
                }
            }
        }
        if (x != o && x - 1 != o) {
            return false;
        }
        if (win(board, 'X') && x - 1 != o) {
            return false;
        }
        return !(win(board, 'O') && x != o);
    }

    bool win(vector<string>& b, char p) {
        for (int i = 0; i < 3; ++i) {
            if (b[i][0] == p && b[i][1] == p && b[i][2] == p) {
                return true;
            }
            if (b[0][i] == p && b[1][i] == p && b[2][i] == p) {
                return true;
            }
        }
        if (b[0][0] == p && b[1][1] == p && b[2][2] == p) {
            return true;
        }
        return b[0][2] == p && b[1][1] == p && b[2][0] == p;
    }
};
