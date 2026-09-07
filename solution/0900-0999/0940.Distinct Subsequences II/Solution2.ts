function distinctSubseqII(s: string): number {
    const mod = 1e9 + 7;
    const f: number[] = Array(26).fill(0);
    let ans = 0;
    for (const c of s) {
        const i = c.charCodeAt(0) - 97;
        const add = (ans + 1 + mod - f[i]) % mod;
        ans = (ans + add) % mod;
        f[i] = (f[i] + add) % mod;
    }
    return ans;
}
