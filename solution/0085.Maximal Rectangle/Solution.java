class Solution {
    public int maximalRectangle(char[][] matrix) {
        if(matrix==null || matrix.length==0) return 0;
        int result = 0;
        int[] row = new int[matrix[0].length];
        for(char[] line : matrix){
            update(line,row);
            result = Math.max(result, largestRectangleArea(row));
        }
        return result;
    }
    private int largestRectangleArea(int[] heights) {
        int[] stack = new int[1 << 10];
        int length = heights.length;
        int j, stackSize= 0, ma = 0, a;
        for (int i = 0; i <= length; i++) {
            while (stackSize > 0 &&( i==length || heights[i] < heights[stack[stackSize - 1]])) {
                j = stack[--stackSize];
                a = (i - (stackSize == 0 ? 0 : stack[stackSize - 1] + 1)) * (heights[j]);
                if (a > ma) ma = a;
            }
            stack[stackSize++] = i;
        }
        return ma;
    }
    private void update(char[] line, int[] row){
        for (int i = 0; i < row.length; i++) {
            if (line[i] == '0') row[i] = 0;
            else row[i]++;
        }
    }
}