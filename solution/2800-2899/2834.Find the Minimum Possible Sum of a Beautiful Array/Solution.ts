function minimumPossibleSum(n: number, target: number): number {
    const mod = 1000000007n;
    const N = BigInt(n);
    const T = BigInt(target);
    const m = T >> 1n;
    if (N <= m) {
        return Number((((1n + N) * N) / 2n) % mod);
    }
    const a = ((1n + m) * m) / 2n;
    const b = ((T + T + N - m - 1n) * (N - m)) / 2n;
    return Number((a + b) % mod);
}
