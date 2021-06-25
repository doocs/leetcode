class SubrectangleQueries {
    private int[][] rec;
    private List<int[]> history;

    public SubrectangleQueries(int[][] rectangle) {
        rec = rectangle;
        history = new ArrayList<>();
    }
    
    public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
        history.add(new int[]{row1, col1, row2, col2, newValue});
    }
    
    public int getValue(int row, int col) {
        for (int i = history.size() - 1; i >= 0; --i) {
            int[] record = history.get(i);
            if (row >= record[0] && row <= record[2] && col >= record[1] && col <= record[3]) {
                return record[4];
            }
        }
        return rec[row][col];
    }
}

/**
 * Your SubrectangleQueries object will be instantiated and called as such:
 * SubrectangleQueries obj = new SubrectangleQueries(rectangle);
 * obj.updateSubrectangle(row1,col1,row2,col2,newValue);
 * int param_2 = obj.getValue(row,col);
 */