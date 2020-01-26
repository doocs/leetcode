using System;
using System.Collections.Generic;
using System.Linq;

public class Solution {
    private int MaximalRectangleHistagram(int[] height) {
        var stack = new Stack<int>();
        var result = 0;
        var i = 0;
        while (i < height.Length || stack.Any())
        {
            if (!stack.Any() || (i < height.Length && height[stack.Peek()] < height[i]))
            {
                stack.Push(i);
                ++i;
            }
            else
            {
                var previousIndex = stack.Pop();
                var area = height[previousIndex] * (stack.Any() ? (i - stack.Peek() - 1) : i);
                result = Math.Max(result, area);
            }
        }

        return result;
    }

    public int MaximalRectangle(char[][] matrix) {
        var lenI = matrix.Length;
        var lenJ = lenI == 0 ? 0 : matrix[0].Length;
        var height = new int[lenJ];
        var result = 0;
        for (var i = 0; i < lenI; ++i)
        {
            for (var j = 0; j < lenJ; ++j)
            {
                if (matrix[i][j] == '1')
                {
                    ++height[j];
                }
                else
                {
                    height[j] = 0;
                }
            }
            result = Math.Max(result, MaximalRectangleHistagram(height));
        }
        return result;
    }
}