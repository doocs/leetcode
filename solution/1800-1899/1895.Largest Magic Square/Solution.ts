function largestMagicSquare(grid: number[][]): number {
    let m = grid.length,
        n = grid[0].length;
    // 前缀和
    let rowSum = Array.from({ length: m + 1 }, (v, i) =>
            new Array(n + 1).fill(0),
        ),
        colSum = Array.from({ length: m + 1 }, v => new Array(n + 1).fill(0));
    for (let i = 0; i < m; i++) {
        rowSum[i + 1][1] = grid[i][0];
        for (let j = 1; j < n; j++) {
            rowSum[i + 1][j + 1] = rowSum[i + 1][j] + grid[i][j];
        }
    }

    for (let j = 0; j < n; j++) {
        colSum[1][j + 1] = grid[0][j];
        for (let i = 1; i < m; i++) {
            colSum[i + 1][j + 1] = colSum[i][j + 1] + grid[i][j];
        }
    }
    // console.log(rowSum, colSum)
    // 寻找最大k
    for (let k = Math.min(m, n); k > 1; k--) {
        for (let i = 0; i + k - 1 < m; i++) {
            for (let j = 0; j + k - 1 < n; j++) {
                let x2 = i + k - 1,
                    y2 = j + k - 1;
                if (valid(grid, rowSum, colSum, i, j, x2, y2)) {
                    return k;
                }
            }
        }
    }
    return 1;
}

function valid(
    grid: number[][],
    rowSum: number[][],
    colSum: number[][],
    x1: number,
    y1: number,
    x2: number,
    y2: number,
): boolean {
    let diff = rowSum[x1 + 1][y2 + 1] - rowSum[x1 + 1][y1];
    // 行
    for (let i = x1 + 1; i <= x2; i++) {
        if (diff != rowSum[i + 1][y2 + 1] - rowSum[i + 1][y1]) {
            return false;
        }
    }
    // 列
    for (let j = y1; j <= y2; j++) {
        if (diff != colSum[x2 + 1][j + 1] - colSum[x1][j + 1]) {
            return false;
        }
    }
    // 主队对角线
    let mainSum = diff;
    for (let i = x1, j = y1; i <= x2; i++, j++) {
        mainSum -= grid[i][j];
    }
    if (mainSum != 0) return false;
    // 副对角线
    let subSum = diff;
    for (let i = x1, j = y2; i <= x2; i++, j--) {
        subSum -= grid[i][j];
    }
    if (subSum != 0) return false;
    return true;
}
