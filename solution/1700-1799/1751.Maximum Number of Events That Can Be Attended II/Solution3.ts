function maxValue(events: number[][], k: number): number {
    events.sort((a, b) => a[0] - b[0]);
    const n = events.length;
    const f: number[][] = Array.from({ length: n + 1 }, () => Array(k + 1).fill(0));
    const search = (ed: number, lo: number): number => {
        let left = lo;
        let right = n;
        while (left < right) {
            const mid = (left + right) >> 1;
            if (events[mid][0] > ed) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    };
    for (let i = n - 1; i >= 0; --i) {
        const ed = events[i][1],
            val = events[i][2];
        const p = search(ed, i + 1);
        for (let c = 0; c <= k; ++c) {
            f[i][c] = f[i + 1][c];
            if (c > 0) {
                f[i][c] = Math.max(f[i][c], f[p][c - 1] + val);
            }
        }
    }
    return f[0][k];
}
