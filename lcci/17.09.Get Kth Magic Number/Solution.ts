function getKthMagicNumber(k: number): number {
    const dp = [1];
    const index = [0, 0, 0];
    while (dp.length < k) {
        const a = dp[index[0]] * 3;
        const b = dp[index[1]] * 5;
        const c = dp[index[2]] * 7;
        const num = Math.min(a, b, c);
        dp.push(num);
        if (a === num) {
            index[0]++;
        }
        if (b === num) {
            index[1]++;
        }
        if (c === num) {
            index[2]++;
        }
    }
    return dp[k - 1];
}
