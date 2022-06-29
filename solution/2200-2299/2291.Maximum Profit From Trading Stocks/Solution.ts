function maximumProfit(
    present: number[],
    future: number[],
    budget: number,
): number {
    let packet = present.map((v, i) => [v, future[i] - v]);
    let dp = new Array(budget + 1).fill(0);
    for (let [v, w] of packet) {
        for (let j = budget; j >= v; j--) {
            dp[j] = Math.max(dp[j], dp[j - v] + w);
        }
    }
    return dp[budget];
}
