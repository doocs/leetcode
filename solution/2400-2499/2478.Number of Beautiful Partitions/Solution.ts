function beautifulPartitions(s: string, k: number, minLength: number): number {
    const prime = (c: string): boolean => {
        return c === '2' || c === '3' || c === '5' || c === '7';
    };

    const n: number = s.length;
    if (!prime(s[0]) || prime(s[n - 1])) return 0;

    const f: number[][] = new Array(n + 1).fill(0).map(() => new Array(k + 1).fill(0));
    const g: number[][] = new Array(n + 1).fill(0).map(() => new Array(k + 1).fill(0));
    const mod: number = 1e9 + 7;

    f[0][0] = g[0][0] = 1;

    for (let i = 1; i <= n; ++i) {
        if (i >= minLength && !prime(s[i - 1]) && (i === n || prime(s[i]))) {
            for (let j = 1; j <= k; ++j) {
                f[i][j] = g[i - minLength][j - 1];
            }
        }
        for (let j = 0; j <= k; ++j) {
            g[i][j] = (g[i - 1][j] + f[i][j]) % mod;
        }
    }

    return f[n][k];
}
