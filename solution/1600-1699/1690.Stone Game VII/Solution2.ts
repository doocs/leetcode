function stoneGameVII(stones: number[]): number {
    const n = stones.length;
    const s: number[] = Array(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        s[i + 1] = s[i] + stones[i];
    }
    const f: number[][] = Array.from({ length: n }, () => Array(n).fill(0));
    for (let i = n - 2; ~i; --i) {
        for (let j = i + 1; j < n; ++j) {
            f[i][j] = Math.max(s[j + 1] - s[i + 1] - f[i + 1][j], s[j] - s[i] - f[i][j - 1]);
        }
    }
    return f[0][n - 1];
}
