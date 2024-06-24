function numberOfPermutations(n: number, requirements: number[][]): number {
    const req: number[] = Array(n).fill(-1);
    for (const [end, cnt] of requirements) {
        req[end] = cnt;
    }
    if (req[0] > 0) {
        return 0;
    }
    req[0] = 0;
    const m = Math.max(...req);
    const mod = 1e9 + 7;
    const f = Array.from({ length: n }, () => Array(m + 1).fill(0));
    f[0][0] = 1;
    for (let i = 1; i < n; ++i) {
        let [l, r] = [0, m];
        if (req[i] >= 0) {
            l = r = req[i];
        }
        for (let j = l; j <= r; ++j) {
            for (let k = 0; k <= Math.min(i, j); ++k) {
                f[i][j] = (f[i][j] + f[i - 1][j - k]) % mod;
            }
        }
    }
    return f[n - 1][req[n - 1]];
}
