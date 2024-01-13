function numPermsDISequence(s: string): number {
    const n = s.length;
    let f: number[] = Array(n + 1).fill(0);
    f[0] = 1;
    const mod = 10 ** 9 + 7;
    for (let i = 1; i <= n; ++i) {
        let pre = 0;
        const g: number[] = Array(n + 1).fill(0);
        if (s[i - 1] === 'D') {
            for (let j = i; j >= 0; --j) {
                pre = (pre + f[j]) % mod;
                g[j] = pre;
            }
        } else {
            for (let j = 0; j <= i; ++j) {
                g[j] = pre;
                pre = (pre + f[j]) % mod;
            }
        }
        f = g;
    }
    let ans = 0;
    for (let j = 0; j <= n; ++j) {
        ans = (ans + f[j]) % mod;
    }
    return ans;
}
