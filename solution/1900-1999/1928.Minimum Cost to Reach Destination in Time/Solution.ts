function minCost(maxTime: number, edges: number[][], passingFees: number[]): number {
    const [m, n] = [maxTime, passingFees.length];
    const f: number[][] = Array.from({ length: m + 1 }, () => Array(n).fill(Infinity));
    f[0][0] = passingFees[0];
    for (let i = 1; i <= m; ++i) {
        for (const [x, y, t] of edges) {
            if (t <= i) {
                f[i][x] = Math.min(f[i][x], f[i - t][y] + passingFees[x]);
                f[i][y] = Math.min(f[i][y], f[i - t][x] + passingFees[y]);
            }
        }
    }
    let ans = Infinity;
    for (let i = 1; i <= m; ++i) {
        ans = Math.min(ans, f[i][n - 1]);
    }
    return ans === Infinity ? -1 : ans;
}
