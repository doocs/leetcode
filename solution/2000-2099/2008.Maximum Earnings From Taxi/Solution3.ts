function maxTaxiEarnings(n: number, rides: number[][]): number {
    rides.sort((a, b) => a[0] - b[0]);
    const m = rides.length;
    const f: number[] = Array(m + 1).fill(0);
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
    for (let i = m - 1; i >= 0; --i) {
        const [st, ed, tip] = rides[i];
        const j = search(ed, i + 1);
        f[i] = Math.max(f[i + 1], f[j] + ed - st + tip);
    }
    return f[0];
}
