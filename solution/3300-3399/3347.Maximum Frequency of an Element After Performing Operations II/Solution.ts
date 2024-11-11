function maxFrequency(nums: number[], k: number, numOperations: number): number {
    const cnt: Record<number, number> = {};
    const d: Record<number, number> = {};
    for (const x of nums) {
        cnt[x] = (cnt[x] || 0) + 1;
        d[x] = d[x] || 0;
        d[x - k] = (d[x - k] || 0) + 1;
        d[x + k + 1] = (d[x + k + 1] || 0) - 1;
    }
    let [ans, s] = [0, 0];
    const keys = Object.keys(d)
        .map(Number)
        .sort((a, b) => a - b);
    for (const x of keys) {
        s += d[x];
        ans = Math.max(ans, Math.min(s, (cnt[x] || 0) + numOperations));
    }

    return ans;
}
