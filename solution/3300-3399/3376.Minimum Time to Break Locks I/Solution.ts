function findMinimumTime(strength: number[], K: number): number {
    const n = strength.length;
    const f: number[] = Array(1 << n).fill(-1);
    const dfs = (i: number): number => {
        if (i === (1 << n) - 1) {
            return 0;
        }
        if (f[i] !== -1) {
            return f[i];
        }
        f[i] = Infinity;
        const x = 1 + K * bitCount(i);
        for (let j = 0; j < n; ++j) {
            if (((i >> j) & 1) == 0) {
                f[i] = Math.min(f[i], dfs(i | (1 << j)) + Math.ceil(strength[j] / x));
            }
        }
        return f[i];
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
