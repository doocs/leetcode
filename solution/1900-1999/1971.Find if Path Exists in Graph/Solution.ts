function validPath(n: number, edges: number[][], source: number, destination: number): boolean {
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }

    const vis = new Set<number>();
    const dfs = (i: number) => {
        if (i === destination) {
            return true;
        }
        if (vis.has(i)) {
            return false;
        }

        vis.add(i);
        return g[i].some(dfs);
    };

    return dfs(source);
}
