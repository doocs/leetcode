function maxDifference(s: string): number {
    const cnt: Record<string, number> = {};
    for (const c of s) {
        cnt[c] = (cnt[c] || 0) + 1;
    }
    let [a, b] = [0, Infinity];
    for (const [_, v] of Object.entries(cnt)) {
        if (v % 2 === 1) {
            a = Math.max(a, v);
        } else {
            b = Math.min(b, v);
        }
    }
    return a - b;
}
