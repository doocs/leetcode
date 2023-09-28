function minDifficulty(jobDifficulty: number[], d: number): number {
    const n = jobDifficulty.length;
    const inf = 1 << 30;
    const f: number[][] = new Array(n + 1).fill(0).map(() => new Array(d + 1).fill(inf));
    f[0][0] = 0;
    for (let i = 1; i <= n; ++i) {
        for (let j = 1; j <= Math.min(d, i); ++j) {
            let mx = 0;
            for (let k = i; k > 0; --k) {
                mx = Math.max(mx, jobDifficulty[k - 1]);
                f[i][j] = Math.min(f[i][j], f[k - 1][j - 1] + mx);
            }
        }
    }
    return f[n][d] < inf ? f[n][d] : -1;
}
