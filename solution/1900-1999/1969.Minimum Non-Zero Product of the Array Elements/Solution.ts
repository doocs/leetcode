function minNonZeroProduct(p: number): number {
    const mod = BigInt(1e9 + 7);

    const qpow = (a: bigint, n: bigint): bigint => {
        let ans = BigInt(1);
        for (; n; n >>= BigInt(1)) {
            if (n & BigInt(1)) {
                ans = (ans * a) % mod;
            }
            a = (a * a) % mod;
        }
        return ans;
    };
    const a = (2n ** BigInt(p) - 1n) % mod;
    const b = qpow((2n ** BigInt(p) - 2n) % mod, 2n ** (BigInt(p) - 1n) - 1n);
    return Number((a * b) % mod);
}
