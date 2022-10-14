function distinctSubseqII(s: string): number {
    const mod = 1e9 + 7;
    const dp = new Array(26).fill(0);
    for (const c of s) {
        dp[c.charCodeAt(0) - 'a'.charCodeAt(0)] =
            dp.reduce((r, v) => (r + v) % mod, 0) + 1;
    }
    return dp.reduce((r, v) => (r + v) % mod, 0);
}
