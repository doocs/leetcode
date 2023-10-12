function numWays(n: number, relation: number[][], k: number): number {
    let f: number[] = new Array(n).fill(0);
    f[0] = 1;
    while (k--) {
        const g: number[] = new Array(n).fill(0);
        for (const [a, b] of relation) {
            g[b] += f[a];
        }
        f = g;
    }
    return f[n - 1];
}
