function minimumMoves(grid: number[][]): number {
    const left: number[][] = [];
    const right: number[][] = [];
    for (let i = 0; i < 3; ++i) {
        for (let j = 0; j < 3; ++j) {
            if (grid[i][j] === 0) {
                left.push([i, j]);
            } else {
                for (let k = 1; k < grid[i][j]; ++k) {
                    right.push([i, j]);
                }
            }
        }
    }
    const cal = (a: number[], b: number[]) => {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    };
    const n = left.length;
    const f: number[] = Array(1 << n).fill(1 << 30);
    f[0] = 0;
    for (let i = 0; i < 1 << n; ++i) {
        let k = 0;
        for (let j = 0; j < n; ++j) {
            if ((i >> j) & 1) {
                ++k;
            }
        }
        for (let j = 0; j < n; ++j) {
            if ((i >> j) & 1) {
                f[i] = Math.min(f[i], f[i ^ (1 << j)] + cal(left[k - 1], right[j]));
            }
        }
    }
    return f[(1 << n) - 1];
}
