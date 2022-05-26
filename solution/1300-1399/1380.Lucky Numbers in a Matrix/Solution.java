class Solution {
    public List<Integer> luckyNumbers (int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        Set<Integer> rowMin = new HashSet<>();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < n; ++j) {
                min = Math.min(min, matrix[i][j]);
            }
            rowMin.add(min);
        }

        for (int j = 0; j < n; ++j) {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < m; ++i) {
                max = Math.max(max, matrix[i][j]);
            }
            if (rowMin.contains(max)) {
                res.add(max);
            }

        }
        return res;
    }
}