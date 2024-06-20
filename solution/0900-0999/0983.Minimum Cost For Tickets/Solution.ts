function mincostTickets(days: number[], costs: number[]): number {
    const n = days.length;
    const f: number[] = Array(n).fill(0);
    const valid: number[] = [1, 7, 30];
    const search = (x: number): number => {
        let [l, r] = [0, n];
        while (l < r) {
            const mid = (l + r) >> 1;
            if (days[mid] >= x) {
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
        if (f[i]) {
            return f[i];
        }
        f[i] = Infinity;
        for (let k = 0; k < 3; ++k) {
            const j = search(days[i] + valid[k]);
            f[i] = Math.min(f[i], dfs(j) + costs[k]);
        }
        return f[i];
    };
    return dfs(0);
}
