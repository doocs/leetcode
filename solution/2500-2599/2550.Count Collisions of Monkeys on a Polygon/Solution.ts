function monkeyMove(n: number): number {
    const mod = BigInt(10 ** 9 + 7);
    return Number((qmi(2n, n, mod) - 2n + mod) % mod);
}

function qmi(a: bigint, k: number, p: bigint): bigint {
    let res = 1n;
    while (k) {
        if ((k & 1) === 1) {
            res = (res * a) % p;
        }
        k >>= 1;
        a = (a * a) % p;
    }
    return res;
}
