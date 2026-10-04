function maximumTotalDamage(power: number[]): number {
    const n = power.length;
    power.sort((a, b) => a - b);
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
    const f: number[] = Array(n + 1).fill(0);
    for (let i = n - 1; i >= 0; --i) {
        const j = i + cnt[power[i]];
        const a = j <= n ? f[j] : 0;
        const b = power[i] * cnt[power[i]] + f[nxt[i]];
        f[i] = Math.max(a, b);
    }
    return f[0];
}
