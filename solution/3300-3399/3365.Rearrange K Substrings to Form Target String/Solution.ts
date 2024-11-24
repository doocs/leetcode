function isPossibleToRearrange(s: string, t: string, k: number): boolean {
    const cnt: Record<string, number> = {};
    const n = s.length;
    const m = Math.floor(n / k);
    for (let i = 0; i < n; i += m) {
        const a = s.slice(i, i + m);
        cnt[a] = (cnt[a] || 0) + 1;
        const b = t.slice(i, i + m);
        cnt[b] = (cnt[b] || 0) - 1;
    }
    return Object.values(cnt).every(x => x === 0);
}
