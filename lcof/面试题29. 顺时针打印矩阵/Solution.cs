public class Solution {
    public int[] SpiralOrder(int[][] matrix) {
        List<int> ans = new List<int>();
        if (matrix.Length == 0) {
            return ans.ToArray();
        }
        int left = 0, top = 0, bottom = matrix.Length - 1, right = matrix[0].Length - 1;
        while (true) {
            for (int i = left; i <= right; i++) {
                ans.Add(matrix[top][i]);
            }
            top += 1;
            if (top > bottom) {
                break;
            }
            for (int i = top; i <= bottom; i++) {
                ans.Add(matrix[i][right]);
            }
            right -= 1;
            if (right < left) {
                break;
            }
            for (int i = right; i >= left; i--) {
                ans.Add(matrix[bottom][i]);
            }
            bottom -= 1;
            if (bottom < top) {
                break;
            }
            for (int i = bottom; i >= top; i--) {
                ans.Add(matrix[i][left]);
            } 
            left += 1;
            if (left > right) {
                break;
            }
        }
        return ans.ToArray();
    }
}