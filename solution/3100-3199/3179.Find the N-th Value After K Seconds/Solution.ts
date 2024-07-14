function valueAfterKSeconds(n: number, k: number): number {
    const a: number[] = Array(n).fill(1);
    const mod: number = 10 ** 9 + 7;
    while (k--) {
        for (let i = 1; i < n; ++i) {
            a[i] = (a[i] + a[i - 1]) % mod;
        }
    }
    return a[n - 1];
}
