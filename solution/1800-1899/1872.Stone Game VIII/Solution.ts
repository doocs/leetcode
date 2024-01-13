function stoneGameVIII(stones: number[]): number {
    const n = stones.length;
    const f: number[] = Array(n).fill(-1);
    for (let i = 1; i < n; ++i) {
        stones[i] += stones[i - 1];
    }
    const dfs = (i: number): number => {
        if (i >= n - 1) {
            return stones[i];
        }
        if (f[i] === -1) {
            f[i] = Math.max(dfs(i + 1), stones[i] - dfs(i + 1));
        }
        return f[i];
    };
    return dfs(1);
}
