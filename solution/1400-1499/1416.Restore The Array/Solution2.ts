function numberOfArrays(s: string, k: number): number {
    const mod = 1e9 + 7;
    const n = s.length;
    const d = String(k).length;
    const f = Array(d + 1).fill(0);
    f[n % (d + 1)] = 1;
    for (let i = n - 1; i >= 0; --i) {
        let ways = 0;
        if (s[i] !== '0') {
            let value = 0;
            for (let j = i; j < n && j < i + d; ++j) {
                value = value * 10 + s.charCodeAt(j) - 48;
                if (value > k) {
                    break;
                }
                ways = (ways + f[(j + 1) % (d + 1)]) % mod;
            }
        }
        f[i % (d + 1)] = ways;
    }
    return f[0];
}
