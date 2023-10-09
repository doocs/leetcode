function superPow(a: number, b: number[]): number {
    let ans = 1;
    const mod = 1337;
    const qpow = (a: number, n: number): number => {
        let ans = 1;
        for (; n; n >>= 1) {
            if (n & 1) {
                ans = Number((BigInt(ans) * BigInt(a)) % BigInt(mod));
            }
            a = Number((BigInt(a) * BigInt(a)) % BigInt(mod));
        }
        return ans;
    };
    for (let i = b.length - 1; ~i; --i) {
        ans = Number((BigInt(ans) * BigInt(qpow(a, b[i]))) % BigInt(mod));
        a = qpow(a, 10);
    }
    return ans;
}
