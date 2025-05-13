function lengthAfterTransformations(s: string, t: number): number {
    const mod = 1_000_000_007;
    const f: number[][] = Array.from({ length: t + 1 }, () => Array(26).fill(0));

    for (const c of s) {
        f[0][c.charCodeAt(0) - 'a'.charCodeAt(0)]++;
    }

    for (let i = 1; i <= t; i++) {
        f[i][0] = f[i - 1][25] % mod;
        f[i][1] = (f[i - 1][0] + f[i - 1][25]) % mod;
        for (let j = 2; j < 26; j++) {
            f[i][j] = f[i - 1][j - 1] % mod;
        }
    }

    let ans = 0;
    for (let j = 0; j < 26; j++) {
        ans = (ans + f[t][j]) % mod;
    }

    return ans;
}
