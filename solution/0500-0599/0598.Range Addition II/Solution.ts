function maxCount(m: number, n: number, ops: number[][]): number {
    for (const [a, b] of ops) {
        m = Math.min(m, a);
        n = Math.min(n, b);
    }
    return m * n;
}
