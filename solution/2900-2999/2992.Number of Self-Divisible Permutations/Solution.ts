function selfDivisiblePermutationCount(n: number): number {
    const f: number[] = Array(1 << (n + 1)).fill(-1);
    const dfs = (mask: number): number => {
        if (f[mask] !== -1) {
            return f[mask];
        }
        const i = bitCount(mask) + 1;
        if (i > n) {
            return 1;
        }
        f[mask] = 0;
        for (let j = 1; j <= n; ++j) {
            if (((mask >> j) & 1) === 0 && (i % j === 0 || j % i === 0)) {
                f[mask] += dfs(mask | (1 << j));
            }
        }
        return f[mask];
    };
    return dfs(0);
}

function bitCount(i: number): number {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
