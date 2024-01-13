function selfDivisiblePermutationCount(n: number): number {
    const f: number[] = Array(1 << n).fill(0);
    f[0] = 1;
    for (let mask = 0; mask < 1 << n; ++mask) {
        const i = bitCount(mask);
        for (let j = 1; j <= n; ++j) {
            if ((mask >> (j - 1)) & 1 && (i % j === 0 || j % i === 0)) {
                f[mask] += f[mask ^ (1 << (j - 1))];
            }
        }
    }
    return f.at(-1)!;
}

function bitCount(i: number): number {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
