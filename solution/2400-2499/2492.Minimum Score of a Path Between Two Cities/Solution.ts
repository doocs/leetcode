function minScore(n: number, roads: number[][]): number {
    const vis = new Array(n + 1).fill(false);
    const g = Array.from({ length: n + 1 }, () => []);
    for (const [a, b, v] of roads) {
        g[a].push([b, v]);
        g[b].push([a, v]);
    }
    let ans = Infinity;
    const dfs = (i: number) => {
        if (vis[i]) {
            return;
        }
        vis[i] = true;
        for (const [j, v] of g[i]) {
            ans = Math.min(ans, v);
            dfs(j);
        }
    };
    dfs(1);
    return ans;
}
