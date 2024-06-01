function countComponents(n: number, edges: number[][]): number {
    const graph: Map<number, number[]> = new Map(Array.from({ length: n }, (_, i) => [i, []]));
    const vis = new Set<number>();
    let ans = 0;

    for (const [a, b] of edges) {
        graph.get(a)!.push(b);
        graph.get(b)!.push(a);
    }

    for (const [i] of graph) {
        if (vis.has(i)) continue;

        const q = [i];
        for (const j of q) {
            if (vis.has(j)) continue;

            vis.add(j);
            q.push(...graph.get(j)!);
        }

        ans++;
    }

    return ans;
}
