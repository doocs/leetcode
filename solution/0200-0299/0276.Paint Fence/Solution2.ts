function numWays(n: number, k: number): number {
    let [f, g] = [k, 0];
    for (let i = 1; i < n; ++i) {
        const ff = (f + g) * (k - 1);
        g = f;
        f = ff;
    }
    return f + g;
}
