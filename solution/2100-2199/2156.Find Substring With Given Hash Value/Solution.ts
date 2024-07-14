function subStrHash(
    s: string,
    power: number,
    modulo: number,
    k: number,
    hashValue: number,
): string {
    let h: bigint = BigInt(0),
        p: bigint = BigInt(1);
    const n: number = s.length;
    const mod = BigInt(modulo);
    for (let i: number = n - 1; i >= n - k; --i) {
        const val: bigint = BigInt(s.charCodeAt(i) - 'a'.charCodeAt(0) + 1);
        h = (((h * BigInt(power)) % mod) + val) % mod;
        if (i !== n - k) {
            p = (p * BigInt(power)) % mod;
        }
    }
    let j: number = n - k;
    for (let i: number = n - k - 1; i >= 0; --i) {
        const pre: bigint = BigInt(s.charCodeAt(i + k) - 'a'.charCodeAt(0) + 1);
        const cur: bigint = BigInt(s.charCodeAt(i) - 'a'.charCodeAt(0) + 1);
        h = ((((h - ((pre * p) % mod) + mod) * BigInt(power)) % mod) + cur) % mod;
        if (Number(h) === hashValue) {
            j = i;
        }
    }
    return s.substring(j, j + k);
}
