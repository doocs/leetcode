function jobScheduling(startTime: number[], endTime: number[], profit: number[]): number {
    const n = startTime.length;
    const f = new Array(n + 1).fill(0);
    const idx = new Array(n).fill(0).map((_, i) => i);
    idx.sort((i, j) => startTime[i] - startTime[j]);
    const search = (x: number, left: number) => {
        let l = left;
        let r = n;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (startTime[idx[mid]] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    for (let i = n - 1; i >= 0; --i) {
        const j = search(endTime[idx[i]], i + 1);
        f[i] = Math.max(f[i + 1], f[j] + profit[idx[i]]);
    }
    return f[0];
}
