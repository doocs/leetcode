function deleteString(s: string): number {
    const n = s.length;
    const f: number[] = new Array(n).fill(0);
    const dfs = (i: number): number => {
        if (i == n) {
            return 0;
        }
        if (f[i] > 0) {
            return f[i];
        }
        f[i] = 1;
        for (let j = 1; j <= (n - i) >> 1; ++j) {
            if (s.slice(i, i + j) == s.slice(i + j, i + j + j)) {
                f[i] = Math.max(f[i], dfs(i + j) + 1);
            }
        }
        return f[i];
    };
    return dfs(0);
}
