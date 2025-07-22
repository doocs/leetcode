function minDistance(word1: string, word2: string): number {
    const m = word1.length;
    const n = word2.length;
    const f: number[][] = Array.from({ length: m + 1 }, () => Array(n + 1).fill(0));
    for (let i = 1; i <= m; ++i) {
        f[i][0] = i;
    }
    for (let j = 1; j <= n; ++j) {
        f[0][j] = j;
    }
    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            if (word1[i - 1] === word2[j - 1]) {
                f[i][j] = f[i - 1][j - 1];
            } else {
                f[i][j] = Math.min(f[i - 1][j], f[i][j - 1]) + 1;
            }
        }
    }
    return f[m][n];
}
