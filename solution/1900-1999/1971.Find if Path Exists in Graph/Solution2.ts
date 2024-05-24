function validPath(n: number, edges: number[][], source: number, destination: number): boolean {
    const g: number[][] = Array.from({ length: n }, () => []);

    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }

    const vis = new Set<number>();
    const q = [source];

    while (q.length) {
        const i = q.pop()!;
        if (i === destination) {
            return true;
        }
        if (vis.has(i)) {
            continue;
        }
        vis.add(i);
        q.push(...g[i]);
    }

    return false;
}
