function minimumPossibleSum(n: number, target: number): number {
    const mod = 10 ** 9 + 7;
    const m = target >> 1;
    if (n <= m) {
        return (((1 + n) * n) / 2) % mod;
    }
    return (((1 + m) * m) / 2 + ((target + target + n - m - 1) * (n - m)) / 2) % mod;
}
