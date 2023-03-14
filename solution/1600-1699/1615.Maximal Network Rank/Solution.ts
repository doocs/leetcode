function maximalNetworkRank(n: number, roads: number[][]): number {
    const g: number[][] = Array.from(new Array(n), () => new Array(n).fill(0));
    const cnt: number[] = new Array(n).fill(0);
    for (const [a, b] of roads) {
        g[a][b] = 1;
        g[b][a] = 1;
        ++cnt[a];
        ++cnt[b];
    }
    let ans = 0;
    for (let a = 0; a < n; ++a) {
        for (let b = a + 1; b < n; ++b) {
            ans = Math.max(ans, cnt[a] + cnt[b] - g[a][b]);
        }
    }
    return ans;
}
