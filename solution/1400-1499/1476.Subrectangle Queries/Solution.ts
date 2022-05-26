class SubrectangleQueries {
    grid: number[][];
    history: number[][];
    constructor(rectangle: number[][]) {
        this.grid = rectangle;
        this.history = [];
    }

    updateSubrectangle(
        row1: number,
        col1: number,
        row2: number,
        col2: number,
        newValue: number,
    ): void {
        this.history.push([row1, col1, row2, col2, newValue]);
    }

    getValue(row: number, col: number): number {
        for (let i = this.history.length - 1; i >= 0; --i) {
            let [row1, col1, row2, col2, newValue] = this.history[i];
            if (row >= row1 && row <= row2 && col >= col1 && col <= col2) {
                return newValue;
            }
        }
        return this.grid[row][col];
    }
}

/**
 * Your SubrectangleQueries object will be instantiated and called as such:
 * var obj = new SubrectangleQueries(rectangle)
 * obj.updateSubrectangle(row1,col1,row2,col2,newValue)
 * var param_2 = obj.getValue(row,col)
 */
