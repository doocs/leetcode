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
    const q: number[] = [start];
    vis[start] = true;
    while (q.length > 0) {
        const i = q.pop()!;
        if (i === target) {
            return true;
        }
        for (const j of g[i]) {
            if (!vis[j]) {
                vis[j] = true;
                q.push(j);
            }
        }
    }
    return false;
}
