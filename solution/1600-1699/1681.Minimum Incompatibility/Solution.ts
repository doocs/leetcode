function minimumIncompatibility(nums: number[], k: number): number {
    const n = nums.length;
    const m = Math.floor(n / k);
    const g: number[] = Array(1 << n).fill(-1);
    for (let i = 1; i < 1 << n; ++i) {
        if (bitCount(i) !== m) {
            continue;
        }
        const s: Set<number> = new Set();
        let [mi, mx] = [20, 0];
        for (let j = 0; j < n; ++j) {
            if ((i >> j) & 1) {
                if (s.has(nums[j])) {
                    break;
                }
                s.add(nums[j]);
                mi = Math.min(mi, nums[j]);
                mx = Math.max(mx, nums[j]);
            }
        }
        if (s.size === m) {
            g[i] = mx - mi;
        }
    }
    const inf = 1e9;
    const f: number[] = Array(1 << n).fill(inf);
    f[0] = 0;
    for (let i = 0; i < 1 << n; ++i) {
        if (f[i] === inf) {
            continue;
        }
        const s: Set<number> = new Set();
        let mask = 0;
        for (let j = 0; j < n; ++j) {
            if (((i >> j) & 1) === 0 && !s.has(nums[j])) {
                s.add(nums[j]);
                mask |= 1 << j;
            }
        }
        if (s.size < m) {
            continue;
        }
        for (let j = mask; j; j = (j - 1) & mask) {
            if (g[j] !== -1) {
                f[i | j] = Math.min(f[i | j], f[i] + g[j]);
            }
        }
    }
    return f[(1 << n) - 1] === inf ? -1 : f[(1 << n) - 1];
}

function bitCount(i: number): number {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
