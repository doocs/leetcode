function countKSubsequencesWithMaxBeauty(s: string, k: number): number {
    const f: number[] = new Array(26).fill(0);
    let cnt = 0;
    for (const c of s) {
        const i = c.charCodeAt(0) - 97;
        if (++f[i] === 1) {
            ++cnt;
        }
    }
    if (cnt < k) {
        return 0;
    }
    const mod = BigInt(10 ** 9 + 7);
    const vs: number[] = f.filter(v => v > 0).sort((a, b) => b - a);
    const val = vs[k - 1];
    const x = vs.filter(v => v === val).length;
    let ans = 1n;
    for (const v of vs) {
        if (v === val) {
            break;
        }
        --k;
        ans = (ans * BigInt(v)) % mod;
    }
    const c: number[][] = new Array(x + 1).fill(0).map(() => new Array(k + 1).fill(0));
    for (let i = 0; i <= x; ++i) {
        c[i][0] = 1;
        for (let j = 1; j <= Math.min(i, k); ++j) {
            c[i][j] = (c[i - 1][j] + c[i - 1][j - 1]) % Number(mod);
        }
    }
    const qpow = (a: bigint, n: number): bigint => {
        let ans = 1n;
        for (; n; n >>>= 1) {
            if (n & 1) {
                ans = (ans * a) % BigInt(mod);
            }
            a = (a * a) % BigInt(mod);
        }
        return ans;
    };
    ans = (((ans * BigInt(c[x][k])) % mod) * qpow(BigInt(val), k)) % mod;
    return Number(ans);
}
