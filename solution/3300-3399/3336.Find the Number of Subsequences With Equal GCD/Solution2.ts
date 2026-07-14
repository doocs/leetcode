function subsequencePairCount(nums: number[]): number {
    const mod = 1_000_000_007;
    const m = Math.max(...nums);
    let f: number[][] = Array.from({ length: m + 1 }, () => Array(m + 1).fill(0));
    f[0][0] = 1;
    const gcd = (a: number, b: number): number => {
        while (b !== 0) {
            [a, b] = [b, a % b];
        }
        return a;
    };
    for (const x of nums) {
        const g: number[][] = Array.from({ length: m + 1 }, () => Array(m + 1).fill(0));
        for (let j = 0; j <= m; ++j) {
            for (let k = 0; k <= m; ++k) {
                if (f[j][k] === 0) {
                    continue;
                }
                const v = f[j][k];
                g[j][k] = (g[j][k] + v) % mod;
                const nj = gcd(x, j);
                g[nj][k] = (g[nj][k] + v) % mod;
                const nk = gcd(x, k);
                g[j][nk] = (g[j][nk] + v) % mod;
            }
        }
        f = g;
    }
    let ans = 0;
    for (let i = 0; i <= m; ++i) {
        ans += f[i][i];
    }
    return (((ans - 1) % mod) + mod) % mod;
}
