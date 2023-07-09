function minimumBeautifulSubstrings(s: string): number {
    const ss: Set<number> = new Set();
    const n = s.length;
    const f: number[] = new Array(n).fill(-1);
    for (let i = 0, x = 1; i <= n; ++i) {
        ss.add(x);
        x *= 5;
    }
    const dfs = (i: number): number => {
        if (i === n) {
            return 0;
        }
        if (s[i] === '0') {
            return n + 1;
        }
        if (f[i] !== -1) {
            return f[i];
        }
        f[i] = n + 1;
        for (let j = i, x = 0; j < n; ++j) {
            x = (x << 1) | (s[j] === '1' ? 1 : 0);
            if (ss.has(x)) {
                f[i] = Math.min(f[i], 1 + dfs(j + 1));
            }
        }
        return f[i];
    };
    const ans = dfs(0);
    return ans > n ? -1 : ans;
}
