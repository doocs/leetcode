class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new ArrayList<Integer>();
        }
        int m = matrix.length;
        int n = matrix[0].length;
        
        int m1 = 0;
        int n1 = 0;
        int m2 = m - 1;
        int n2 = n - 1;
        List<Integer> res = new ArrayList<>();
        
        while (m1 <= m2 && n1 <= n2) {
            goCircle(res, matrix, m1++, n1++, m2--, n2--);
        }
        
        return res;
    }
    
    private void goCircle(List<Integer> res, int[][] matrix, int m1, int n1, int m2, int n2) {
        
        if (m1 == m2) {
            for (int j = n1; j <= n2; ++j) {
                res.add(matrix[m1][j]);
            }
        } else if (n1 == n2) {
            for (int i = m1; i <= m2; ++i) {
                res.add(matrix[i][n1]);
            }
        } else {
            for (int j = n1; j < n2; ++j) {
                res.add(matrix[m1][j]);
            }
            for (int i = m1; i < m2; ++i) {
                res.add(matrix[i][n2]);
            }
            for (int j = n2; j > n1; --j) {
                res.add(matrix[m2][j]);
            }
            for (int i = m2; i > m1; --i) {
                res.add(matrix[i][n1]);
            }
        }
        
        
    }
}