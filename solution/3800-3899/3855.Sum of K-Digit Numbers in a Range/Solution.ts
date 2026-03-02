function sumOfNumbers(l: number, r: number, k: number): number {
    const mod = 1_000_000_007n;

    const n = BigInt(r - l + 1);

    // ((l + r) * (r - l + 1) / 2) % mod
    const sum = ((BigInt(l + r) * n) / 2n) % mod;

    // pow(r - l + 1, k - 1, mod)
    const part1 = qpow(n % mod, BigInt(k - 1), mod);

    // (pow(10, k, mod) - 1)
    const part2 = (qpow(10n, BigInt(k), mod) - 1n + mod) % mod;

    // Fermat inverse of 9
    const inv9 = qpow(9n, mod - 2n, mod);

    let ans = sum;
    ans = (ans * part1) % mod;
    ans = (ans * part2) % mod;
    ans = (ans * inv9) % mod;

    return Number(ans);
}

function qpow(a: bigint, n: bigint, mod: bigint): bigint {
    a %= mod;
    let ans = 1n;
    while (n > 0n) {
        if ((n & 1n) === 1n) {
            ans = (ans * a) % mod;
        }
        a = (a * a) % mod;
        n >>= 1n;
    }
    return ans;
}
