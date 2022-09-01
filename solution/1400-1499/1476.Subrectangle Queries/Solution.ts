class SubrectangleQueries {
    g: number[][];
    ops: number[][];
    constructor(rectangle: number[][]) {
        this.g = rectangle;
        this.ops = [];
    }

    updateSubrectangle(
        row1: number,
        col1: number,
        row2: number,
        col2: number,
        newValue: number,
    ): void {
        this.ops.push([row1, col1, row2, col2, newValue]);
    }

    getValue(row: number, col: number): number {
        for (let i = this.ops.length - 1; ~i; --i) {
            const [r1, c1, r2, c2, v] = this.ops[i];
            if (r1 <= row && row <= r2 && c1 <= col && col <= c2) {
                return v;
            }
        }
        return this.g[row][col];
    }
}

/**
 * Your SubrectangleQueries object will be instantiated and called as such:
 * var obj = new SubrectangleQueries(rectangle)
 * obj.updateSubrectangle(row1,col1,row2,col2,newValue)
 * var param_2 = obj.getValue(row,col)
 */
