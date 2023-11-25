function distanceToCycle(n: number, edges: number[][]): number[] {
    const g: Set<number>[] = new Array(n).fill(0).map(() => new Set<number>());
    for (const [a, b] of edges) {
        g[a].add(b);
        g[b].add(a);
    }
    const q: number[] = [];
    for (let i = 0; i < n; ++i) {
        if (g[i].size === 1) {
            q.push(i);
        }
    }
    const f: number[] = Array(n).fill(0);
    const seq: number[] = [];
    while (q.length) {
        const i = q.pop()!;
        seq.push(i);
        for (const j of g[i]) {
            g[j].delete(i);
            f[i] = j;
            if (g[j].size === 1) {
                q.push(j);
            }
        }
        g[i].clear();
    }
    const ans: number[] = Array(n).fill(0);
    while (seq.length) {
        const i = seq.pop()!;
        ans[i] = ans[f[i]] + 1;
    }
    return ans;
}
