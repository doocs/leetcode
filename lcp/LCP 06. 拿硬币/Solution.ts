function minCount(coins: number[]): number {
    let ans = 0;
    for (const coin of coins) {
        ans += Math.floor((coin + 1) / 2);
    }
    return ans;
}
