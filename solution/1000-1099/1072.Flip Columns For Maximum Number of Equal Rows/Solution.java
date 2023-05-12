class Solution {
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        Map<String, Integer> cnt = new HashMap<>();
        int ans = 0;
        for (var row : matrix) {
            var sb = new StringBuilder();
            for (int x : row) {
                sb.append(row[0] == 0 ? x : x ^ 1);
            }
            ans = Math.max(ans, cnt.merge(sb.toString(), 1, Integer::sum));
        }
        return ans;
    }
}