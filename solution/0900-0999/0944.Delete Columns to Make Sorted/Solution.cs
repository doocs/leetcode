public class Solution {
    public int MinDeletionSize(string[] strs) {
        int m = strs[0].Length;
        int n = strs.Length;
        int ans = 0;

        for (int j = 0; j < m; ++j) {
            for (int i = 1; i < n; ++i) {
                if (strs[i][j] < strs[i - 1][j]) {
                    ++ans;
                    break;
                }
            }
        }

        return ans;
    }
}
