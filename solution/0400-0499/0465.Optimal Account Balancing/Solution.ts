function minTransfers(transactions: number[][]): number {
    const g: number[] = new Array(12).fill(0);
    for (const [f, t, x] of transactions) {
        g[f] -= x;
        g[t] += x;
    }
    const nums = g.filter(x => x !== 0);
    const m = nums.length;
    const f: number[] = new Array(1 << m).fill(1 << 29);
    f[0] = 0;
    for (let i = 1; i < 1 << m; ++i) {
        let s = 0;
        for (let j = 0; j < m; ++j) {
            if (((i >> j) & 1) === 1) {
                s += nums[j];
            }
        }
        if (s === 0) {
            f[i] = bitCount(i) - 1;
            for (let j = (i - 1) & i; j; j = (j - 1) & i) {
                f[i] = Math.min(f[i], f[j] + f[i ^ j]);
            }
        }
    }
    return f[(1 << m) - 1];
}

function bitCount(i: number): number {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
