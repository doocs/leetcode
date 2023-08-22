function getProbability(balls: number[]): number {
    const n = balls.reduce((a, b) => a + b, 0) >> 1;
    const mx = Math.max(...balls);
    const m = Math.max(mx, n << 1);
    const c: number[][] = Array(m + 1)
        .fill(0)
        .map(() => Array(m + 1).fill(0));
    for (let i = 0; i <= m; ++i) {
        c[i][0] = 1;
        for (let j = 1; j <= i; ++j) {
            c[i][j] = c[i - 1][j - 1] + c[i - 1][j];
        }
    }
    const k = balls.length;
    const f: number[][][] = Array(k)
        .fill(0)
        .map(() =>
            Array(n + 1)
                .fill(0)
                .map(() => Array((k << 1) | 1).fill(-1)),
        );
    const dfs = (i: number, j: number, diff: number): number => {
        if (i >= k) {
            return j === 0 && diff === k ? 1 : 0;
        }
        if (j < 0) {
            return 0;
        }
        if (f[i][j][diff] !== -1) {
            return f[i][j][diff];
        }
        let ans = 0;
        for (let x = 0; x <= balls[i]; ++x) {
            const y = x === balls[i] ? 1 : x === 0 ? -1 : 0;
            ans += dfs(i + 1, j - x, diff + y) * c[balls[i]][x];
        }
        return (f[i][j][diff] = ans);
    };
    return dfs(0, n, k) / c[n << 1][n];
}
