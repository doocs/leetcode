function maxTaxiEarnings(n: number, rides: number[][]): number {
    rides.sort((a, b) => a[1] - b[1]);
    const m = rides.length;
    const f: number[] = Array(m + 1).fill(0);
    const search = (x: number, r: number): number => {
        let l = 0;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (rides[mid][1] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    for (let i = 1; i <= m; ++i) {
        const [st, ed, tip] = rides[i - 1];
        const j = search(st + 1, i);
        f[i] = Math.max(f[i - 1], f[j] + ed - st + tip);
    }
    return f[m];
}
