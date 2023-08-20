function maximizeTheProfit(n: number, offers: number[][]): number {
    offers.sort((a, b) => a[1] - b[1]);
    n = offers.length;
    const f: number[] = Array(n + 1).fill(0);
    const g = offers.map(x => x[1]);
    const search = (x: number) => {
        let l = 0;
        let r = n;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (g[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    for (let i = 1; i <= n; ++i) {
        const j = search(offers[i - 1][0]);
        f[i] = Math.max(f[i - 1], f[j] + offers[i - 1][2]);
    }
    return f[n];
}
