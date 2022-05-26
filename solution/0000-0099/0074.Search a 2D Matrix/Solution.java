class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int x = 0,y = matrix.length-1;
        if(y<0) return false;
        int maxX = matrix[0].length-1;
        while ((x <= maxX) && (y >= 0)) {
            int cur = matrix[y][x];
            if (cur == target) return false;
            if (cur < target) x++;
            else y--;
        }
        return false;
    }
}