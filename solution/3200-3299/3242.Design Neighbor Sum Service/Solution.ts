class NeighborSum {
    private grid: number[][];
    private d: Map<number, [number, number]> = new Map();
    private dirs: number[][] = [
        [-1, 0, 1, 0, -1],
        [-1, 1, 1, -1, -1],
    ];
    constructor(grid: number[][]) {
        for (let i = 0; i < grid.length; ++i) {
            for (let j = 0; j < grid[0].length; ++j) {
                this.d.set(grid[i][j], [i, j]);
            }
        }
        this.grid = grid;
    }

    adjacentSum(value: number): number {
        return this.cal(value, 0);
    }

    diagonalSum(value: number): number {
        return this.cal(value, 1);
    }

    cal(value: number, k: number): number {
        const [i, j] = this.d.get(value)!;
        let s = 0;
        for (let q = 0; q < 4; ++q) {
            const [x, y] = [i + this.dirs[k][q], j + this.dirs[k][q + 1]];
            if (x >= 0 && x < this.grid.length && y >= 0 && y < this.grid[0].length) {
                s += this.grid[x][y];
            }
        }
        return s;
    }
}

/**
 * Your NeighborSum object will be instantiated and called as such:
 * var obj = new NeighborSum(grid)
 * var param_1 = obj.adjacentSum(value)
 * var param_2 = obj.diagonalSum(value)
 */
