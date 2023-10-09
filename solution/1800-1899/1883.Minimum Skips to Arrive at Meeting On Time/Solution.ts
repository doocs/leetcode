function minSkips(dist: number[], speed: number, hoursBefore: number): number {
    const n = dist.length;
    const f = Array.from({ length: n + 1 }, () => Array.from({ length: n + 1 }, () => Infinity));
    f[0][0] = 0;
    const eps = 1e-8;
    for (let i = 1; i <= n; ++i) {
        for (let j = 0; j <= i; ++j) {
            if (j < i) {
                f[i][j] = Math.min(f[i][j], Math.ceil(f[i - 1][j] + dist[i - 1] / speed - eps));
            }
            if (j) {
                f[i][j] = Math.min(f[i][j], f[i - 1][j - 1] + dist[i - 1] / speed);
            }
        }
    }
    for (let j = 0; j <= n; ++j) {
        if (f[n][j] <= hoursBefore + eps) {
            return j;
        }
    }
    return -1;
}
