function maximumXorProduct(a: number, b: number, n: number): number {
    const mod = BigInt(1e9 + 7);
    let ax = (BigInt(a) >> BigInt(n)) << BigInt(n);
    let bx = (BigInt(b) >> BigInt(n)) << BigInt(n);
    for (let i = BigInt(n - 1); ~i; --i) {
        const x = (BigInt(a) >> i) & 1n;
        const y = (BigInt(b) >> i) & 1n;
        if (x === y) {
            ax |= 1n << i;
            bx |= 1n << i;
        } else if (ax < bx) {
            ax |= 1n << i;
        } else {
            bx |= 1n << i;
        }
    }
    ax %= mod;
    bx %= mod;
    return Number((ax * bx) % mod);
}
