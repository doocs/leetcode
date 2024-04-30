function stoneGameVII(stones: number[]): number {
    const n = stones.length;
    const s: number[] = Array(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        s[i + 1] = s[i] + stones[i];
    }
    const f: number[][] = Array.from({ length: n }, () => Array(n).fill(0));
    const dfs = (i: number, j: number): number => {
        if (i > j) {
            return 0;
        }
        if (f[i][j]) {
            return f[i][j];
        }
        const a = s[j + 1] - s[i + 1] - dfs(i + 1, j);
        const b = s[j] - s[i] - dfs(i, j - 1);
        return (f[i][j] = Math.max(a, b));
    };
    return dfs(0, n - 1);
}
