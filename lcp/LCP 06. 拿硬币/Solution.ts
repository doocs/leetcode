function minCount(coins: number[]): number {
    let ans = 0;
    for (const x of coins) {
        ans += (x + 1) >> 1;
    }
    return ans;
}
