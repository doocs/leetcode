public class Solution {
    public int[] FindDiagonalOrder(int[][] mat) {
        int m = mat.Length;
        int n = mat[0].Length;
        List<int> ans = new List<int>();
        for (int k = 0; k < m + n - 1; k++) {
            List<int> t = new List<int>();
            int i = k < n ? 0 : k - n + 1;
            int j = k < n ? k : n - 1;
            while (i < m && j >= 0) {
                t.Add(mat[i][j]);
                i++;
                j--;
            }
            if (k % 2 == 0) {
                t.Reverse();
            }
            ans.AddRange(t);
        }
        return ans.ToArray();
    }
}
