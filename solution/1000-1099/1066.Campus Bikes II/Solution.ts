function assignBikes(workers: number[][], bikes: number[][]): number {
    const n = workers.length;
    const m = bikes.length;
    const inf = 1 << 30;
    const f: number[][] = new Array(n + 1).fill(0).map(() => new Array(1 << m).fill(inf));
    f[0][0] = 0;
    for (let i = 1; i <= n; ++i) {
        for (let j = 0; j < 1 << m; ++j) {
            for (let k = 0; k < m; ++k) {
                if (((j >> k) & 1) === 1) {
                    const d =
                        Math.abs(workers[i - 1][0] - bikes[k][0]) +
                        Math.abs(workers[i - 1][1] - bikes[k][1]);
                    f[i][j] = Math.min(f[i][j], f[i - 1][j ^ (1 << k)] + d);
                }
            }
        }
    }
    return Math.min(...f[n]);
}
