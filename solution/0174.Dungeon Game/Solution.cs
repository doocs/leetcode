using System;

public class Solution {
    public int CalculateMinimumHP(int[,] dungeon) {
        var len1 = dungeon.GetLength(0);
        var len2 = dungeon.GetLength(1);
        var f = new long[len1, len2]; 
        for (var i = len1 - 1; i >= 0; --i)
        {
            for (var j = len2 - 1; j >= 0; --j)
            {
                var down = (i == len1 - 1) ? long.MaxValue : f[i + 1, j];
                var right = (j == len2 - 1) ? long.MaxValue : f[i, j + 1];
                f[i, j] = Math.Min(down, right);
                if (f[i, j] == long.MaxValue) f[i, j] = 1;
                f[i, j] -= dungeon[i, j];
                if (f[i, j] < 1) f[i, j] = 1;
            }
        }
        return (int) f[0, 0];
    }
}