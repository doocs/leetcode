class SubrectangleQueries {
    private int[][] g;
    private LinkedList<int[]> ops = new LinkedList<>();

    public SubrectangleQueries(int[][] rectangle) {
        g = rectangle;
    }

    public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
        ops.addFirst(new int[] {row1, col1, row2, col2, newValue});
    }

    public int getValue(int row, int col) {
        for (var op : ops) {
            if (op[0] <= row && row <= op[2] && op[1] <= col && col <= op[3]) {
                return op[4];
            }
        }
        return g[row][col];
    }
}

/**
 * Your SubrectangleQueries object will be instantiated and called as such:
 * SubrectangleQueries obj = new SubrectangleQueries(rectangle);
 * obj.updateSubrectangle(row1,col1,row2,col2,newValue);
 * int param_2 = obj.getValue(row,col);
 */