function numWays(words: string[], target: string): number {
    const m = target.length;
    const n = words[0].length;
    const mod = 1e9 + 7;
    const cnt = new Array(n).fill(0).map(() => new Array(26).fill(0));
    for (const w of words) {
        for (let j = 0; j < n; ++j) {
            ++cnt[j][w.charCodeAt(j) - 97];
        }
    }
    const f = new Array(m + 1).fill(0).map(() => new Array(n + 1).fill(0));
    for (let j = 0; j <= n; ++j) {
        f[m][j] = 1;
    }
    for (let i = m - 1; i >= 0; --i) {
        for (let j = n - 1; j >= 0; --j) {
            const ans = f[i][j + 1] + f[i + 1][j + 1] * cnt[j][target.charCodeAt(i) - 97];
            f[i][j] = ans % mod;
        }
    }
    return f[0][0];
}
