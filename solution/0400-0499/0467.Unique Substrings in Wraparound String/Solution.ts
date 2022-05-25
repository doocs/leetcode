function findSubstringInWraproundString(p: string): number {
    const n = p.length;
    const dp = new Array(26).fill(0);
    let cur = 1;
    dp[p.charCodeAt(0) - 'a'.charCodeAt(0)] = 1;
    for (let i = 1; i < n; i++) {
        if ((p.charCodeAt(i) - p.charCodeAt(i - 1) + 25) % 26 == 0) {
            cur++;
        } else {
            cur = 1;
        }
        const index = p.charCodeAt(i) - 'a'.charCodeAt(0);
        dp[index] = Math.max(dp[index], cur);
    }
    return dp.reduce((r, v) => r + v);
}
