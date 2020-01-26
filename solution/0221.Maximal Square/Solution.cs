// https://leetcode.com/problems/maximal-square/

using System;

public partial class Solution
{
    public int MaximalSquare(char[][] matrix)
    {
        var lengthI = matrix.Length;
        var lengthJ = lengthI == 0 ? 0 : matrix[0].Length;
        if (lengthI == 0 || lengthJ == 0) return 0;

        var lenI = new int[lengthI, lengthJ];
        var lenJ = new int[lengthI, lengthJ];
        var f = new int[lengthI, lengthJ];
        var maxSideLength = 0;
        for (var i = 0; i < lengthI; ++i)
        {
            for (var j = 0; j < lengthJ ; ++j)
            {
                if (matrix[i][j] == '1')
                {
                    lenI[i, j] = i == 0 ? 1 : lenI[i - 1, j] + 1;
                    lenJ[i, j] = j == 0 ? 1 : lenJ[i, j - 1] + 1;
                    if (i == 0 || j == 0)
                    {
                        f[i, j] = 1;
                    }
                    else
                    {
                        f[i, j] = Math.Min(Math.Min(f[i - 1, j - 1] + 1, lenI[i, j]), lenJ[i, j]);
                    }
                    if (f[i, j] > maxSideLength) maxSideLength = f[i, j];
                }
            }
        }

        return maxSideLength * maxSideLength;
    }
}