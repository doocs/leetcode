public class Solution {
    public int MaximalRectangle(char[][] matrix) {
        int n = matrix[0].Length;
        int[] heights = new int[n];
        int ans = 0;

        foreach (var row in matrix) {
            for (int j = 0; j < n; ++j) {
                if (row[j] == '1') {
                    heights[j] += 1;
                } else {
                    heights[j] = 0;
                }
            }
            ans = Math.Max(ans, LargestRectangleArea(heights));
        }

        return ans;
    }

    private int LargestRectangleArea(int[] heights) {
        int res = 0, n = heights.Length;
        Stack<int> stk = new Stack<int>();
        int[] left = new int[n];
        int[] right = new int[n];

        Array.Fill(right, n);

        for (int i = 0; i < n; ++i) {
            while (stk.Count > 0 && heights[stk.Peek()] >= heights[i]) {
                right[stk.Pop()] = i;
            }
            left[i] = stk.Count == 0 ? -1 : stk.Peek();
            stk.Push(i);
        }

        for (int i = 0; i < n; ++i) {
            res = Math.Max(res, heights[i] * (right[i] - left[i] - 1));
        }

        return res;
    }
}
