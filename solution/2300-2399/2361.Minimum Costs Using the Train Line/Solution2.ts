function minimumCosts(regular: number[], express: number[], expressCost: number): number[] {
    const n = regular.length;
    let f = 0;
    let g = 1 << 30;
    const cost: number[] = new Array(n).fill(0);
    for (let i = 0; i < n; ++i) {
        const [a, b] = [regular[i], express[i]];
        const ff = Math.min(f + a, g + a);
        const gg = Math.min(f + expressCost + b, g + b);
        [f, g] = [ff, gg];
        cost[i] = Math.min(f, g);
    }
    return cost;
}
