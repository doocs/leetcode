class Solution {
    public int numSpecial(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;
        
        int[] rows1 = new int[rows];
        int[] cols1 = new int[cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mat[i][j] == 1) {
                    rows1[i]++;
                    cols1[j]++;
                }
            }
        }
        
        int ans = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mat[i][j] == 1 && rows1[i] == 1 && cols1[j] == 1) {
                    ans ++;
                } 
            }
        }
        
        return ans;
    }
}