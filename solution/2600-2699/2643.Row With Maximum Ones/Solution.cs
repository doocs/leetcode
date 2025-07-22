public class Solution {
    public int[] RowAndMaximumOnes(int[][] mat) {
        int[] ans = new int[2];
        for (int i = 0; i < mat.Length; i++) {
            int cnt = mat[i].Sum();
            if (ans[1] < cnt) {
                ans = new int[] { i, cnt };
            }
        }
        return ans;
    }
}
