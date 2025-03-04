function palindromePartition(s: string, k: number): number {
    const n = s.length;
    const g: number[][] = Array.from({ length: n }, () => Array(n).fill(0));
    for (let i = n - 1; i >= 0; i--) {
        for (let j = i + 1; j < n; j++) {
            g[i][j] = s[i] !== s[j] ? 1 : 0;
            if (i + 1 < j) {
                g[i][j] += g[i + 1][j - 1];
            }
        }
    }
    const f: number[][] = Array.from({ length: n + 1 }, () => Array(k + 1).fill(0));
    for (let i = 1; i <= n; i++) {
        for (let j = 1; j <= Math.min(i, k); j++) {
            if (j === 1) {
                f[i][j] = g[0][i - 1];
            } else {
                f[i][j] = 1 << 30;
                for (let h = j - 1; h < i; h++) {
                    f[i][j] = Math.min(f[i][j], f[h][j - 1] + g[h][i - 1]);
                }
            }
        }
    }
    return f[n][k];
}
