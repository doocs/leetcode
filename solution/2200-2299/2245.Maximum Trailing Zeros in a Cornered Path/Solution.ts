function maxTrailingZeros(grid: number[][]): number {
    let m = grid.length,
        n = grid[0].length;
    let r2 = Array.from({ length: m + 1 }, v => new Array(n + 1).fill(0)),
        c2 = Array.from({ length: m + 1 }, v => new Array(n + 1).fill(0)),
        r5 = Array.from({ length: m + 1 }, v => new Array(n + 1).fill(0)),
        c5 = Array.from({ length: m + 1 }, v => new Array(n + 1).fill(0));
    for (let i = 1; i <= m; i++) {
        for (let j = 1; j <= n; j++) {
            let cur = grid[i - 1][j - 1];
            let two = 0,
                five = 0;
            while (cur % 2 == 0) two++, (cur /= 2);
            while (cur % 5 == 0) five++, (cur /= 5);
            r2[i][j] = r2[i - 1][j] + two;
            c2[i][j] = c2[i][j - 1] + two;
            r5[i][j] = r5[i - 1][j] + five;
            c5[i][j] = c5[i][j - 1] + five;
        }
    }
    let ans = 0;
    function getMin(i0, j0, i1, j1, i3, j3, i4, j4): number {
        // 横向开始、结束，竖向开始、结束
        const two = c2[i1][j1] - c2[i0][j0] + r2[i4][j4] - r2[i3][j3];
        const five = c5[i1][j1] - c5[i0][j0] + r5[i4][j4] - r5[i3][j3];
        return Math.min(two, five);
    }
    for (let i = 1; i <= m; i++) {
        for (let j = 1; j <= n; j++) {
            const leftToTop = getMin(i, 0, i, j, 0, j, i - 1, j),
                leftToBotton = getMin(i, 0, i, j, i, j, m, j),
                rightToTop = getMin(i, j, i, n, 0, j, i, j),
                rightToBotton = getMin(i, j, i, n, i - 1, j, m, j);
            ans = Math.max(
                leftToTop,
                leftToBotton,
                rightToTop,
                rightToBotton,
                ans,
            );
        }
    }
    return ans;
}
