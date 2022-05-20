function appealSum(s: string): number {
    const n = s.length;
    let dp = new Array(n + 1).fill(0);
    const hashMap = new Map();
    for (let i = 0; i < n; i++) {
        const c = s.charAt(i);
        dp[i + 1] = dp[i] + i + 1 - (hashMap.get(c) || 0);
        hashMap.set(c, i + 1);
    }
    return dp.reduce((a, c) => a + c, 0);
}
