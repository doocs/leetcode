class Solution {
    bool checkWin(vector<vector<char>>& board, bool moveA) {
        char symbol = (moveA ? 'X' : 'O');
        for (int i = 0; i < 3; i++)
            if (board[i][0] == symbol && board[i][0] == board[i][1] && board[i][0] == board[i][2]) 
                return true;
        for (int i = 0; i < 3; i++)
            if (board[0][i] == symbol && board[0][i] == board[1][i] && board[0][i] == board[2][i])
                return true;
        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol)
            return true;
        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol)
            return true;
        return false;
    }
public:
    string tictactoe(vector<vector<int>>& moves) {
        vector<vector<char>> board(3, vector<char>(3, ' '));
        bool moveA = true;
        for (auto &v : moves) {
            board[v[0]][v[1]] = (moveA ? 'X' : 'O');
            if (checkWin(board, moveA))
                return (moveA ? "A" : "B");
            moveA = !moveA;
        }
        if (moves.size() == 9)
            return "Draw";
        return "Pending";
    }
};