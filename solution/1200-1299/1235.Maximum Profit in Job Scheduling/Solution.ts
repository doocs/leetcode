function jobScheduling(startTime: number[], endTime: number[], profit: number[]): number {
    const n = startTime.length;
    const f = new Array(n).fill(0);
    const idx = new Array(n).fill(0).map((_, i) => i);
    idx.sort((i, j) => startTime[i] - startTime[j]);
    const search = (x: number) => {
        let l = 0;
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
    const dfs = (i: number): number => {
        if (i >= n) {
            return 0;
        }
        if (f[i] !== 0) {
            return f[i];
        }
        const j = search(endTime[idx[i]]);
        return (f[i] = Math.max(dfs(i + 1), dfs(j) + profit[idx[i]]));
    };
    return dfs(0);
}
