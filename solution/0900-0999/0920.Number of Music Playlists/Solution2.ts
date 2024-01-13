function numMusicPlaylists(n: number, goal: number, k: number): number {
    const mod = 1e9 + 7;
    let f = new Array(goal + 1).fill(0);
    f[0] = 1;
    for (let i = 1; i <= goal; ++i) {
        const g = new Array(goal + 1).fill(0);
        for (let j = 1; j <= n; ++j) {
            g[j] = f[j - 1] * (n - j + 1);
            if (j > k) {
                g[j] += f[j] * (j - k);
            }
            g[j] %= mod;
        }
        f = g;
    }
    return f[n];
}
