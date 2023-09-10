function flipgame(fronts: number[], backs: number[]): number {
    const s: Set<number> = new Set();
    const n = fronts.length;
    for (let i = 0; i < n; ++i) {
        if (fronts[i] === backs[i]) {
            s.add(fronts[i]);
        }
    }
    let ans = 9999;
    for (const v of fronts) {
        if (!s.has(v)) {
            ans = Math.min(ans, v);
        }
    }
    for (const v of backs) {
        if (!s.has(v)) {
            ans = Math.min(ans, v);
        }
    }
    return ans % 9999;
}
