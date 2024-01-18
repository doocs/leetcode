function minimumCosts(regular: number[], express: number[], expressCost: number): number[] {
    const n = regular.length;
    const f: number[] = new Array(n + 1).fill(0);
    const g: number[] = new Array(n + 1).fill(0);
    g[0] = 1 << 30;
    const cost: number[] = new Array(n).fill(0);
    for (let i = 1; i <= n; ++i) {
        const [a, b] = [regular[i - 1], express[i - 1]];
        f[i] = Math.min(f[i - 1] + a, g[i - 1] + a);
        g[i] = Math.min(f[i - 1] + expressCost + b, g[i - 1] + b);
        cost[i - 1] = Math.min(f[i], g[i]);
    }
    return cost;
}
