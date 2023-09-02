function numWays(words: string[], target: string): number {
    const m = target.length;
    const n = words[0].length;
    const f = new Array(m + 1).fill(0).map(() => new Array(n + 1).fill(0));
    const mod = 1e9 + 7;
    for (let j = 0; j <= n; ++j) {
        f[0][j] = 1;
    }
    const cnt = new Array(n).fill(0).map(() => new Array(26).fill(0));
    for (const w of words) {
        for (let j = 0; j < n; ++j) {
            ++cnt[j][w.charCodeAt(j) - 97];
        }
    }
    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            f[i][j] = f[i][j - 1] + f[i - 1][j - 1] * cnt[j - 1][target.charCodeAt(i - 1) - 97];
            f[i][j] %= mod;
        }
    }
    return f[m][n];
}
