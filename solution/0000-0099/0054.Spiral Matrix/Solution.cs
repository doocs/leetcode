public class Solution {
    public IList<int> SpiralOrder(int[][] matrix) {
        int m = matrix.Length;
        int n = matrix[0].Length;
        int top = 0, bottom = m - 1, left = 0, right = n - 1;
        var ans = new List<int>(m * n);
        while (top <= bottom && left <= right)
        {
            for (int j = left; j <= right; ++j)
            {
                ans.Add(matrix[top][j]);
            }
            for (int i = top + 1; i <= bottom; ++i)
            {
                ans.Add(matrix[i][right]);
            }
            if (left < right && top < bottom)
            {
                for (int j = right - 1; j >= left; --j)
                {
                    ans.Add(matrix[bottom][j]);
                }
                for (int i = bottom - 1; i > top; --i)
                {
                    ans.Add(matrix[i][left]);
                }
            }
            ++top;
            --bottom;
            ++left;
            --right;
        }
        return ans;
    }
}