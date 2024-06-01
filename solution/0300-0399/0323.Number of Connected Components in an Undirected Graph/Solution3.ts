function countComponents(n: number, edges: number[][]): number {
    const g: Map<number, number[]> = new Map(Array.from({ length: n }, (_, i) => [i, []]));
    for (const [a, b] of edges) {
        g.get(a)!.push(b);
        g.get(b)!.push(a);
    }

    const vis = new Set<number>();
    let ans = 0;
    for (const [i] of g) {
        if (vis.has(i)) {
            continue;
        }
        const q = [i];
        for (const j of q) {
            if (vis.has(j)) {
                continue;
            }
            vis.add(j);
            q.push(...g.get(j)!);
        }
        ans++;
    }
    return ans;
}
