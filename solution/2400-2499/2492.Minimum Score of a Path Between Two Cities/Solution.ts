function minScore(n: number, roads: number[][]): number {
    const g: [number, number][][] = Array.from({ length: n + 1 }, () => []);
    for (const [a, b, w] of roads) {
        g[a].push([b, w]);
        g[b].push([a, w]);
    }

    const vis = new Array(n + 1).fill(false);
    let ans = Infinity;

    const dfs = (a: number): void => {
        vis[a] = true;
        for (const [b, w] of g[a]) {
            ans = Math.min(ans, w);
            if (!vis[b]) {
                dfs(b);
            }
        }
    };

    dfs(1);
    return ans;
}
