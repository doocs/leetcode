function countGoodNumbers(n: number): number {
    const mod = 1000000007n;
    const qpow = (x: bigint, n: bigint): bigint => {
        let res = 1n;
        while (n > 0n) {
            if (n & 1n) {
                res = (res * x) % mod;
            }
            x = (x * x) % mod;
            n >>= 1n;
        }
        return res;
    };
    const a = qpow(5n, BigInt(n + 1) / 2n);
    const b = qpow(4n, BigInt(n) / 2n);
    return Number((a * b) % mod);
}
