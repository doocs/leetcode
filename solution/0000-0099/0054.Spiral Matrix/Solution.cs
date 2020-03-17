using System;
using System.Collections.Generic;

public class Solution {
    public IList<int> SpiralOrder(int[][] matrix) {
        var lenI = matrix.Length;
        var lenJ = lenI == 0 ? 0 : matrix[0].Length;
        var result = new List<int>(lenI * lenJ);
        var rounds = (Math.Min(lenI, lenJ) + 1) / 2;
        for (var r = 0; r < rounds; ++r)
        {
            if (lenI - r * 2 == 1)
            {
                for (var j = r; j < lenJ - r; ++j)
                {
                    result.Add(matrix[r][j]);
                }
            }
            else if (lenJ - r * 2 == 1)
            {
                for (var i = r; i < lenI - r; ++i)
                {
                    result.Add(matrix[i][r]);
                }
            }
            else
            {
                for (var j = r; j < lenJ - r - 1; ++j)
                {
                    result.Add(matrix[r][j]);
                }
                for (var i = r; i < lenI - r - 1; ++i)
                {
                    result.Add(matrix[i][lenJ - r - 1]);
                }
                for (var j = lenJ - r - 1; j > r; --j)
                {
                    result.Add(matrix[lenI - r - 1][j]);
                }
                for (var i = lenI - r - 1; i > r; --i)
                {
                    result.Add(matrix[i][r]);
                }
            }
        }
        return result;
    }
}