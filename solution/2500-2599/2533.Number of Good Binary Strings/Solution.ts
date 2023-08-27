function goodBinaryStrings(
    minLength: number,
    maxLength: number,
    oneGroup: number,
    zeroGroup: number,
): number {
    const mod = 10 ** 9 + 7;
    const f: number[] = Array(maxLength + 1).fill(0);
    f[0] = 1;
    for (let i = 1; i <= maxLength; ++i) {
        if (i >= oneGroup) {
            f[i] += f[i - oneGroup];
        }
        if (i >= zeroGroup) {
            f[i] += f[i - zeroGroup];
        }
        f[i] %= mod;
    }
    return f.slice(minLength).reduce((a, b) => a + b, 0) % mod;
}
