function tallestBillboard(rods: number[]): number {
    const s = rods.reduce((a, b) => a + b, 0);
    const n = rods.length;
    const f = new Array(n).fill(0).map(() => new Array(s + 1).fill(-1));
    const dfs = (i: number, j: number): number => {
        if (i >= n) {
            return j === 0 ? 0 : -(1 << 30);
        }
        if (f[i][j] !== -1) {
            return f[i][j];
        }
        let ans = Math.max(dfs(i + 1, j), dfs(i + 1, j + rods[i]));
        ans = Math.max(ans, dfs(i + 1, Math.abs(j - rods[i])) + Math.min(j, rods[i]));
        return (f[i][j] = ans);
    };
    return dfs(0, 0);
}
