function numPermsDISequence(s: string): number {
    const n = s.length;
    const f: number[][] = Array(n + 1)
        .fill(0)
        .map(() => Array(n + 1).fill(0));
    f[0][0] = 1;
    const mod = 10 ** 9 + 7;
    for (let i = 1; i <= n; ++i) {
        let pre = 0;
        if (s[i - 1] === 'D') {
            for (let j = i; j >= 0; --j) {
                pre = (pre + f[i - 1][j]) % mod;
                f[i][j] = pre;
            }
        } else {
            for (let j = 0; j <= i; ++j) {
                f[i][j] = pre;
                pre = (pre + f[i - 1][j]) % mod;
            }
        }
    }
    let ans = 0;
    for (let j = 0; j <= n; ++j) {
        ans = (ans + f[n][j]) % mod;
    }
    return ans;
}
