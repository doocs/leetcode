function longestIdealString(s: string, k: number): number {
    const dp = new Array(26).fill(0);
    for (const c of s) {
        const x = c.charCodeAt(0) - 'a'.charCodeAt(0);
        let t = 0;
        for (let i = 0; i < 26; i++) {
            if (Math.abs(x - i) <= k) {
                t = Math.max(t, dp[i] + 1);
            }
        }
        dp[x] = Math.max(dp[x], t);
    }

    return dp.reduce((r, c) => Math.max(r, c), 0);
}
