using System;
using System.Collections.Generic;

public class Solution {
    private static readonly int[,] directions = new int[4, 2] { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 }};
    public void Solve(char[][] board) {
        var lenI = board.Length;
        var lenJ = lenI == 0 ? 0 : board[0].Length;

        for (var i = 0; i < lenI; ++i)
        {
            for (var j = 0; j < lenJ; ++j)
            {
                if (board[i][j] == 'O')
                {
                    var marked = new List<Tuple<int, int>>();
                    marked.Add(Tuple.Create(i, j));
                    board[i][j] = 'M';
                    bool escaped = false;
                    for (var m = 0; m < marked.Count; ++m)
                    {
                        for (var k = 0; k < 4; ++k)
                        {
                            var newI = marked[m].Item1 + directions[k, 0];
                            var newJ = marked[m].Item2 + directions[k, 1];
                            if (newI < 0 || newI >= lenI || newJ < 0 || newJ >= lenJ)
                            {
                                escaped = true;
                            }
                            else if (board[newI][newJ] == 'O')
                            {
                                board[newI][newJ] = 'M';
                                marked.Add(Tuple.Create(newI, newJ));
                            }
                        }
                    }

                    if (!escaped)
                    {
                        foreach (var item in marked)
                        {
                            board[item.Item1][item.Item2] = 'X';
                        }
                    }
                }
            }
        }

        for (var i = 0; i < lenI; ++i)
        {
            for (var j = 0; j < lenJ; ++j)
            {
                if (board[i][j] == 'M')
                {
                    board[i][j] = 'O';
                }
            }
        }
    }
}