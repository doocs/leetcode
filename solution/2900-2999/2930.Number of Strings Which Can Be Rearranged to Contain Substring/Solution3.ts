function stringCount(n: number): number {
    const mod = 10 ** 9 + 7;
    const layer = () =>
        Array.from({ length: 2 }, () =>
            Array.from({ length: 3 }, () => Array.from({ length: 2 }, () => 0)),
        );
    let f = layer();
    f[1][2][1] = 1;
    for (let i = 0; i < n; ++i) {
        const g = layer();
        for (let l = 0; l < 2; ++l) {
            for (let e = 0; e < 3; ++e) {
                for (let t = 0; t < 2; ++t) {
                    const a = f[l][e][t] * 23;
                    const b = f[Math.min(1, l + 1)][e][t];
                    const c = f[l][Math.min(2, e + 1)][t];
                    const d = f[l][e][Math.min(1, t + 1)];
                    g[l][e][t] = (a + b + c + d) % mod;
                }
            }
        }
        f = g;
    }
    return f[0][0][0];
}
