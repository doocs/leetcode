function maxValue(events: number[][], k: number): number {
    events.sort((a, b) => a[1] - b[1]);
    const n = events.length;
    const f: number[][] = new Array(n + 1).fill(0).map(() => new Array(k + 1).fill(0));
    const search = (x: number, hi: number): number => {
        let l = 0;
        let r = hi;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (events[mid][1] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    for (let i = 1; i <= n; ++i) {
        const [st, _, val] = events[i - 1];
        const p = search(st, i - 1);
        for (let j = 1; j <= k; ++j) {
            f[i][j] = Math.max(f[i - 1][j], f[p][j - 1] + val);
        }
    }
    return f[n][k];
}
