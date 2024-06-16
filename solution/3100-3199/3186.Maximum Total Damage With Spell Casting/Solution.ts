function maximumTotalDamage(power: number[]): number {
    const n = power.length;
    power.sort((a, b) => a - b);
    const f: number[] = Array(n).fill(0);
    const cnt: Record<number, number> = {};
    const nxt: number[] = Array(n).fill(0);
    for (let i = 0; i < n; ++i) {
        cnt[power[i]] = (cnt[power[i]] || 0) + 1;
        let [l, r] = [i + 1, n];
        while (l < r) {
            const mid = (l + r) >> 1;
            if (power[mid] > power[i] + 2) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        nxt[i] = l;
    }
    const dfs = (i: number): number => {
        if (i >= n) {
            return 0;
        }
        if (f[i]) {
            return f[i];
        }
        const a = dfs(i + cnt[power[i]]);
        const b = power[i] * cnt[power[i]] + dfs(nxt[i]);
        return (f[i] = Math.max(a, b));
    };
    return dfs(0);
}
