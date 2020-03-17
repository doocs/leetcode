public class Solution {
    public bool Exist(char[][] board, string word) {
        var lenI = board.Length;
        var lenJ = lenI == 0 ? 0 : board[0].Length;
        var visited = new bool[lenI, lenJ];
        for (var i = 0; i < lenI; ++i)
        {
            for (var j = 0; j < lenJ; ++j)
            {
                if (Search(board, visited, word, lenI, lenJ, i, j, 0))
                {
                    return true;
                }
            }
        }
        return false;
    }

    private int[,] paths = new int[4,2] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    private bool Search(char[][] board, bool[,] visited, string word, int lenI, int lenJ, int i, int j, int p)
    {
        if (p == word.Length)
        {
            return true;
        }
        if (i < 0 || i >= lenI || j < 0 || j >= lenJ) return false;
        if (visited[i, j] || word[p] != board[i][j]) return false;
        visited[i, j] = true;
        for (var k = 0; k < 4; ++k)
        {
            if (Search(board, visited, word, lenI, lenJ, i + paths[k, 0], j + paths[k, 1], p + 1)) return true;
        }
        visited[i, j] = false;
        return false;
    }
}