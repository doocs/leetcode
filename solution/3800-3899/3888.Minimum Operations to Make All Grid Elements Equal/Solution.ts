function minOperations(grid: number[][], k: number): number {
    const m = grid.length;
    const n = grid[0].length;
    let maxVal = grid[0][0];

    for (const row of grid) {
        for (const val of row) {
            maxVal = Math.max(maxVal, val);
        }
    }

    const check = (target: number): number => {
        const diff: number[][] = Array.from({ length: m + 2 }, () => Array(n + 2).fill(0));
        let totalOps = 0;

        for (let i = 1; i <= m; i++) {
            for (let j = 1; j <= n; j++) {
                diff[i][j] += diff[i - 1][j] + diff[i][j - 1] - diff[i - 1][j - 1];
                const curVal = grid[i - 1][j - 1] + diff[i][j];

                if (curVal > target) return -1;

                if (curVal < target) {
                    if (i + k - 1 > m || j + k - 1 > n) return -1;

                    const needed = target - curVal;
                    totalOps += needed;
                    diff[i][j] += needed;
                    diff[i + k][j] -= needed;
                    diff[i][j + k] -= needed;
                    diff[i + k][j + k] += needed;
                }
            }
        }

        return totalOps;
    };

    for (let t = maxVal; t <= maxVal + 1; t++) {
        const res = check(t);
        if (res !== -1) return res;
    }

    return -1;
}
