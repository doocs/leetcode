public class Solution {
    public void SolveSudoku(char[][] board) {
        this.board = new ushort?[9,9];
        for (var i = 0; i < 9; ++i)
        {
            for (var j = 0; j < 9; ++j)
            {
                if (board[i][j] != '.')
                {
                    this.board[i, j] = (ushort) (1 << (board[i][j] - '0' - 1));
                }
            }
        }

        if (SolveSudoku(0, 0))
        {
            for (var i = 0; i < 9; ++i)
            {
                for (var j = 0; j < 9; ++j)
                {
                    if (board[i][j] == '.')
                    {
                        board[i][j] = '0';
                        while (this.board[i, j].Value != 0)
                        {
                            board[i][j] = (char)(board[i][j] + 1);
                            this.board[i, j] >>= 1;
                        }
                    }
                }
            }
        }
    }

    private ushort?[,] board;

    private bool ValidateHorizontalRule(int row)
    {
        ushort temp = 0;
        for (var i = 0; i < 9; ++i)
        {
            if (board[row, i].HasValue)
            {
                if ((temp | board[row, i].Value) == temp)
                {
                    return false;
                }
                temp |= board[row, i].Value;
            }
        }
        return true;
    }

    private bool ValidateVerticalRule(int column)
    {
        ushort temp = 0;
        for (var i = 0; i < 9; ++i)
        {
            if (board[i, column].HasValue)
            {
                if ((temp | board[i, column].Value) == temp)
                {
                    return false;
                }
                temp |= board[i, column].Value;
            }
        }
        return true;
    }

    private bool ValidateBlockRule(int row, int column)
    {
        var startRow = row / 3 * 3;
        var startColumn = column / 3 * 3;
        ushort temp = 0;
        for (var i = startRow; i < startRow + 3; ++i)
        {
            for (var j = startColumn; j < startColumn + 3; ++j)
            {
                if (board[i, j].HasValue)
                {
                    if ((temp | board[i, j].Value) == temp)
                    {
                        return false;
                    }
                    temp |= board[i, j].Value;
                }
            }
        }
        return true;
    }

    private bool SolveSudoku(int i, int j)
    {
        while (true)
        {
            if (j == 9)
            {
                ++i;
                j = 0;
            }
            if (i == 9)
            {
                return true;
            }
            if (board[i, j].HasValue)
            {
                ++j;
            }
            else
            {
                break;
            }
        }

        ushort stop = 1 << 9;
        for (ushort t = 1; t != stop; t <<= 1)
        {
            board[i, j] = t;
            if (ValidateHorizontalRule(i) && ValidateVerticalRule(j) && ValidateBlockRule(i, j))
            {
                if (SolveSudoku(i, j + 1))
                {
                    return true;
                }
            }
        }
        board[i, j] = null;
        return false;
    }
}