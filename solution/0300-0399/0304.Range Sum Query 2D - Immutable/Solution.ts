class NumMatrix {
    private s: number[][];

    constructor(matrix: number[][]) {
        const m = matrix.length;
        const n = matrix[0].length;
        this.s = new Array(m + 1).fill(0).map(() => new Array(n + 1).fill(0));
        for (let i = 0; i < m; ++i) {
            for (let j = 0; j < n; ++j) {
                this.s[i + 1][j + 1] =
                    this.s[i + 1][j] +
                    this.s[i][j + 1] -
                    this.s[i][j] +
                    matrix[i][j];
            }
        }
    }

    sumRegion(row1: number, col1: number, row2: number, col2: number): number {
        return (
            this.s[row2 + 1][col2 + 1] -
            this.s[row2 + 1][col1] -
            this.s[row1][col2 + 1] +
            this.s[row1][col1]
        );
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * var obj = new NumMatrix(matrix)
 * var param_1 = obj.sumRegion(row1,col1,row2,col2)
 */
