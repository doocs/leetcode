function maxPalindromes(s: string, k: number): number {
    const n = s.length;
    const g: boolean[][] = Array.from({ length: n }, () => Array(n).fill(true));
    for (let i = n - 1; ~i; --i) {
        for (let j = i + 1; j < n; ++j) {
            g[i][j] = s[i] === s[j] && g[i + 1][j - 1];
        }
    }
    const f: number[] = Array(n + 1).fill(0);
    for (let i = n - 1; ~i; --i) {
        f[i] = f[i + 1];
        for (let j = i + k - 1; j < n; ++j) {
            if (g[i][j]) {
                f[i] = Math.max(f[i], 1 + f[j + 1]);
            }
        }
    }
    return f[0];
}
