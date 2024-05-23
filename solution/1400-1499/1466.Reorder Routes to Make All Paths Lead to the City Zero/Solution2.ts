function minReorder(n: number, connections: number[][]): number {
    const g: [number, number][][] = Array.from({ length: n }, () => []);
    for (const [a, b] of connections) {
        g[a].push([b, 1]);
        g[b].push([a, 0]);
    }

    const q: number[] = [0];
    const vis = new Set<number>();
    vis.add(0);

    let ans = 0;
    while (q.length) {
        const a = q.pop()!;
        for (const [b, c] of g[a]) {
            if (!vis.has(b)) {
                vis.add(b);
                q.push(b);
                ans += c;
            }
        }
    }
    return ans;
}
