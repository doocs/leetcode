function minimumSubstringsInPartition(s: string): number {
    const n = s.length;
    const f: number[] = Array(n).fill(-1);
    const dfs = (i: number): number => {
        if (i >= n) {
            return 0;
        }
        if (f[i] !== -1) {
            return f[i];
        }
        const cnt: number[] = Array(26).fill(0);
        f[i] = n - i;
        let [k, m] = [0, 0];
        for (let j = i; j < n; ++j) {
            const x = s.charCodeAt(j) - 97;
            k += ++cnt[x] === 1 ? 1 : 0;
            m = Math.max(m, cnt[x]);
            if (j - i + 1 === k * m) {
                f[i] = Math.min(f[i], 1 + dfs(j + 1));
            }
        }
        return f[i];
    };
    return dfs(0);
}
