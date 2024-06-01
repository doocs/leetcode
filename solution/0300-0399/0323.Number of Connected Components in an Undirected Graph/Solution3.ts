function countComponents(n: number, edges: number[][]): number {
    const g: number[][] = Array.from({ length: n }, () => []);
    const vis = new Set<number>();
    let ans = 0;

    for (const [i, j] of edges) {
        g[i].push(j);
        g[j].push(i);
    }

    const dfs = (i: number) => {
        if (vis.has(i)) return;

        vis.add(i);
        for (const j of g[i]) {
            dfs(j);
        }
    };

    for (let i = 0; i < n; i++) {
        if (vis.has(i)) continue;

        dfs(i);
        ans++;
    }

    return ans;
}
