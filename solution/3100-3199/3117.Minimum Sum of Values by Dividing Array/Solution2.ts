function minimumValueSum(nums: number[], andValues: number[]): number {
    const n = nums.length;
    const m = andValues.length;
    const inf = 1 << 29;
    const stride = 100001;
    let f = new Map<number, number>();
    f.set(0, 0);
    for (let i = 0; i < n; ++i) {
        const g = new Map<number, number>();
        for (const [key, cost] of f) {
            const j = (key / stride) | 0;
            const a = (key % stride) - 1;
            if (n - i < m - j) {
                continue;
            }
            const na = a & nums[i];
            if (na < andValues[j]) {
                continue;
            }
            const nk = j * stride + na + 1;
            g.set(nk, Math.min(g.get(nk) ?? inf, cost));
            if (na === andValues[j]) {
                const t = cost + nums[i];
                if (j + 1 === m) {
                    if (i === n - 1) {
                        const done = m * stride;
                        g.set(done, Math.min(g.get(done) ?? inf, t));
                    }
                } else {
                    const nk2 = (j + 1) * stride;
                    g.set(nk2, Math.min(g.get(nk2) ?? inf, t));
                }
            }
        }
        f = g;
    }
    const ans = f.get(m * stride) ?? inf;
    return ans >= inf ? -1 : ans;
}
