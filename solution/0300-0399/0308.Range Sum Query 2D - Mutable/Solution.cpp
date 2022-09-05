class BinaryIndexedTree {
public:
    int n;
    vector<int> c;

    BinaryIndexedTree(int _n)
        : n(_n)
        , c(_n + 1) {}

    void update(int x, int delta) {
        while (x <= n) {
            c[x] += delta;
            x += lowbit(x);
        }
    }

    int query(int x) {
        int s = 0;
        while (x > 0) {
            s += c[x];
            x -= lowbit(x);
        }
        return s;
    }

    int lowbit(int x) {
        return x & -x;
    }
};

class NumMatrix {
public:
    vector<BinaryIndexedTree*> trees;

    NumMatrix(vector<vector<int>>& matrix) {
        int m = matrix.size();
        int n = matrix[0].size();
        trees.resize(m);
        for (int i = 0; i < m; ++i) {
            BinaryIndexedTree* tree = new BinaryIndexedTree(n);
            for (int j = 0; j < n; ++j) tree->update(j + 1, matrix[i][j]);
            trees[i] = tree;
        }
    }

    void update(int row, int col, int val) {
        BinaryIndexedTree* tree = trees[row];
        int prev = tree->query(col + 1) - tree->query(col);
        tree->update(col + 1, val - prev);
    }

    int sumRegion(int row1, int col1, int row2, int col2) {
        int s = 0;
        for (int i = row1; i <= row2; ++i) {
            BinaryIndexedTree* tree = trees[i];
            s += tree->query(col2 + 1) - tree->query(col1);
        }
        return s;
    }
};

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix* obj = new NumMatrix(matrix);
 * obj->update(row,col,val);
 * int param_2 = obj->sumRegion(row1,col1,row2,col2);
 */