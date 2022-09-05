class SubrectangleQueries {
public:
    vector<vector<int>> g;
    vector<vector<int>> ops;

    SubrectangleQueries(vector<vector<int>>& rectangle) {
        g = rectangle;
    }

    void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
        ops.push_back({row1, col1, row2, col2, newValue});
    }

    int getValue(int row, int col) {
        for (int i = ops.size() - 1; ~i; --i) {
            auto op = ops[i];
            if (op[0] <= row && row <= op[2] && op[1] <= col && col <= op[3]) {
                return op[4];
            }
        }
        return g[row][col];
    }
};

/**
 * Your SubrectangleQueries object will be instantiated and called as such:
 * SubrectangleQueries* obj = new SubrectangleQueries(rectangle);
 * obj->updateSubrectangle(row1,col1,row2,col2,newValue);
 * int param_2 = obj->getValue(row,col);
 */