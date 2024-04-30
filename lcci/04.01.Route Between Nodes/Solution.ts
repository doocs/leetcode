function findWhetherExistsPath(
    n: number,
    graph: number[][],
    start: number,
    target: number,
): boolean {
    const g: number[][] = Array.from({ length: n }, () => []);
    const vis: boolean[] = Array.from({ length: n }, () => false);
    for (const [a, b] of graph) {
        g[a].push(b);
    }
    const dfs = (i: number): boolean => {
        if (i === target) {
            return true;
        }
        if (vis[i]) {
            return false;
        }
        vis[i] = true;
        return g[i].some(dfs);
    };
    return dfs(start);
}
