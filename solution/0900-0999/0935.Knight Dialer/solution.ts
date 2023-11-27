function knightDialer(n: number): number {
    const MOD: number = 1e9 + 7;

    if (n === 1) {
        return 10;
    }

    const f: number[] = new Array(10).fill(1);

    while (--n > 0) {
        const t: number[] = new Array(10).fill(0);

        t[0] = f[4] + f[6];
        t[1] = f[6] + f[8];
        t[2] = f[7] + f[9];
        t[3] = f[4] + f[8];
        t[4] = f[0] + f[3] + f[9];
        t[6] = f[0] + f[1] + f[7];
        t[7] = f[2] + f[6];
        t[8] = f[1] + f[3];
        t[9] = f[2] + f[4];

        for (let i = 0; i < 10; ++i) {
            f[i] = t[i] % MOD;
        }
    }

    let ans: number = 0;
    for (const v of f) {
        ans = (ans + v) % MOD;
    }

    return ans;
}