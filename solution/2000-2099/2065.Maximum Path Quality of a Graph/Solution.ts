function maximalPathQuality(values: number[], edges: number[][], maxTime: number): number {
    const n = values.length;
    const g: [number, number][][] = Array.from({ length: n }, () => []);
    for (const [u, v, t] of edges) {
        g[u].push([v, t]);
        g[v].push([u, t]);
    }
    const vis: boolean[] = Array(n).fill(false);
    vis[0] = true;
    let ans = 0;
    const dfs = (u: number, cost: number, value: number) => {
        if (u === 0) {
            ans = Math.max(ans, value);
        }
        for (const [v, t] of g[u]) {
            if (cost + t <= maxTime) {
                if (vis[v]) {
                    dfs(v, cost + t, value);
                } else {
                    vis[v] = true;
                    dfs(v, cost + t, value + values[v]);
                    vis[v] = false;
                }
            }
        }
    };
    dfs(0, 0, values[0]);
    return ans;
}
