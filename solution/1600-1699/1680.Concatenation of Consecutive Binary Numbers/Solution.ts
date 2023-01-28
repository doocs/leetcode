function concatenatedBinary(n: number): number {
    const mod = BigInt(10 ** 9 + 7);
    let ans = 0n;
    let shift = 0n;
    for (let i = 1n; i <= n; ++i) {
        if ((i & (i - 1n)) == 0n) {
            ++shift;
        }
        ans = ((ans << shift) | i) % mod;
    }
    return Number(ans);
}
