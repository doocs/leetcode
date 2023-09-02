function numberOfBeautifulIntegers(low: number, high: number, k: number): number {
    let s = String(high);
    let f: number[][][] = Array(11)
        .fill(0)
        .map(() =>
            Array(21)
                .fill(0)
                .map(() => Array(21).fill(-1)),
        );
    const dfs = (pos: number, mod: number, diff: number, lead: boolean, limit: boolean): number => {
        if (pos >= s.length) {
            return mod == 0 && diff == 10 ? 1 : 0;
        }
        if (!lead && !limit && f[pos][mod][diff] != -1) {
            return f[pos][mod][diff];
        }
        let ans = 0;
        const up = limit ? Number(s[pos]) : 9;
        for (let i = 0; i <= up; ++i) {
            if (i === 0 && lead) {
                ans += dfs(pos + 1, mod, diff, true, limit && i === up);
            } else {
                const nxt = diff + (i % 2 ? 1 : -1);
                ans += dfs(pos + 1, (mod * 10 + i) % k, nxt, false, limit && i === up);
            }
        }
        if (!lead && !limit) {
            f[pos][mod][diff] = ans;
        }
        return ans;
    };
    const a = dfs(0, 0, 10, true, true);
    s = String(low - 1);
    f = Array(11)
        .fill(0)
        .map(() =>
            Array(21)
                .fill(0)
                .map(() => Array(21).fill(-1)),
        );
    const b = dfs(0, 0, 10, true, true);
    return a - b;
}
