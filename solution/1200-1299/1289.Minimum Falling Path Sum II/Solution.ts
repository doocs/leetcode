function minFallingPathSum(grid: number[][]): number {
    const n = grid.length;
    const f: number[] = Array(n).fill(0);
    for (const row of grid) {
        const g = [...row];
        for (let i = 0; i < n; ++i) {
            let t = Infinity;
            for (let j = 0; j < n; ++j) {
                if (j !== i) {
                    t = Math.min(t, f[j]);
                }
            }
            g[i] += t === Infinity ? 0 : t;
        }
        f.splice(0, n, ...g);
    }
    return Math.min(...f);
}
