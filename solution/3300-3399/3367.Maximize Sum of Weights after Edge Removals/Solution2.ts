function maximizeSumOfWeights(edges: number[][], k: number): number {
    const n = edges.length + 1;
    const g: [number, number][][] = Array.from({ length: n }, () => []);
    for (const [u, v, w] of edges) {
        g[u].push([v, w]);
        g[v].push([u, w]);
    }
    const keep = Array(n).fill(0);
    const reserve = Array(n).fill(0);
    const stk: [number, number, number][] = [[0, -1, 0]];
    while (stk.length) {
        const [u, fa, state] = stk.pop()!;
        if (state === 0) {
            stk.push([u, fa, 1]);
            for (const [v] of g[u]) {
                if (v !== fa) {
                    stk.push([v, u, 0]);
                }
            }
        } else {
            let s = 0;
            const t: number[] = [];
            for (const [v, w] of g[u]) {
                if (v === fa) continue;
                const a = keep[v];
                const b = reserve[v];
                s += a;
                const d = w + b - a;
                if (d > 0) t.push(d);
            }
            t.sort((a, b) => b - a);
            for (let i = 0; i < Math.min(t.length, k - 1); i++) {
                s += t[i];
            }
            reserve[u] = s;
            keep[u] = s + (t.length >= k ? t[k - 1] : 0);
        }
    }
    return Math.max(keep[0], reserve[0]);
}
