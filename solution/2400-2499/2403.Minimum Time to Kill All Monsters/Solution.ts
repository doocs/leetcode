function minimumTime(power: number[]): number {
    const n = power.length;
    const f: number[] = Array(1 << n).fill(-1);
    const dfs = (mask: number): number => {
        if (mask === 0) {
            return 0;
        }
        if (f[mask] !== -1) {
            return f[mask];
        }
        f[mask] = Infinity;
        const gain = 1 + (n - bitCount(mask));
        for (let i = 0; i < n; ++i) {
            if ((mask >> i) & 1) {
                f[mask] = Math.min(f[mask], dfs(mask ^ (1 << i)) + Math.ceil(power[i] / gain));
            }
        }
        return f[mask];
    };
    return dfs((1 << n) - 1);
}

function bitCount(i: number): number {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
