function validPath(n: number, edges: number[][], source: number, destination: number): boolean {
    if (source === destination) {
        return true;
    }
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [u, v] of edges) {
        g[u].push(v);
        g[v].push(u);
    }
    const vis: boolean[] = Array(n).fill(false);
    vis[source] = true;
    const stk: number[] = [source];
    while (stk.length) {
        const i = stk.pop()!;
        for (const j of g[i]) {
            if (j === destination) {
                return true;
            }
            if (!vis[j]) {
                vis[j] = true;
                stk.push(j);
            }
        }
    }
    return false;
}
