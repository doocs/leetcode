function numPermsDISequence(s: string): number {
    const n = s.length;
    const f: number[][] = Array(n + 1)
        .fill(0)
        .map(() => Array(n + 1).fill(0));
    f[0][0] = 1;
    const mod = 10 ** 9 + 7;
    for (let i = 1; i <= n; ++i) {
        if (s[i - 1] === 'D') {
            for (let j = 0; j <= i; ++j) {
                for (let k = j; k < i; ++k) {
                    f[i][j] = (f[i][j] + f[i - 1][k]) % mod;
                }
            }
        } else {
            for (let j = 0; j <= i; ++j) {
                for (let k = 0; k < j; ++k) {
                    f[i][j] = (f[i][j] + f[i - 1][k]) % mod;
                }
            }
        }
    }
    let ans = 0;
    for (let j = 0; j <= n; ++j) {
        ans = (ans + f[n][j]) % mod;
    }
    return ans;
}
