function stringCount(n: number): number {
    const mod = BigInt(10 ** 9 + 7);
    const qpow = (a: bigint, n: number): bigint => {
        let ans = 1n;
        for (; n; n >>>= 1) {
            if (n & 1) {
                ans = (ans * a) % mod;
            }
            a = (a * a) % mod;
        }
        return ans;
    };
    const a = qpow(25n, n);
    const b = a;
    const c = (qpow(25n, n) + ((BigInt(n) * qpow(25n, n - 1)) % mod)) % mod;
    const ab = qpow(24n, n);
    const ac = (qpow(24n, n) + ((BigInt(n) * qpow(24n, n - 1)) % mod)) % mod;
    const bc = ac;
    const abc = (qpow(23n, n) + ((BigInt(n) * qpow(23n, n - 1)) % mod)) % mod;
    const tot = qpow(26n, n);
    return Number((((tot - (a + b + c - ab - ac - bc + abc)) % mod) + mod) % mod);
}
