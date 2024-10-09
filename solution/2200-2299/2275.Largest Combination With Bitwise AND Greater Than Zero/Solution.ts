function largestCombination(candidates: number[]): number {
    const mx = Math.max(...candidates);
    const m = mx.toString(2).length;
    let ans = 0;
    for (let i = 0; i < m; ++i) {
        let cnt = 0;
        for (const x of candidates) {
            cnt += (x >> i) & 1;
        }
        ans = Math.max(ans, cnt);
    }
    return ans;
}
