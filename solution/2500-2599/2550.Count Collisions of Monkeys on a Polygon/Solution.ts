function monkeyMove(n: number): number {
    const mod = 10 ** 9 + 7;
    const qpow = (a: number, n: number): number => {
        let ans = 1n;
        for (; n; n >>>= 1) {
            if (n & 1) {
                ans = (ans * BigInt(a)) % BigInt(mod);
            }
            a = Number((BigInt(a) * BigInt(a)) % BigInt(mod));
        }
        return Number(ans);
    };
    return (qpow(2, n) - 2 + mod) % mod;
}
