function minReorder(n: number, connections: number[][]): number {
    const roads: Record<number, Set<number>> = {};
    const graph: Record<number, number[]> = {};
    const seen = new Set<number>();

    for (const [k, v] of connections) {
        (roads[k] ?? (roads[k] = new Set())).add(v);
        (graph[k] ?? (graph[k] = [])).push(v);
        (graph[v] ?? (graph[v] = [])).push(k);
    }

    const xs = [0];
    let res = 0;

    while (xs.length) {
        const x = xs.pop()!;

        if (seen.has(x)) continue;
        seen.add(x);

        if (graph[x]) xs.push(...graph[x]);

        for (const neighbour of graph[x]) {
            if (!seen.has(neighbour) && !roads?.[neighbour]?.has(x)) {
                res++;
            }
        }
    }

    return res;
}
