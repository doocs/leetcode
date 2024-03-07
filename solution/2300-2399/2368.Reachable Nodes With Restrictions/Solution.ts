function reachableNodes(n: number, edges: number[][], restricted: number[]): number {
    const vis: boolean[] = Array(n).fill(false);
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    for (const i of restricted) {
        vis[i] = true;
    }
    const dfs = (i: number): number => {
        vis[i] = true;
        let ans = 1;
        for (const j of g[i]) {
            if (!vis[j]) {
                ans += dfs(j);
            }
        }
        return ans;
    };
    return dfs(0);
}
