function maximizeSumOfWeights(edges: number[][], k: number): number {
    const n = edges.length + 1;
    const g: [number, number][][] = Array.from({ length: n }, () => []);
    for (const [u, v, w] of edges) {
        g[u].push([v, w]);
        g[v].push([u, w]);
    }
    const dfs = (u: number, fa: number): [number, number] => {
        let s = 0;
        const t: number[] = [];

        for (const [v, w] of g[u]) {
            if (v === fa) continue;

            const [a, b] = dfs(v, u);
            s += a;
            const d = w + b - a;
            if (d > 0) t.push(d);
        }

        t.sort((a, b) => b - a);
        for (let i = 0; i < Math.min(t.length, k - 1); i++) {
            s += t[i];
        }
        const s2 = s;
        if (t.length >= k) {
            s += t[k - 1];
        }

        return [s, s2];
    };

    const [x, y] = dfs(0, -1);
    return Math.max(x, y);
}
