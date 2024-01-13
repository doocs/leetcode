function maxTaxiEarnings(n: number, rides: number[][]): number {
    rides.sort((a, b) => a[0] - b[0]);
    const m = rides.length;
    const f: number[] = Array(m).fill(-1);
    const search = (x: number, l: number): number => {
        let r = m;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (rides[mid][0] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    const dfs = (i: number): number => {
        if (i >= m) {
            return 0;
        }
        if (f[i] === -1) {
            const [st, ed, tip] = rides[i];
            const j = search(ed, i + 1);
            f[i] = Math.max(dfs(i + 1), dfs(j) + ed - st + tip);
        }
        return f[i];
    };
    return dfs(0);
}
