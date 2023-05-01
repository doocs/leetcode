function numMusicPlaylists(n: number, goal: number, k: number): number {
    const mod = 1e9 + 7;
    const f = new Array(goal + 1).fill(0).map(() => new Array(n + 1).fill(0));
    f[0][0] = 1;
    for (let i = 1; i <= goal; ++i) {
        for (let j = 1; j <= n; ++j) {
            f[i][j] = f[i - 1][j - 1] * (n - j + 1);
            if (j > k) {
                f[i][j] += f[i - 1][j] * (j - k);
            }
            f[i][j] %= mod;
        }
    }
    return f[goal][n];
}
